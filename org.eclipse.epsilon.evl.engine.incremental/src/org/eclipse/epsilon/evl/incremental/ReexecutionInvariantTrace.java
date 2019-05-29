package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
 * A reexecution for an invariant trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
//Invaraints should not have accesses and hence no need for re-execution, this class not needed
public class ReexecutionInvariantTrace extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

	/** The invariant trace. */
	protected final IInvariantTrace invariantTrace;

	/**
	 * Instantiates a new reexecution invariant trace.
	 *
	 * @param invrtTrc			the invariant trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdlUri 		the evl module uri
	 * @param slfTrc 			the self trace
	 */
	public ReexecutionInvariantTrace(
		IInvariantTrace invrtTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelElementTrace slfTrc) {
		this(invrtTrc, exctnCntxt, evlMdlUri, slfTrc, new HashSet<>(), null);
	}

	/**
	 * Instantiates a new reexecution invariant trace.
	 *
	 * @param invrtTrc			the invariant trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdl 			the evl module
	 * @param slfTrc 			the self trace
	 * @param children 			the children
	 * @param parent 			the parent
	 */
	public ReexecutionInvariantTrace(
		IInvariantTrace invrtTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, slfTrc, exctnCntxt, children, parent);
		invariantTrace = invrtTrc;
	}

	/**
	 * Make child of.
	 *
	 * @param parent the parent
	 * @return the trace reexecution
	 */
	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionInvariantTrace(invariantTrace, executionContext, moduleTrace, selfTrace, children,
				parent);
	}

	/**
	 * Module element trace.
	 *
	 * @return the i invariant trace
	 */
	@Override
	public IInvariantTrace moduleElementTrace() {
		return invariantTrace;
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
		return "I";
	}
}
