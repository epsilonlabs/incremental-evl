package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;


import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * A property access recorder that creates property access objects in the incremental execution trace model.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public class PropertyAccessRecorder extends AbstractRecorder<IPropertyAccess> implements IPropertyAccessRecorder {
	
	// TODO. Should we get this from the context - TraceManager
	private final IEolExecutionTraceManager<?> traceManager;
	private final IModuleExecution evlExecution;
	
	/** The ExecutionTrace for which property access is being recorded */
	private final IExecutionTrace executionTrace;

	public PropertyAccessRecorder(IEolExecutionTraceManager<?> traceManager, IModuleExecution evlExecution,
			IExecutionTrace executionTrace) {
		super();
		this.traceManager = traceManager;
		this.evlExecution = evlExecution;
		this.executionTrace = executionTrace;
	}

	@Override
	public void record(IModel model, Object modelElement, String propertyName) {
		if (recording) {
			currentRecording.add(createPropertyAccess(model, modelElement, propertyName));
		}
	}

	//TODO Do we need to make this thread safe?
	private IPropertyAccess createPropertyAccess(IModel model, Object modelElement, String propertyName) {
		
		IModelTrace modelTrace = traceManager.modelTraces().getModelTraceByName(model.getName());
		if (modelTrace == null) {
			try {
				modelTrace = evlExecution.createModelTrace(model.getName());
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			} finally {
				traceManager.modelTraces().add(modelTrace);
			}
		}
		IModelElementTrace modelElementTrace = ModelUtil.findElement(modelTrace, model.getElementId(modelElement));
		if (modelElementTrace == null) {
			try {
				modelElementTrace = modelTrace.createModelElementTrace(model.getElementId(modelElement));
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IPropertyTrace propertyTrace = ModelUtil.findProperty(modelElementTrace, propertyName);
		if (propertyTrace == null) {
			try {
				propertyTrace = modelElementTrace.createPropertyTrace(propertyName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IPropertyAccess pa = traceManager.moduleExecutionTraces().getPropertyAccessFor(executionTrace, propertyTrace);
		if (pa == null) {
			try {
				pa = executionTrace.createPropertyAccess(modelElementTrace, propertyTrace);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		return pa;
	}

}
