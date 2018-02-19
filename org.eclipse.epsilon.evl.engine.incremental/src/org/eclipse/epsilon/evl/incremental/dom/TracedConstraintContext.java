package org.eclipse.epsilon.evl.incremental.dom;

import org.eclipse.epsilon.base.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ElementAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceRepository;
import org.eclipse.epsilon.evl.incremental.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
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
		implements TracedModuleElement {
	
	private static final Logger logger = LoggerFactory.getLogger(TracedConstraintContext.class);
	
	private IContextTrace trace;
	private int index;

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
		TracedEvlContext tracedEvlContext = (TracedEvlContext)context;
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
	
	@Override
	public IExecutionTrace getTrace() {
		return trace;
	}

	/**
	 * Add an ElementAccessTrace for the ConstraintContext
	 * 
	 * @param modelElement	The model element for which the Context is being executed
	 * @param context	The IEolContext of the execution
	 * @param owningModel	The model that owns the element
	 * @throws TraceModelDuplicateRelation 
	 */
	private void createContextTrace(Object element, TracedEvlContext context, final IModel model)
			throws EolIncrementalExecutionException {
		
		logger.info("Create ContextTrace: element: {}, context: {}", element, getTypeName());
		if (!(model instanceof IIncrementalModel)) {
			logger.warn("Can not trace non-incremental models. Model {} is not an IIncrementalModel",  model);
			throw new EolIncrementalExecutionException("Can not trace non-incremental models");
		}
		IElementAccess access = ((IIncrementalModel)model).getModelTraceFactory().createElementAccess(element);
		trace.accesses().create(access);
		IEvlExecutionTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleTrace = context.getEvlModuleTrace();
		trace = repo.getContextTraceFor(getTypeName(), index, moduleTrace);
		if (trace == null) {
			try {
				trace = new ContextTrace(getTypeName(), index, moduleTrace);
			} catch (TraceModelDuplicateRelation e1) {
				logger.warn("If the ContextTrace was not in the repo it should have been created correctly.");
				throw new EolIncrementalExecutionException("Error creating new ContextTrace", e1);
			}
			repo.add(trace);
			if (guardBlock != null) {
				try {
					IGuardTrace guard = trace.createGuardTrace();
					((TracedGuardBlock) guardBlock).setTrace(guard);
				} catch (EolIncrementalExecutionException e2) {
					throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e2);	
				}	
			}
		}
		// This created traces for the current execution, do we need to keep groups of traces that executed?
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			IInvariantTrace tcTrace = trace.createInvariantTrace(tc.getName());
			tc.setTrace(tcTrace);
			tc.createGuardTrace();
			tc.createCheckTrace();
			tc.createMessageTrace();
			
		}
	}


}
