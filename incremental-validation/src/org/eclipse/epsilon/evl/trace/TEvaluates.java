package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TEvaluates} interface represents an edge between a scope and the
 * constraint used to evaluate it.
 * 
 * @author Jonathan Co
 *
 */
public interface TEvaluates extends TraceEdge {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "evaluates";

	/**
	 * @return The scope that is evaluated by the constraint at
	 *         {@link #getConstraint()}.
	 */
	@InVertex
	TScope getScope();

	/**
	 * @return The constraint that is used to evaluate the scope at
	 *         {@link #getScope()}.
	 */
	@OutVertex
	TConstraint getConstraint();

}
