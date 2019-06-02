package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;

/**
 * A reexecution for a message trace.
 *
 * @author Horacio Hoyos Rodriguez
 */
public class ReexecutionMessageTrace
	extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

	/** The message trace. */
	protected final IMessageTrace messageTrace;

	/**
	 * Instantiates a new reexecution message trace.
	 *
	 * @param msgTrc 			the message trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdlUri 		the evl module uri
	 * @param slfTrc 			the self trace
	 */
	public ReexecutionMessageTrace(
		IMessageTrace msgTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelElementTrace slfTrc) {
		this(msgTrc, exctnCntxt, evlMdlUri, slfTrc, new HashSet<>(), null);
	}

	/**
	 * Instantiates a new reexecution message trace.
	 *
	 * @param msgTrc 			the message trace
	 * @param exctnCntxt 		the execution context
	 * @param evlMdl 			the evl module
	 * @param slfTrc 			the self trace
	 * @param children 			the children
	 * @param parent 			the parent
	 */
	public ReexecutionMessageTrace(
		IMessageTrace msgTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, slfTrc, exctnCntxt, children, parent);
		messageTrace = msgTrc;
	}

	/**
	 * Make child of.
	 *
	 * @param parent the parent
	 * @return the trace reexecution
	 */
	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionMessageTrace(messageTrace, executionContext, moduleTrace, selfTrace, children,
				parent);
	}


	/**
	 * Gets the traced constraint.
	 *
	 * @param strategy the strategy
	 * @return the traced constraint
	 */
	protected TracedConstraint getTracedConstraint(IEvlModuleIncremental evlModule) {
		return evlModule.getTracedConstraint(messageTrace);
	}
	
	/**
	 * Section.
	 *
	 * @return the string
	 */
	protected String section() {
		return "M";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((messageTrace == null) ? 0 : messageTrace.hashCode());
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
		ReexecutionMessageTrace other = (ReexecutionMessageTrace) obj;
		if (messageTrace == null) {
			if (other.messageTrace != null)
				return false;
		} else if (!messageTrace.equals(other.messageTrace))
			return false;
		return true;
	}
	

}
