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
package org.eclipse.epsilon.eol.incremental.trace;

// TODO: Auto-generated Javadoc
/**
 * The {@link IModelElement} interface represents a model element vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface IModelElement extends TraceComponent { //, VertexFrame {

	/**
  * Gets the element id.
  *
  * @return The id of this model element
  */
	String getElementId();

	/**
	 * Set the id of this model element.
	 *
	 * @param elementId            The new id
	 */
	void setElementId(String elementId);

	/**
	 * Get the scopes that use this model element as its root.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	Iterable<ITraceScope> getScopes();

	/**
	 * Add a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this element.
	 */
	ITraceScope addScope(ITraceScope scope);

	/**
	 * Remove a scope that uses this model element as its root.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	void removeScope(ITraceScope scope);

	/**
	 * Get the properties that are owned by this model element.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	Iterable<IElementProperty> getProperties();

	/**
	 * Add a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this element.
	 */
	IElementProperty addProperty(IElementProperty property);

	/**
	 * Remove a property that is owned by this model element as its root.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	void removeProperty(IElementProperty property);

}
