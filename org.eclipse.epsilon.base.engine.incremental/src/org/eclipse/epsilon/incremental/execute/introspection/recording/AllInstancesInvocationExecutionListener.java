package org.eclipse.epsilon.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.incremental.dom.TracedExecutableBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link IExecutionListener} that monitors execution of operations that retrieve all elements of a type/kind.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class AllInstancesInvocationExecutionListener implements IExecutionListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AllInstancesInvocationExecutionListener.class);
	
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
				try {
					record(currentAst.getTrace(), isKind, typeName);
				} catch (TraceModelDuplicateRelation e) {
					logger.warn("Unable to create traces for the execution of {}", ast, e);
				}				
			}
		}
	}
	
	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	private void record(IExecutionTrace executionTrace, boolean isKind, String modelAndMetaClass) throws TraceModelDuplicateRelation {
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
		IModelTrace modelTrace = new ModelTrace(modelName);
		IModelTypeTrace modelTypeTrace = new ModelTypeTrace(typeName, modelTrace);
		IAllInstancesAccess allIns = new AllInstancesAccess(modelTypeTrace);
		allIns.setOfKind(isKind);
		executionTrace.accesses().create(allIns);
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, not interested
	}

}
