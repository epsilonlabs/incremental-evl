package org.eclipse.epsilon.eol.incremental.trace;

public interface IContextTrace extends TraceComponent { //, VertexFrame {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "context";

	/**
	 * Key to the 'name' property of this constraint
	 */
//	String NAME = "name";

	/**
	 * @return The name of this context
	 */
//	@Property(NAME)
	String getName();

	/**
	 * Set the name of this context
	 * 
	 * @param name
	 *            The new name of this context
	 */
//	@Property(NAME)
	void setName(String name);

//	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	Iterable<IConstraintTrace> getConstraints();

//	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	IConstraintTrace addConstraint(IConstraintTrace constraint);

//	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	void removeConstraint(IConstraintTrace constraint);
}
