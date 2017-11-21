package org.eclipse.epsilon.evl.execute.introspection.recording;


import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

public class PropertyAccessRecorder extends AbstractRecorder<IPropertyAccess> implements IPropertyAccessRecorder {
	
	private final IEvlExecutionTraceManager traceManager;
	private final IEvlModuleExecution evlExecution;
	private final IExecutionTrace executionTrace;

	public PropertyAccessRecorder(IEvlExecutionTraceManager traceManager, IEvlModuleExecution evlExecution,
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

	private IPropertyAccess createPropertyAccess(IModel model, Object modelElement, String propertyName) {
		
		IModelTrace modelTrace = traceManager.modelTraces().getModelTraceByName(model.getName());
		if (modelTrace == null) {
			try {
				//TODO Do we need to make this thread safe?
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
		IPropertyTrace property = ModelUtil.findProperty(modelElementTrace, propertyName);
		if (property == null) {
			try {
				property = modelElementTrace.createPropertyTrace(propertyName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IPropertyAccess pa = traceManager.moduleExecutionTraces().getPropertyAccessFor(executionTrace, property);
		if (pa == null) {
			try {
				pa = executionTrace.createPropertyAccess(modelElementTrace, property);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		return pa;
	}

}
