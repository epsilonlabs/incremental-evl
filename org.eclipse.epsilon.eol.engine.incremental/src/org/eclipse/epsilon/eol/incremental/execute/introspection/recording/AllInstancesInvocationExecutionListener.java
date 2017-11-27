package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;

/**
 * An {@link IExecutionListener} that monitors execution of operations that retrieve all elements of a type/kind.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class AllInstancesInvocationExecutionListener implements IExecutionListener {
	
	/** The name of the operations of interest */
	public static final String[] SET_VALUES = new String[] { "all",
															 "allInstances",
															 "allOfKind",
															 "allOfType"
															};
	
	/** Static set for quick search */
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));
	
	/** Keep track of ModuleElements executing */
	private final Deque<TracedExecutableBlock<?>> moduleElementStack = new ArrayDeque<>();
	
	/** The trace manager */
	private final IEolExecutionTraceManager<?> traceManager;
	
	/** The Module being executed */
	private final IModuleExecution moduleExecution;
	

	public AllInstancesInvocationExecutionListener(IEolExecutionTraceManager<?> traceManager,
			IModuleExecution moduleExecution) {
		super();
		this.traceManager = traceManager;
		this.moduleExecution = moduleExecution;
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		if (ast instanceof TracedExecutableBlock) {
			moduleElementStack.addLast((TracedExecutableBlock<?>) ast);
		}
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedExecutableBlock<?> currentAst = moduleElementStack.peekFirst();
		if (currentAst.equals(ast)) {
			moduleElementStack.pollFirst();
		}
		if (ast instanceof OperationCallExpression) {
			OperationCallExpression oce = (OperationCallExpression)ast;
			String operationName = oce.getOperationName();
			if (OPERATION_NAMES.contains(operationName)) {
				// We assume the targetExpression resolved in a type
				String typeName = oce.getTargetExpression().getResolvedType().getName();
				boolean isKind = !"allOfType".equals(operationName);
				record(currentAst.getTrace(), isKind, typeName);				
			}
		}
	}
	
	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	private void record(IExecutionTrace executionTrace, boolean isKind, String modelAndMetaClass) {
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
		if (modelTrace == null) {
			try {
				//TODO Do we need to make this thread safe?
				// FIXME What is the default model name?
				modelTrace = moduleExecution.createModelTrace(modelName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			} finally {
				traceManager.modelTraces().add(modelTrace);
			}
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
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, not interested
	}

}
