package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TAccesses} interface represents an edge between a scope and a
 * single property accessed by that scope in the trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TAccesses extends TraceGraphEdge {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "accesses";

	/**
	 * @return The scope that is accessing the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	TScope getScope();

	/**
	 * @return The property that has been accessed at {@link #getScope()}.
	 */
	@InVertex
	TProperty getProperty();
	
}
