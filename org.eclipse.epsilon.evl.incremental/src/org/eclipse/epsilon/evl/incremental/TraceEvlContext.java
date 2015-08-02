package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraphFactory;
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

	private TraceGraph graph = null;
	
	public TraceGraph<? extends Graph> getTraceGraph() {
		if (graph == null) {
			this.graph = OrientTraceGraphFactory.getInstance().getGraph();
		}

		return this.graph;
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
