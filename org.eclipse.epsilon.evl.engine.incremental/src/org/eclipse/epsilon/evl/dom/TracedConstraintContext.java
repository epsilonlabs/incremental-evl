package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

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
	
	/**
	 * Create a trace for the 
	 */
	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfType(object, getTypeName())) {
			return false;
		}
		// Add the ModelElementTrace
		IEvlExecutionTraceManager unitOfWork = ((TracedEvlContext)context).getTraceManager();
		IEvlModuleExecution evlExecution = ((TracedEvlContext)context).getEvlExecution();
		IModelTrace model = unitOfWork.modelTraces().getModelTraceByName(owningModel.getName());
		if (model == null) {
			try {
				model = evlExecution.createModelTrace(owningModel.getName());
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			} finally {
				unitOfWork.modelTraces().add(model);				
			}
		}
		String elementUri = owningModel.getElementId(object);
		IModelElementTrace modelElement = ModelUtil.findElement(model, elementUri);
		if (modelElement == null) {
			try {
				modelElement = model.createModelElementTrace(elementUri);
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			}
		}
		
		IElementAccess eAccess = unitOfWork.contextTraces().getElementAccessFor(trace, modelElement);
		if (eAccess == null) {
			try {
				eAccess = trace.createElementAccess(modelElement);
			} catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException(e.getMessage(), this);
			}
		}
		if (guardBlock != null) {
			return guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
		} else {
			return true;
		}
	}
}
