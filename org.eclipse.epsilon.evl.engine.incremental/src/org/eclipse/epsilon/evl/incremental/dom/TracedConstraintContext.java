package org.eclipse.epsilon.evl.incremental.dom;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.util.IExecutionContextRepository;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceRepository;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can create ElementAccessTraces and that
 * starts/stops the recording of the guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext 
		implements TracedModuleElement<IContextTrace> {
	
	private static final Logger logger = LoggerFactory.getLogger(TracedConstraintContext.class);
	
	private IContextTrace currentTrace;
	private int index;
	
	@Override
	public void setCurrentTrace(IContextTrace trace) {
		this.currentTrace = trace;
	}

	@Override
	public IContextTrace getCurrentTrace() {
		return currentTrace;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		this.index = cst.childIndex;
	}

	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfType(object, getTypeName())) {
			return false;
		}
		IncrementalEvlContext tracedEvlContext = (IncrementalEvlContext)context;
		try {
			createContextTrace(object, tracedEvlContext, owningModel);
		} catch (EolIncrementalExecutionException e) {
			throw new EolRuntimeException("Error creating trace information. " + e.getMessage(), this);
		}
		if (guardBlock != null) {
			
			tracedEvlContext.getTraceManager().getPropertyAccessListener().aboutToExecute(guardBlock, context);
			tracedEvlContext.getTraceManager().getAllInstancesAccessListener().aboutToExecute(guardBlock, context);
			Boolean result = guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
			tracedEvlContext.getTraceManager().getPropertyAccessListener().finishedExecuting(guardBlock, result, context);
			tracedEvlContext.getTraceManager().getAllInstancesAccessListener().finishedExecuting(guardBlock, result, context);
			return result;
		} else {
			return true;
		}
	}
	

	/**
	 * Add an ElementAccessTrace for the ConstraintContext
	 * 
	 * @param modelElement	The model element for which the Context is being executed
	 * @param context	The IEolContext of the execution
	 * @param owningModel	The model that owns the element
	 * @throws TraceModelDuplicateRelation 
	 */
	private void createContextTrace(Object element, IncrementalEvlContext context, final IModel model)
			throws EolIncrementalExecutionException {
		
		logger.info("Create ContextTrace: element: {}, context: {}", element, getTypeName());
		if (!(model instanceof IIncrementalModel)) {
			logger.error("Model {} does not implement IIncrementalModel",  model);
			throw new EolIncrementalExecutionException("Cannot trace non-incremental models");
		}
		IEvlExecutionTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IExecutionContextRepository contextRepo = context.getTraceManager().getExecutionContextRepository();
		IEvlModuleTrace moduleTrace = context.getEvlModuleTrace();
		
		
		
		IModelElementVariable selfVariable =  ((IIncrementalModel)model).getModelTraceFactory()
				.createModelElementVariable("self", element);
		IExecutionContext exContext = contextRepo.getExecutionContextFor(selfVariable);
		if (exContext == null) {
			try {
				exContext = new ExecutionContext();
			} catch (TraceModelDuplicateRelation e) {
				logger.warn("If the ExecutionContext was not in the repo it should have been created correctly.");
				throw new EolIncrementalExecutionException("Error creating new ExecutionContext", e);
			}
			contextRepo.add(exContext);
		}
		exContext.contextVariables().create(selfVariable);
		currentTrace = repo.getContextTraceFor(getTypeName(), index, moduleTrace, exContext);
		if (currentTrace == null) {
			logger.debug("Creating a new context trace");
			try {
				currentTrace = new ContextTrace(getTypeName(), index, moduleTrace, exContext);
			} catch (TraceModelDuplicateRelation e1) {
				logger.warn("If the ContextTrace was not in the repo it should have been created correctly.");
				throw new EolIncrementalExecutionException("Error creating new ContextTrace", e1);
			}
			repo.add(currentTrace);
			if (guardBlock != null) {
				try {
					IGuardTrace guard = currentTrace.createGuardTrace();
					((TracedGuardBlock) guardBlock).setCurrentTrace(guard);
				} catch (EolIncrementalExecutionException e2) {
					throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e2);	
				}	
			}
		}
		IElementAccess access = ((IIncrementalModel)model).getModelTraceFactory().createElementAccess(element, currentTrace);
		if (!currentTrace.accesses().create(access)) {
			logger.info("ElementAcces was not added to ContextTrace");
		}
		// This created traces for the current execution, do we need to keep groups of traces that executed?
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			IInvariantTrace tcTrace = currentTrace.createInvariantTrace(tc.getName());
			repo.add(tcTrace);
			tc.setCurrentTrace(tcTrace);
			if (tc.createGuardTrace()) {
				repo.add(tcTrace.guard().get());
			}
			if (tc.createCheckTrace()) {
				repo.add(tcTrace.check().get());
			}
			if (tc.createMessageTrace()) {
				repo.add(tcTrace.message().get());
			}
		}
	}

	public void goOnline() {
		for (Constraint c : constraints) {
			((TracedConstraint)c).setListeningToChagnes(true);
		}
	}
	
	public void goOffline() {
		for (Constraint c : constraints) {
			((TracedConstraint)c).setListeningToChagnes(false);
		}
	}
	
}
