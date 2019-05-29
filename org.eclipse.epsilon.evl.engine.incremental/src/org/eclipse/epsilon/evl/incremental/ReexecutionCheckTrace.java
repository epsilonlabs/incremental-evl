package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ReexecutionCheckTrace
		extends ConstraintModuleElementTraceReexecution
		implements TraceReexecution {
	
	protected final ICheckTrace checkTrace;

	public ReexecutionCheckTrace(
		ICheckTrace chckTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc) {
		this(chckTrc, exctnCntxt, evlMdlUri, mdlTrc, slfTrc, new HashSet<>(), null);
	}
		
	public ReexecutionCheckTrace(
		ICheckTrace chckTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, mdlTrc, slfTrc, exctnCntxt, children, parent);
		checkTrace = chckTrc;
	}

	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionCheckTrace(checkTrace, executionContext, moduleTrace, modelTrace, selfTrace, children, parent);
	}
	
	@Override
	public ICheckTrace moduleElementTrace() {
		return checkTrace;
	}

	protected TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy) {
		return ((IncrementalEvlExecutionStrategy) strategy).getConstraint(moduleElementTrace());
	}
	
	protected String section() {
		return "C";
	}


}
