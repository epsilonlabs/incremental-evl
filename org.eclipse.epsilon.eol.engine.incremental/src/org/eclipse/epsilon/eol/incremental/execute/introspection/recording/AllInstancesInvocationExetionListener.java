package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

/**
 * An Execution listener that monitors execution of operations that retrieve all elements of a type/kind.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class AllInstancesInvocationExetionListener implements IExecutionListener {
	
	/** The name of the operations of interest */
	public static final String[] SET_VALUES = new String[] { "all",
															 "allInstances",
															 "allOfKind",
															 "allOfType"
															};
	
	/** Static set for quick search */
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));
	
	/** The recorders that are notified by this listener */
	private final Collection<IAllInstancesInvocationRecorder> recorders = new ArrayList<IAllInstancesInvocationRecorder>();
	
	
	public AllInstancesInvocationExetionListener(IAllInstancesInvocationRecorder... recorders) {
		this.recorders.addAll(Arrays.asList(recorders));
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		// Pass, not interested
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (ast instanceof OperationCallExpression) {
			OperationCallExpression oce = (OperationCallExpression)ast;
			String operationName = oce.getOperationName();
			if (OPERATION_NAMES.contains(operationName)) {
				// We assume the targetExpression resolved in a type
				String typeName = oce.getTargetExpression().getResolvedType().getName();
				for (IAllInstancesInvocationRecorder recorder : recorders) {
					boolean isKind = !"allOfType".equals(operationName);
					recorder.record(isKind , typeName);
				}
			}
		}
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, not interested
	}

}
