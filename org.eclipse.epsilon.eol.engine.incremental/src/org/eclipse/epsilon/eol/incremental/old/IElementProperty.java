/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Refactoring and Consolidation
 *     
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

/**
 * The {@link IElementProperty} interface represents a model element property vertex
 * in the trace graph.
 * 
 * FIXME Saving the property value might allow incremental execution when the model has been unloaded and reloaded. That
 * way we can detect if the model changed (e.g. modified by another process). However, this has the problem that the
 * value has to be persisted in the used persistence method and general serialisation method would need to be devised.
 * 
 * @author Jonathan Co
 *
 */
public interface IElementProperty extends ITraceComponent { //, VertexFrame {

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

//	/**
//	 * Get the model element that owns this property.
//	 * 
//	 * @return The owning model element.
//	 */
//	IModelElementTrace getOwner();
//
//	/**
//	 * Set the model element that owns this property.
//	 *
//	 * @param element            The new model element that owns this property.
//	 * @return the i model element
//	 */
//	IModelElementTrace setOwner(IModelElementTrace element);

	/**
	 * Get the execution traces that access this property.
	 *
	 * @return An {@link Iterable} containing all the relevant execution traces.
	 */
	Iterable<IExecutionTrace> getExecutionTraces();

	/**
	 * Add a execution trace that accesses this property.
	 * 
	 * @param executionTrace
	 *            The execution trace to add.
	 * @return the original execution trace parameter but linked to this property.
	 */
	IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace);

	/**
	 * Remove a execution trace that accesses this property.
	 * 
	 * @param executionTrace
	 *            The execution trace to remove.
	 */
	void removeExecutionTrace(IExecutionTrace executionTrace);

}
