package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * A reexecution for a check trace.
 *
 * @author Horacio Hoyos Rodriguez
 */
public class ReexecutionCheckTrace
		extends ConstraintModuleElementTraceReexecution
		implements TraceReexecution {
	
	/** The check trace. */
	protected final ICheckTrace checkTrace;

	/**
	 * Instantiates a new reexecution check trace.
	 *
	 * @param chckTrc			the check trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdlUri 		the evl module uri
	 * @param slfTrc 			the self trace
	 */
	public ReexecutionCheckTrace(
		ICheckTrace chckTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelElementTrace slfTrc) {
		this(chckTrc, exctnCntxt, evlMdlUri, slfTrc, new HashSet<>(), null);
	}
		
	/**
	 * Instantiates a new reexecution check trace.
	 *
	 * @param chckTrc 			the check trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdl 			the evl module
	 * @param slfTrc 			the self trace
	 * @param children 			the children
	 * @param parent 			the parent
	 */
	public ReexecutionCheckTrace(
		ICheckTrace chckTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, slfTrc, exctnCntxt, children, parent);
		checkTrace = chckTrc;
	}

	/**
	 * Make child of.
	 *
	 * @param parent 			the parent
	 * @return the trace reexecution
	 */
	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionCheckTrace(checkTrace, executionContext, moduleTrace, selfTrace, children, parent);
	}
	
	/**
	 * Module element trace.
	 *
	 * @return the i check trace
	 */
	@Override
	public ICheckTrace moduleElementTrace() {
		return checkTrace;
	}

	/**
	 * Gets the traced constraint.
	 *
	 * @param strategy the strategy
	 * @return the traced constraint
	 */
	protected TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy) {
		return ((IncrementalEvlExecutionStrategy) strategy).getConstraint(moduleElementTrace());
	}
	
	/**
	 * Section.
	 *
	 * @return the string
	 */
	protected String section() {
		return "C";
	}

}
