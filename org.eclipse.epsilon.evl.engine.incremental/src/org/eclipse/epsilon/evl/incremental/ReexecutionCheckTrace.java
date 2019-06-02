package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

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
	 * Gets the traced constraint.
	 *
	 * @param strategy the strategy
	 * @return the traced constraint
	 */
	@Override
	protected TracedConstraint getTracedConstraint(IEvlModuleIncremental evlModule) {
		return evlModule.getTracedConstraint(checkTrace);
	}
	
	/**
	 * Section.
	 *
	 * @return the string
	 */
	protected String section() {
		return "C";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((checkTrace == null) ? 0 : checkTrace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReexecutionCheckTrace other = (ReexecutionCheckTrace) obj;
		if (checkTrace == null) {
			if (other.checkTrace != null)
				return false;
		} else if (!checkTrace.equals(other.checkTrace))
			return false;
		return true;
	}
	
	

}
