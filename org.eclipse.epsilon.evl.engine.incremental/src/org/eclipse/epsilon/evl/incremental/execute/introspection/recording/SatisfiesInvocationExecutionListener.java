package org.eclipse.epsilon.evl.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.util.ContextTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An An {@link IExecutionListener} that listens to OperationCallExpressions
 * that match the "Satisfies" name. To avoid re-executing dynamic parameters,
 * the listeners activates in the
 * {@link #aboutToExecute(ModuleElement, IEolContext)} to keep track of the
 * execution results of the parameter expressions. After all parameters have
 * been processes, the listener waits for the operation to finish executing.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class SatisfiesInvocationExecutionListener<T extends IModuleExecutionTrace, R extends IModuleExecutionTraceRepository<?>, M extends IExecutionTraceManager<?, ?, ?>>
		implements IExecutionListener<IIncrementalBaseContext<T, R, M>> {

	private static final Logger logger = LoggerFactory.getLogger(SatisfiesInvocationExecutionListener.class);

	public static final String[] SET_VALUES = new String[] { EvlOperationFactory.SATISFIES_OPERATION,
			EvlOperationFactory.SATISFIES_ONE_OPERATION, EvlOperationFactory.SATISFIES_ALL_OPERATION, };
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));

	/** Keep track of ModuleElements executing */
	private final Deque<TracedModuleElement<IInvariantTrace>> moduleElementStack = new ArrayDeque<>();

	/** The invariant we are currently in */
	// private final IInvariantTrace invariant;

	/** Flag to indicate that it is waiting for parameter execution */
	private boolean listening;

	/** The ASTs of the invocation parameters */
	private Queue<Expression> parameters = new ArrayDeque<>();

	/**
	 * The result of executing the invocation parameters, i.e. the final values of
	 * the parameters of the satisfies invocation
	 */
	private Collection<String> parameterValues = new ArrayList<String>();

	/** The initial satisfies operation */
	private OperationCallExpression waitingFor;

	public SatisfiesInvocationExecutionListener() {
		super();
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IIncrementalBaseContext<T, R, M> context) {
		logger.debug("aboutToExecute {}", ast);
		if (ast instanceof TracedModuleElement) {
			moduleElementStack.addLast((TracedModuleElement<IInvariantTrace>) ast);
		}
		if (!moduleElementStack.isEmpty()) {
			if (ast instanceof OperationCallExpression) {
				OperationCallExpression oce = (OperationCallExpression) ast;
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
	public void finishedExecuting(ModuleElement ast, Object result, IIncrementalBaseContext<T, R, M> context) {
		logger.debug("finishedExecuting {} for {}", ast, result);
		if (moduleElementStack.isEmpty()) {
			return;
		}
		if (listening) {
			if (ast.equals(parameters.peek())) {
				parameterValues.add((String) result);
				parameters.poll();
				listening = !parameters.isEmpty();
			}
		} else {
			TracedModuleElement<IInvariantTrace> currentInvariant = moduleElementStack.peekFirst();
			if (ast.equals(waitingFor)) {
				boolean all = EvlOperationFactory.SATISFIES_ALL_OPERATION.equals(waitingFor.getOperationName());
				record(all, parameterValues, (IInvariantTrace) currentInvariant.getCurrentTrace());
				parameters.clear();
				parameterValues.clear();
				listening = false;
				waitingFor = null;
			} else {
				if (ast instanceof TracedConstraint) {
					TracedConstraint block = (TracedConstraint) ast;
					if (currentInvariant.equals(block)) {
						moduleElementStack.pollFirst();
					}
				}
				// Something went wrong?
				parameters.clear();
				parameterValues.clear();
				listening = false;
				waitingFor = null;
			}
		}

	}

	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	private void record(boolean all, Collection<String> parameterValues, IInvariantTrace invariantTrace) {
		logger.info("Creating SatisfiesTrace. invariant: {}, satisfied: {}, all: {}", invariantTrace.getName(),
				parameterValues, all);

		// Each parameter should be an Invariant name
		IContextTrace contextTrace = invariantTrace.invariantContext().get();
		Set<IInvariantTrace> invariants = new HashSet<>();
		for (Object p : parameterValues) {
			assert p instanceof String;
			String invariantName = (String) p;
			IInvariantTrace targetInvariant = ContextTraceUtil.getInvariantIn(contextTrace, invariantName);
			if (targetInvariant == null) {
				try {
					targetInvariant = contextTrace.getOrCreateInvariantTrace((String) p);
				} catch (EolIncrementalExecutionException e) {
					logger.error("Uknown invariant for {}: {}", invariantName, p);
					throw new IllegalStateException(String.format("Uknown invariant for %s: %s", invariantName, p), e);
				}
			}
			invariants.add(targetInvariant);
		}
		ISatisfiesTrace result = null;
		try {
			result = invariantTrace.getOrCreateSatisfiesTrace();
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			for (IInvariantTrace i : invariants) {
				result.satisfiedInvariants().create(i);
			}
			result.setAll(all);
		}
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception,
			IIncrementalBaseContext<T, R, M> context) {
		logger.debug("finishedExecutingWithException");
	}

}
