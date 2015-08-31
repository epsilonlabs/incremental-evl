package org.eclipse.epsilon.evl.incremental.trace;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

public interface TContext extends TraceComponent, VertexFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "context";

	/**
	 * Key to the 'name' property of this constraint
	 */
	String NAME = "name";

	/**
	 * @return The name of this context
	 */
	@Property(NAME)
	String getName();

	/**
	 * Set the name of this context
	 * 
	 * @param name
	 *            The new name of this context
	 */
	@Property(NAME)
	void setName(String name);

	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	Iterable<TConstraint> getConstraints();

	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	TConstraint addConstraint(TConstraint constraint);

	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	void removeConstraint(TConstraint constraint);
}
