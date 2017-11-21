package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

public class AllInstancesInvocationExetionListener implements IExecutionListener {
	
	public static final String[] SET_VALUES = new String[] { "all",
															 "allInstances",
															 "allOfKind",
															 "allOfType"
															};
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));
	
	private final List<IAllInstancesInvocationRecorder> recorders = new ArrayList<IAllInstancesInvocationRecorder>();
	


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
