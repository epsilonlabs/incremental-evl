package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;

/**
 * An all instances invocation recorder that creates objects in the incremental execution trace model.
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public class AllInstancesInvocationRecorder extends AbstractRecorder<IAllInstancesAccess> implements IAllInstancesInvocationRecorder {
	
	private final IEolExecutionTraceManager traceManager;
	private final IExecutionTrace executionTrace;
	private final IModuleExecution evlExecution;
	
	public AllInstancesInvocationRecorder(IEolExecutionTraceManager traceManager, IModuleExecution evlExecution,
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
			// FIXME What is the default model name?
			modelTrace = evlExecution.createModelTrace(modelName);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			traceManager.modelTraces().add(modelTrace);
		}
		IModelTypeTrace modelTypeTrace = ModelUtil.findModelType(modelTrace, typeName);
		if (modelTypeTrace == null) {
			try {
				modelTypeTrace = modelTrace.createModelTypeTrace(typeName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IAllInstancesAccess allIns = traceManager.moduleExecutionTraces().getAllInstancesAccessFor(executionTrace, modelTypeTrace);
		if (allIns == null) {
			try {
				allIns = executionTrace.createAllInstancesAccess(modelTypeTrace);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		allIns.setOfKind(isKind);
		return allIns;
	}
}
