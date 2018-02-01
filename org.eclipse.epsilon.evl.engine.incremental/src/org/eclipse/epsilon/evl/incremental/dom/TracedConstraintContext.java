package org.eclipse.epsilon.evl.incremental.dom;

import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ElementAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
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
import org.eclipse.epsilon.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can create ElementAccessTraces and that
 * starts/stops the recording of the guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext {
	
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
		} catch (TraceModelDuplicateRelation e) {
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
	private void createContextTrace(Object modelElement, TracedEvlContext context, final IModel owningModel)
			throws TraceModelDuplicateRelation {
		
		IModelTrace modelTrace;
		modelTrace = new ModelTrace(owningModel.getName());
		String elementUri = owningModel.getElementId(modelElement);
		IModelElementTrace modelElementTrace = new ModelElementTrace(elementUri, modelTrace);
		IElementAccess access = new ElementAccess(modelElementTrace);
		trace.accesses().create(access);
		IEvlExecutionTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleTrace = new EvlModuleTrace(module.getSourceUri().toString());
		trace = repo.getContextTraceFor(getTypeName(), index, moduleTrace);
		if (trace == null) {
			trace = new ContextTrace(getTypeName(), index, moduleTrace);
			repo.add(trace);
			if (guardBlock != null) {
				try {
					IGuardTrace guard = trace.createGuardTrace();
					((TracedGuardBlock) guardBlock).setTrace(guard);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e);	
				}	
			}
		}
		// This created traces for the current execution, do we need to keep groups of traces that executed?
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			try {
				IInvariantTrace tcTrace = trace.createInvariantTrace(tc.getName());
				tc.setTrace(tcTrace);
				tc.createGuardTrace();
				tc.createCheckTrace();
				tc.createMessageTrace();
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException("Error creating execution trace elements.", e);
			}
		}
	}
}
