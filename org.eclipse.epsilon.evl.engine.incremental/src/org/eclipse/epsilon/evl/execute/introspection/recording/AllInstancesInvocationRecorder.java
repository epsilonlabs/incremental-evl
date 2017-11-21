package org.eclipse.epsilon.evl.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

public class AllInstancesInvocationRecorder extends AbstractRecorder<IAllInstancesAccess> implements IAllInstancesInvocationRecorder {
	
	private final IEvlExecutionTraceManager traceManager;
	private final IExecutionTrace executionTrace;
	private final IEvlModuleExecution evlExecution;
	
	public AllInstancesInvocationRecorder(IEvlExecutionTraceManager traceManager, IEvlModuleExecution evlExecution,
			IExecutionTrace executionTrace) {
		super();
		this.traceManager = traceManager;
		this.executionTrace = executionTrace;
		this.evlExecution = evlExecution;
	}

	@Override
	public void record(boolean isKind, String modelAndMetaClass) {
		
		if (recording) {
			currentRecording.add(createAllInstancesAccess(isKind, modelAndMetaClass));
		}
	}

	/**
	 * @param isKind
	 * @param modelAndMetaClass
	 */
	public IAllInstancesAccess createAllInstancesAccess(boolean isKind, String modelAndMetaClass) {
		String modelName;
		String typeName;
		if (modelAndMetaClass.indexOf("!") > -1){
			String[] parts = modelAndMetaClass.split("!");
			modelName = parts[0];
			typeName = parts[1];
		}
		else {
			modelName = "";
			typeName = modelAndMetaClass;
		}
		IModelTrace modelTrace;
		if (!modelName.isEmpty()) {
			modelTrace = traceManager.modelTraces().getModelTraceByName(modelName);
		}
		else {
			// Assume one model (i.e the element type is not qualified)
			modelTrace = traceManager.modelTraces().first();
		}
		try {
			//TODO Do we need to make this thread safe?
			modelTrace = evlExecution.createModelTrace(modelName);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			traceManager.modelTraces().add(modelTrace);
		}
		IModelTypeTrace modelType = ModelUtil.findModelType(modelTrace, typeName);
		if (modelType == null) {
			try {
				modelType = modelTrace.createModelTypeTrace(typeName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IAllInstancesAccess allIns = traceManager.moduleExecutionTraces().getAllInstancesAccessFor(executionTrace, modelType);
		if (allIns == null) {
			try {
				allIns = executionTrace.createAllInstancesAccess(modelType);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		allIns.setOfKind(isKind);
		return allIns;
	}
}
