/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

// TODO: Auto-generated Javadoc
/**
 * The {@link TElement} interface represents a model element vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TElement extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "element";

	/** Key to the 'element_id' property of this constraint. */
	String ELEMENT_ID = "element_id";

	/**
	 * Gets the element id.
	 *
	 * @return The id of this model element
	 */
	@Property(ELEMENT_ID)
	String getElementId();

	/**
	 * Set the id of this model element.
	 *
	 * @param elementId            The new id
	 */
	@Property(ELEMENT_ID)
	void setElementId(String elementId);

	/**
	 * Get the scopes that use this model element as its root.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	Iterable<TScope> getScopes();

	/**
	 * Add a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this element.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	TScope addScope(TScope scope);

	/**
	 * Remove a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.OUT)
	void removeScope(TScope scope);

	/**
	 * Get the properties that are owned by this model element.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	Iterable<TProperty> getProperties();

	/**
	 * Add a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this element.
	 */
	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	TProperty addProperty(TProperty property);

	/**
	 * Remove a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
	void removeProperty(TProperty property);

}
