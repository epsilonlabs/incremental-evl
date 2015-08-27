package org.eclipse.epsilon.evl.incremental.trace;


/**
 * Interface specifying common methods for a factory for {@link TraceGraph}s.
 * 
 * @author Jonathan Co
 *
 * @param <T>
 *            The type of graph engine used by the produced {@link TraceGraph}s.
 */
public interface TraceGraphFactory {

	/**
	 * @return An instance of the {@link TraceGraph}.
	 */
	TraceGraph getGraph();
	
}
