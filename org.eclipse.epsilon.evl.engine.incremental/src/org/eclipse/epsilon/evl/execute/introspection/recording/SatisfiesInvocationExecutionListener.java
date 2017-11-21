package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;

/**
 * An IExecutionListener that listens to OperationCallExpressions that match the "Satisfies" name.
 * To avoid re-executing dynamic parameters, the listeners activates in the {@link #aboutToExecute(ModuleElement, IEolContext)}
 * to keep track of the execution results of the parameter expressions. After all parameters have been processes, the
 * listener waits for the operation to finish executing.
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
public class SatisfiesInvocationExecutionListener implements IExecutionListener {
	
	public static final String[] SET_VALUES = new String[] { EvlOperationFactory.SATISFIES_OPERATION,
															 EvlOperationFactory.SATISFIES_ONE_OPERATION,
															 EvlOperationFactory.SATISFIES_ALL_OPERATION,
															};
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));
	
	private final List<ISatisfiesInvocationRecorder> recorders = new ArrayList<ISatisfiesInvocationRecorder>();
	
	private boolean listening;
	private Queue<Expression> parameters = new ArrayDeque<>();
	private Collection<String> parameterValues = new ArrayList<String>();
	private OperationCallExpression waitingFor;
	
	
	public SatisfiesInvocationExecutionListener(ISatisfiesInvocationRecorder... recorders) {
		this.recorders.addAll(Arrays.asList(recorders));
	}
	
	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		if (ast instanceof OperationCallExpression) {
			OperationCallExpression oce = (OperationCallExpression)ast;
			String operationName = oce.getOperationName();
			if (OPERATION_NAMES.contains(operationName)) {
				listening = true;
				parameters.addAll(oce.getParameterExpressions());
				waitingFor = oce;
			}
		}
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (listening) {
			if (ast.equals(parameters.peek())) {
				parameterValues.add((String) result);
				parameters.poll();
				listening = !parameters.isEmpty(); 
			}
		}
		if (!listening && waitingFor.equals(ast)) {
			boolean all = EvlOperationFactory.SATISFIES_ALL_OPERATION.equals(waitingFor.getOperationName());
			for (ISatisfiesInvocationRecorder recorder : recorders) {
				recorder.record(all, parameterValues);
			}
		} else if (waitingFor.equals(ast)) {
			// Something went wrong?
			parameters.clear();
			listening = false;
			waitingFor = null;
		}
		
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, only interested in finished
	}

}
