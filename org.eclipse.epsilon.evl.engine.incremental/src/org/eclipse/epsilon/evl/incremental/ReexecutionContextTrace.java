package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that
 * should be re-executed
 * 
 * @author horacio
 *
 */
// Invaraints should not have accesses and hence no need for re-execution, this class not needed
public class ReexecutionContextTrace extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

	protected final IContextTrace contextTrace;

	public ReexecutionContextTrace(
		IContextTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc) {
		this(trace, exctnCntxt, evlMdlUri, mdlTrc, slfTrc, new HashSet<>(), null);
	}

	public ReexecutionContextTrace(
		IContextTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, mdlTrc, slfTrc, exctnCntxt, children, parent);
		contextTrace = trace;
	}

	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionContextTrace(contextTrace, executionContext, moduleTrace, modelTrace, selfTrace, children,
				parent);
	}

	@Override
	public IContextTrace moduleElementTrace() {
		return contextTrace;
	}
	

	@Override
	public void reexecute(IIncrementalBaseContext context, IncrementalExecutionStrategy strategy)
			throws EolRuntimeException {
		assert context instanceof IIncrementalEvlContext;
		assert strategy instanceof IncrementalEvlExecutionStrategy;
		Object selfVal = getSelf((IIncrementalEvlContext) context);
		TracedConstraint tc = getTracedConstraint(strategy);
		tc.executeImpl(selfVal, (IIncrementalEvlContext) context, section());
	}

	@Override
	protected TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy) {
		// TODO Implement EvlModuleElementTraceReexecution.getTracedConstraint
		throw new RuntimeException("Unimplemented Method EvlModuleElementTraceReexecution.getTracedConstraint invoked.");
	}

	@Override
	protected String section() {
		// TODO Implement EvlModuleElementTraceReexecution.section
		throw new RuntimeException("Unimplemented Method EvlModuleElementTraceReexecution.section invoked.");
	}
}
