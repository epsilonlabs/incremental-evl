package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
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
public class ReexecutionInvariantTrace
	extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

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
	
	@Override
	public void reexecute(IIncrementalBaseContext context, IModuleIncremental evlModule)
			throws EolRuntimeException {
		// Do nothing
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

	@Override
	protected TracedConstraint getTracedConstraint(IEvlModuleIncremental evlModul) {
		// TODO Implement EvlModuleElementTraceReexecution.section
		throw new RuntimeException("Unimplemented Method EvlModuleElementTraceReexecution.getTracedConstraint invoked.");
	}

	/**
	 * Section.
	 *
	 * @return the string
	 */
	protected String section() {
		return "I";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((invariantTrace == null) ? 0 : invariantTrace.hashCode());
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
		ReexecutionInvariantTrace other = (ReexecutionInvariantTrace) obj;
		if (invariantTrace == null) {
			if (other.invariantTrace != null)
				return false;
		} else if (!invariantTrace.equals(other.invariantTrace))
			return false;
		return true;
	}
	
	

}
