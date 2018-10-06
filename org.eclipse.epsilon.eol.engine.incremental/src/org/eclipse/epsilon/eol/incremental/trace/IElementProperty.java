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
 * The {@link IElementProperty} interface represents a model element property vertex
 * in the trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface IElementProperty extends TraceComponent { //, VertexFrame {

	/**
  * Gets the name.
  *
  * @return The name of this property
  */
	String getName();

	/**
	 * Set the name of this property.
	 *
	 * @param name            The new name of this property
	 */
	void setName(String name);

	/**
	 * Get the model element that owns this property.
	 * 
	 * @return The owning model element.
	 */
	IModelElement getOwner();

	/**
	 * Set the model element that owns this property.
	 *
	 * @param element            The new model element that owns this property.
	 * @return the i model element
	 */
	IModelElement setOwner(IModelElement element);

	/**
	 * Get the scopes that access this property.
	 *
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	Iterable<ITraceScope> getScopes();

	/**
	 * Add a scope that accesses this property.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this property.
	 */
	ITraceScope addScope(ITraceScope scope);

	/**
	 * Remove a scope that accesses this property.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	void removeScope(ITraceScope scope);

}
