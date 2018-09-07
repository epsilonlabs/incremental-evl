package org.eclipse.epsilon.evl.incremental.dom;

import java.util.Collection;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can
 * create ElementAccessTraces and that starts/stops the recording of the
 * guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext implements TracedModuleElement<IContextTrace> {

	private static final Logger logger = LoggerFactory.getLogger(TracedConstraintContext.class);

	private IContextTrace currentTrace;
	private IExecutionContext currentContext;
	private int index;

	@Override
	public void setCurrentTrace(IContextTrace trace) {
		this.currentTrace = trace;
	}

	@Override
	public IContextTrace getCurrentTrace() {
		return currentTrace;
	}

	public IExecutionContext getCurrentContext() {
		return currentContext;
	}

	public void setCurrentContext(IExecutionContext currentContext) {
		this.currentContext = currentContext;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		this.index = cst.childIndex;
	}

	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfKind(object, getTypeName())) {
			return false;
		}
		@SuppressWarnings("unchecked")
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> tracedEvlContext = (IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>>) context;
		return appliesTo(object, (IIncrementalModel) owningModel, tracedEvlContext);

	}

	private boolean appliesTo(
		Object object,
		IIncrementalModel owningModel,
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> tracedEvlContext)
		throws EolRuntimeException {
		try {
			createContextTraces(object, tracedEvlContext, owningModel);
		} catch (EolIncrementalExecutionException e) {
			throw new EolRuntimeException("Error creating trace information. " + e.getMessage(), this);
		}
		if (guardBlock != null) {
			tracedEvlContext.getPropertyAccessExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			Boolean result = guardBlock.execute(tracedEvlContext, Variable.createReadOnlyVariable("self", object));
			tracedEvlContext.getPropertyAccessExecutionListener().finishedExecuting(guardBlock, result,
					tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().finishedExecuting(guardBlock, result,
					tracedEvlContext);
			return result;
		} else {
			return true;
		}

	}

	/**
	 * Create an IContextTrace and add an ElementAccessTrace.
	 * 
	 * This method is called once for each execution of the Context and therefore we
	 * create a new IContextTrace for each. Each new IContextTrace will have a
	 * companion IExecutionContext that stores the context information of the
	 * execution.
	 * 
	 * @param modelElement The model element for which the Context is being executed
	 * @param context      The IEolContext of the execution
	 * @param owningModel  The model that owns the element
	 * @throws EolRuntimeException
	 * @throws TraceModelDuplicateElement
	 */
	private void createContextTraces(Object modelElement,
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> context,
			final IIncrementalModel model) throws EolIncrementalExecutionException, EolRuntimeException {

		logger.info("createContextTrace: element: {}, context: {}", modelElement, getTypeName());
		String moduleUri = context.getModule().getUri().toString();
		IEvlModuleTrace moduleExecutionTrace = getModuleExecutionTrace(context, moduleUri);
		IModelElementTrace elementTrace = IncrementalUtils.getOrCreateModelElementTrace(modelElement, context,
				model);
		
		createExecutionTraces(moduleExecutionTrace, elementTrace);
		
		currentContext = currentTrace.getOrCreateExecutionContext();
		if (guardBlock != null) {
			((TracedExecutableBlock<?,?>) guardBlock).setCurrentContext(getCurrentContext());
		}
		currentContext.getOrCreateModelElementVariable("self", elementTrace);
		currentContext.getOrCreateElementAccess(currentTrace, elementTrace);
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			IInvariantTrace invariantTrace = currentTrace.getOrCreateInvariantTrace(tc.getName());
			try {
				invariantTrace.contextModuleElement().create(currentTrace);
				tc.setCurrentTrace(invariantTrace);
				tc.setCurrentContext(getCurrentContext());
				if (tc.hasGuard()) {
					IGuardTrace gt = tc.getOrCreateGuardTrace();
					gt.getOrCreateGuardResult(getCurrentContext());
					boolean r = gt.contextModuleElement().create(currentTrace);
					if (!r) {
						logger.error("Not possible to create parent trace for guard");
					}
				}
				if (tc.hasCheck()) {
					ICheckTrace ct = tc.getOrCreateCheckTrace();
					ct.getOrCreateCheckResult(getCurrentContext());
					boolean r = ct.contextModuleElement().create(currentTrace);
					if (!r) {
						logger.error("Not possible to create parent trace for check");
					}
				}
				if (tc.hasMessage()) {
					IMessageTrace mt = tc.getOrCreateMessageTrace();
					boolean r = mt.contextModuleElement().create(currentTrace);
					if (!r) {
						logger.error("Not possible to create parent trace for message");
					}
				}
			} catch (TraceModelConflictRelation e) {
				throw new EolIncrementalExecutionException("Not possible to create parent trace for invariant", e);
			}
		}
	}

	/**
	 * @param context
	 * @param moduleUri
	 * @return
	 * @throws EolIncrementalExecutionException
	 * @throws EolRuntimeException
	 */
	private IEvlModuleTrace getModuleExecutionTrace(
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> context,
			String moduleUri) throws EolIncrementalExecutionException, EolRuntimeException {
		IEvlModuleTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleExecutionTrace = repo.getEvlModuleTraceByIdentity(moduleUri);
		if (moduleExecutionTrace == null) {
			throw new EolIncrementalExecutionException(
					"A moduleExecutionTrace was not found for the module under execution. "
							+ "The module execution trace must be created at the begining of the execution of the module.");
		}
		return moduleExecutionTrace;
	}

	/**
	 * @param moduleExecutionTrace
	 * @param elementTrace
	 * @throws EolIncrementalExecutionException
	 */
	private void createExecutionTraces(IEvlModuleTrace moduleExecutionTrace, IModelElementTrace elementTrace)
			throws EolIncrementalExecutionException {

		currentTrace = moduleExecutionTrace.getOrCreateContextTrace(getTypeName(), index);
		if (guardBlock != null) {
			try {
				IGuardTrace guard = currentTrace.getOrCreateGuardTrace();
				((TracedExecutableBlock<IGuardTrace, Boolean>) guardBlock).setCurrentTrace(guard);
				guard.contextModuleElement().create(currentTrace);
			} catch (EolIncrementalExecutionException e1) {
				throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e1);
			} catch (TraceModelConflictRelation e2) {
				throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e2);
			}
		}
	}

	public void goOnline() {
		for (Constraint c : constraints) {
			((TracedConstraint) c).setListeningToChagnes(true);
		}
	}

	public void goOffline() {
		for (Constraint c : constraints) {
			((TracedConstraint) c).setListeningToChagnes(false);
		}
	}

	// Hack to solve bug #538175
	public Collection<?> getAllOfSourceKind(IEvlContext context)
			throws EolModelElementTypeNotFoundException, EolModelNotFoundException {
		String modelName = getType(context).getModel().getName();

		return context.getModelRepository().getModelByName(modelName).getAllOfKind(getTypeName());
	}

}
