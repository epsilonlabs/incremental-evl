package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
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
	
	private IContextTrace trace;

	public IContextTrace getTrace() {
		return trace;
	}

	public void setTrace(IContextTrace trace) {
		this.trace = trace;
	}
	
	
	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		assert module instanceof IncrementalEvlModule;
		String typeName = getTypeName();
		if (typeName == null) {
			throw new IllegalStateException("Can't create TracedConstraintContext for unknown (null type.");
		}
		else {
			IncrementalEvlModule evlModule = (IncrementalEvlModule) module;
			TracedEvlContext context = (TracedEvlContext) evlModule.getContext();
			IEvlExecutionTraceManager<IEvlModuleExecution>  etManager = context.getTraceManager();
			trace = etManager.contextTraces().getContextFor(typeName , (IEvlModuleTrace) evlModule.getModuleTrace());
			if (trace == null) {
				try {
					trace = ((IEvlModuleExecution) evlModule.getModuleExecution()).createContextTrace(typeName);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException("Can't create ContextTrace for type " + typeName + ".", e);			
				} finally {
					if (trace != null) {
						etManager.contextTraces().add(trace);
					}
				}
			}
		}
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
		if (guardBlock != null) {
			IGuardTrace guard = trace.guard().get();
			if (guard == null) {
				try {
					guard = trace.createGuardTrace();
					((TracedExecutableBlock<?>) guardBlock).setTrace(guard);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e);	
				}
			}
		}
	}

	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfType(object, getTypeName())) {
			return false;
		}
		createContextTrace(object, context, owningModel);
		if (guardBlock != null) {
			return guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
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
		IElementAccess eAccess = traceManager.contextTraces().getElementAccessFor(trace, modelElementTrace);
		if (eAccess == null) {
			try {
				eAccess = trace.createElementAccess(modelElementTrace);
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			}
		}
	}
}
