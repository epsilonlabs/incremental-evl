package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.blueprints.Graph;

/**
 * Interface specifying common methods for a factory for {@link TraceGraph}s.
 * 
 * @author Jonathan Co
 *
 * @param <T>
 *            The type of graph engine used by the produced {@link TraceGraph}s.
 */
public interface TraceGraphFactory<T extends Graph> {

	/**
	 * @return An instance of the {@link TraceGraph}.
	 */
	TraceGraph<T> getGraph();
	
}
