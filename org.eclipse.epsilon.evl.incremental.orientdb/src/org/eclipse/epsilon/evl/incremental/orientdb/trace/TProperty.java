package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link TProperty} interface represents a model element property vertex
 * in the trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TProperty extends IElementProperty, VertexFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "property";

	/**
	 * Key to the 'name' property of this property
	 */
	String NAME = "name";

	/**
	 * @return The name of this property
	 */
	@Property(TProperty.NAME)
	String getName();

	/**
	 * Set the name of this property
	 * 
	 * @param name
	 *            The new name of this property
	 */
	@Property(TProperty.NAME)
	void setName(String name);

	/**
	 * Get the model element that owns this property.
	 * 
	 * @return The owning model element.
	 */
	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.IN)
	IModelElement getOwner();

	/**
	 * Set the model element that owns this property.
	 * 
	 * @param element
	 *            The new model element that owns this property.
	 */
	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.IN)
	IModelElement setOwner(IModelElement element);

	/**
	 * Get the scopes that access this property
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.IN)
	Iterable<ITraceScope> getScopes();

	/**
	 * Add a scope that accesses this property.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this property.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.IN)
	ITraceScope addScope(ITraceScope scope);

	/**
	 * Remove a scope that accesses this property.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.IN)
	void removeScope(ITraceScope scope);

}
