package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.execute.operations.AbstractOperation;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;

public class OperationInvocationExecutionListener implements IExecutionListener {
	
	private final List<IOperationInvocationRecorder> recorders = new ArrayList<IOperationInvocationRecorder>();
	
	public OperationInvocationExecutionListener(IOperationInvocationRecorder... recorders) {
		this.recorders.addAll(Arrays.asList(recorders));
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (ast instanceof OperationCallExpression) {
			OperationCallExpression oce = (OperationCallExpression)ast;
			String operationName = oce.getOperationName();
			if (EvlOperationFactory.SATISFIES_ALL_OPERATION.equals(operationName) ||
					EvlOperationFactory.SATISFIES_OPERATION.equals(operationName) ||
					EvlOperationFactory.SATISFIES_ONE_OPERATION.equals(operationName)) {
				
				for (IOperationInvocationRecorder recorder : recorders) {
					recorder.record(operationName);
				}
			}
			
		}
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {

	}

}
