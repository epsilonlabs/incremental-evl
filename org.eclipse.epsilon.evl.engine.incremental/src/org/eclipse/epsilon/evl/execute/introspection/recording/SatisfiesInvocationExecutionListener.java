package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
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
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.evl.dom.TracedConstraint;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.util.ContextTraceUtil;

/**
 * An An {@link IExecutionListener}  that listens to OperationCallExpressions that match the "Satisfies" name.
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
	
	/** Keep track of ModuleElements executing */
	private final Deque<IInvariantTrace> moduleElementStack = new ArrayDeque<>();
	
	/** The invariant we are currently in */
	//private final IInvariantTrace invariant;
	
	/** Flag to indicate that it is waiting for parameter execution */
	private boolean listening;
	
	/** The ASTs of the invocation parameters */
	private Queue<Expression> parameters = new ArrayDeque<>();
	
	/** The result of executing the invocation parameters, i.e. the final values of the paramters of the satisfies
	 * invocation
	 */
	private Collection<String> parameterValues = new ArrayList<String>();
	
	/** The initial satisfies operation */
	private OperationCallExpression waitingFor;
	
	
	public SatisfiesInvocationExecutionListener() {
		super();
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		if (ast instanceof TracedConstraint) {
			TracedConstraint block = (TracedConstraint) ast;
			moduleElementStack.addLast((IInvariantTrace) block.getTrace());
		}
		if (!moduleElementStack.isEmpty()) {	
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
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (moduleElementStack.isEmpty()) {
			return;
		}
		if (listening) {
			if (ast.equals(parameters.peek())) {
				parameterValues.add((String) result);
				parameters.poll();
				listening = !parameters.isEmpty(); 
			}
		}
		else {
			IInvariantTrace currentInvariant = moduleElementStack.peekFirst();
			if (ast.equals(waitingFor)) {
				boolean all = EvlOperationFactory.SATISFIES_ALL_OPERATION.equals(waitingFor.getOperationName());
				record(all, parameterValues, currentInvariant);				
			}
			else {
				if (ast instanceof TracedConstraint) {
					TracedConstraint block = (TracedConstraint) ast;
					if (currentInvariant.equals(block.getTrace())) {
						moduleElementStack.pollFirst();
					}
				}
				// Something went wrong?
				parameters.clear();
				listening = false;
				waitingFor = null;
			}
		}
		
	}

	private void record(boolean all, Collection<String> parameterValues, IInvariantTrace invariant) {
		// Each parameter should be an Invariant name
		IContextTrace context = invariant.invariantContext().get();
		List<IInvariantTrace> invariants = new ArrayList<>();
		for (Object p : parameterValues) {
			assert p instanceof String;
			String invariantName = (String) p;
			IInvariantTrace  targetInvariant = ContextTraceUtil.getInvariantIn(context, invariantName);
			if (targetInvariant == null) {
				try {
					targetInvariant = context.createInvariantTrace((String) p);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException(String.format("Uknown invariant for %s: %s", invariantName, p), e);
				}
			}
		}
		ISatisfiesTrace result = null;
		try {
			result = invariant.createSatisfiesTrace();
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			for (IInvariantTrace i : invariants) {
				result.invariants().create(i);
			}
			result.setAll(all);
		}
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, only interested in finished
	}

}
