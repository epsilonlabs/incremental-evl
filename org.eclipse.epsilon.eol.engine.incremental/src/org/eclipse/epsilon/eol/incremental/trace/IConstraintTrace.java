package org.eclipse.epsilon.eol.incremental.trace;

/**
 * The {@link IConstraintTrace} interface represents a constraint vertex in the trace
 * graph.
 * 
 * @author Jonathan Co
 *
 */
// TODO: Split name and add in a TClass or TContext vertex
public interface IConstraintTrace extends TraceComponent {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "constraint";

	/**
	 * Key to the 'name' property of this constraint
	 */
//	String NAME = "name";

	/**
	 * @return The name of this constraint
	 */
//	@Property(NAME)
	String getName();

	/**
	 * Set the name of this constraint
	 * 
	 * @param name
	 *            The new name of this constraint
	 */
//	@Property(NAME)
	void setName(String name);

	/**
	 * Get the scopes that are evaluated by this constraint.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
//	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	Iterable<ITraceScope> getScopes();

	/**
	 * Add a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this constraint.
	 */
//	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	ITraceScope addScope(ITraceScope scope);

	/**
	 * Remove a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
//	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	void removeScope(ITraceScope scope);
	
//	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.OUT)
	IContextTrace getContext();
	
//	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.OUT)
	IContextTrace addContext(IContextTrace context);

}
