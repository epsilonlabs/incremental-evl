/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.old;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;


/**
 * The {@link NModelElement} interface represents a model element vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface NModelElement extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "model_element";

	/** Key to the 'element_id' property of this constraint. */
	String ID = "id";

	/**
	 * Gets the element id.
	 *
	 * @return The id of this model element
	 */
	@Property(ID)
	String getElementId();

	/**
	 * Set the id of this model element.
	 *
	 * @param elementId            The new id
	 */
	@Property(ID)
	void setElementId(String elementId);

	/**
	 * Get the execution traces that reach this model element.
	 * 
	 * @return An {@link Iterable} containing all the relevant execution traces.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	Iterable<NExecutionTrace> getExecutionTraces();

	/**
	 * Add an execution trace that reaches this model element.
	 * 
	 * @param executionTrace
	 *            The execution trace to add.
	 * @return the original execution trace parameter but linked to this element.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	NExecutionTrace addExecutionTrace(NExecutionTrace executionTrace);
	
	/**
	 * Remove an execution trace that reaches this model element.
	 *
	 * @param element            the new model element
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	void removeExecutionTrace(NExecutionTrace element);


//	/**
//	 * Get the properties that are owned by this model element.
//	 * 
//	 * @return An {@link Iterable} containing all the relevant properties.
//	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
//	Iterable<TProperty> getProperties();
//
//	/**
//	 * Add a property that is owned by this model element as its root.
//	 * 
//	 * @param property
//	 *            The property to add.
//	 * @return the original property parameter but linked to this element.
//	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
//	TProperty addProperty(TProperty property);
//
//	/**
//	 * Remove a property that is owned by this model element as its root.
//	 * 
//	 * @param property
//	 *            The property to remove.
//	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.OUT)
//	void removeProperty(TProperty property);

}
