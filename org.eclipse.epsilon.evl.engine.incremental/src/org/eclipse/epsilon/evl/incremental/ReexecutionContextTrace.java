package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * A re-execution for a context trace.
 *
 * @author Horacio Hoyos Rodriguez
 */
public class ReexecutionContextTrace extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

	/** The context trace. */
	protected final IContextTrace contextTrace;

	/**
	 * Instantiates a new reexecution context trace.
	 *
	 * @param trace the trace
	 * @param exctnCntxt the exctn cntxt
	 * @param evlMdlUri the evl mdl uri
	 * @param slfTrc the slf trc
	 */
	public ReexecutionContextTrace(
		IContextTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelElementTrace slfTrc) {
		this(trace, exctnCntxt, evlMdlUri, slfTrc, new HashSet<>(), null);
	}

	/**
	 * Instantiates a new reexecution context trace.
	 *
	 * @param trace the trace
	 * @param exctnCntxt the exctn cntxt
	 * @param evlMdl the evl mdl
	 * @param slfTrc the slf trc
	 * @param children the children
	 * @param parent the parent
	 */
	public ReexecutionContextTrace(
		IContextTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, slfTrc, exctnCntxt, children, parent);
		contextTrace = trace;
	}

	/**
	 * Make child of.
	 *
	 * @param parent the parent
	 * @return the trace reexecution
	 */
	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionContextTrace(contextTrace, executionContext, moduleTrace, selfTrace, children, parent);
	}

	/**
	 * Module element trace.
	 *
	 * @return the i context trace
	 */
	@Override
	public IContextTrace moduleElementTrace() {
		return contextTrace;
	}

	/**
	 * Reexecute.
	 *
	 * @param context the context
	 * @param strategy the strategy
	 * @throws EolRuntimeException the eol runtime exception
	 */
	@Override
	public void reexecute(IIncrementalBaseContext context, IncrementalExecutionStrategy strategy)
			throws EolRuntimeException {
		assert context instanceof IIncrementalEvlContext;
		assert strategy instanceof IncrementalEvlExecutionStrategy;
		Object selfVal = getSelf((IIncrementalEvlContext) context);
		TracedConstraintContext tcc = ((IncrementalEvlExecutionStrategy) strategy).getConstraintContext(moduleElementTrace());
		tcc.execute(selfVal, (IIncrementalEvlContext)context);
	}

	/**
	 * Gets the traced constraint.
	 *
	 * @param strategy the strategy
	 * @return the traced constraint
	 */
	@Override
	protected TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy) {
		// TODO Implement EvlModuleElementTraceReexecution.getTracedConstraint
		throw new RuntimeException("Unimplemented Method EvlModuleElementTraceReexecution.getTracedConstraint invoked.");
	}

	/**
	 * Section.
	 *
	 * @return the string
	 */
	@Override
	protected String section() {
		// TODO Implement EvlModuleElementTraceReexecution.section
		throw new RuntimeException("Unimplemented Method EvlModuleElementTraceReexecution.section invoked.");
	}
}
