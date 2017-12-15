package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can create ElementAccessTraces and that
 * starts/stops the recording of the guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext {

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
		createContextTrace(object, context, owningModel);
		if (guardBlock != null) {
			((TracedEvlContext)context).getTraceManager().getPropertyAccessListener().aboutToExecute(guardBlock, context);
			((TracedEvlContext)context).getTraceManager().getAllInstancesAccessListener().aboutToExecute(guardBlock, context);
			Boolean result = guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
			((TracedEvlContext)context).getTraceManager().getPropertyAccessListener().finishedExecuting(guardBlock, result, context);
			((TracedEvlContext)context).getTraceManager().getAllInstancesAccessListener().finishedExecuting(guardBlock, result, context);
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
	 * @throws EolRuntimeException	If elements of the trace model can not be created.
	 */
	private void createContextTrace(Object modelElement, IEvlContext context, final IModel owningModel)
			throws EolRuntimeException {
		// Add the ModelElementTrace
		IEvlExecutionTraceManager<IEvlModuleExecution> traceManager = ((TracedEvlContext)context).getTraceManager();
		IEvlModuleExecution evlExecution = ((TracedEvlContext)context).getEvlExecution();
		IModelTrace modelTrace = traceManager.modelTraces().getModelTraceByName(owningModel.getName());
		if (modelTrace == null) {
			try {
				modelTrace = evlExecution.createModelTrace(owningModel.getName());
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			} finally {
				traceManager.modelTraces().add(modelTrace);				
			}
		}
		String elementUri = owningModel.getElementId(modelElement);
		IModelElementTrace modelElementTrace = ModelUtil.findElement(modelTrace, elementUri);
		if (modelElementTrace == null) {
			try {
				modelElementTrace = modelTrace.createModelElementTrace(elementUri);
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			}
		}
		IContextTrace contextTrace;
		String typeName = getTypeName();
		if (typeName == null) {
			throw new IllegalStateException("Can't create TracedConstraintContext for unknown (null type.");
		}
		contextTrace = traceManager.contextTraces().getContextTraceFor(typeName, index, modelElementTrace);
		if (contextTrace == null) {
			try {
				contextTrace = evlExecution.createContextTrace(typeName, index, modelElementTrace);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException("Can't create ContextTrace for type " + typeName + ".", e);			
			} finally {
				if (contextTrace != null) {
					traceManager.contextTraces().add(contextTrace);
				}
			}
		}
		// This created traces for the current execution, this DOES NOT work
		// if ConstraintContext/Constraints are executed in parallel
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			try {
				IInvariantTrace tcTrace = evlExecution.createInvariantTrace(tc.getName());
				tc.setTrace(tcTrace);
				tcTrace.invariantContext().create(contextTrace);
				tc.createGuardTrace(evlExecution);
				tc.createCheckTrace(evlExecution);
				tc.createMessageTrace(evlExecution);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException("Error creating execution trace elements.", e);
			}
		}
		if (guardBlock != null) {
			IGuardTrace guard = contextTrace.guard().get();
			if (guard == null) {
				try {
					guard = evlExecution.createGuardTrace(contextTrace);
					((TracedExecutableBlock<?>) guardBlock).setTrace(guard);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e);	
				}
			}
		}
	}
}
