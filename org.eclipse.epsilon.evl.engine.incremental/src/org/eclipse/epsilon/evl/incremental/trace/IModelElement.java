package org.eclipse.epsilon.evl.incremental.trace;

/**
 * The {@link IModelElement} interface represents a model element vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface IModelElement extends TraceComponent { //, VertexFrame {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "element";

	/**
	 * Key to the 'element_id' property of this constraint
	 */
//	String ELEMENT_ID = "element_id";

	/**
	 * @return The id of this model element
	 */
//	@Property(ELEMENT_ID)
	String getElementId();

	/**
	 * Set the id of this model element
	 * 
	 * @param elementId
	 *            The new id
	 */
//	@Property(ELEMENT_ID)
	void setElementId(String elementId);

	/**
	 * Get the scopes that use this model element as its root.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
//	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	Iterable<ITraceScope> getScopes();

	/**
	 * Add a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this element.
	 */
//	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	ITraceScope addScope(ITraceScope scope);

	/**
	 * Remove a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
//	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	void removeScope(ITraceScope scope);

	/**
	 * Get the properties that are owned by this model element.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	Iterable<IElementProperty> getProperties();

	/**
	 * Add a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this element.
	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	IElementProperty addProperty(IElementProperty property);

	/**
	 * Remove a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to remove.
	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	void removeProperty(IElementProperty property);

}
