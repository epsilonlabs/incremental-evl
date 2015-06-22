package org.eclipse.epsilon.evl;

import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;
import org.eclipse.epsilon.evl.trace.TraceGraph;

import com.tinkerpop.blueprints.Graph;

public class TraceEvlContext extends EvlContext {

	public TraceGraph<? extends Graph> getTraceGraph() {
		return ((TraceEvlModule) getModule()).getTraceGraph();
	}

	@Override
	public ConstraintTrace getConstraintTrace() {
		throw new UnsupportedOperationException();
	}

}
