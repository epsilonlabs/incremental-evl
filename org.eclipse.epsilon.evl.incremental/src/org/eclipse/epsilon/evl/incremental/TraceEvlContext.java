package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

import com.tinkerpop.blueprints.Graph;

/**
 * Subclass of {@link EvlContext} for use with incremental evaluation.
 * 
 * @author Jonathan Co
 *
 */
public class TraceEvlContext extends EvlContext {

	public TraceGraph<? extends Graph> getTraceGraph() {
		return ((TraceEvlModule) getModule()).getTraceGraph();
	}

	/**
	 * Throws {@link UnsupportedOperationException}. This is not used in 
	 * incremental evaluation.
	 */
	@Override
	public ConstraintTrace getConstraintTrace() {
		throw new UnsupportedOperationException();
	}

}
