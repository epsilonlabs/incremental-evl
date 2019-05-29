package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;

/**
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ReexecutionMessageTrace extends ConstraintModuleElementTraceReexecution implements TraceReexecution {

	protected final IMessageTrace messageTrace;

	public ReexecutionMessageTrace(
		IMessageTrace msgTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc) {
		this(msgTrc, exctnCntxt, evlMdlUri, mdlTrc, slfTrc, new HashSet<>(), null);
	}

	public ReexecutionMessageTrace(
		IMessageTrace msgTrc,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelTrace mdlTrc,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> children,
		TraceReexecution parent) {
		super(evlMdl, mdlTrc, slfTrc, exctnCntxt, children, parent);
		messageTrace = msgTrc;
	}

	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionMessageTrace(messageTrace, executionContext, moduleTrace, modelTrace, selfTrace, children,
				parent);
	}

	@Override
	public IMessageTrace moduleElementTrace() {
		return messageTrace;
	}

	protected TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy) {
		return ((IncrementalEvlExecutionStrategy) strategy).getConstraint(moduleElementTrace());
	}
	
	protected String section() {
		return "M";
	}

}
