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
 * The {@link NElementProperty} interface represents a model element property vertex
 * in the trace graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface NElementProperty extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "property";

	/** Key to the 'name' property of this property. */
	String NAME = "name";

	/**
	 * Gets the name.
	 *
	 * @return The name of this property
	 */
	@Property(NElementProperty.NAME)
	String getName();

	/**
	 * Set the name of this property.
	 *
	 * @param name            The new name of this property
	 */
	@Property(NElementProperty.NAME)
	void setName(String name);

//	/**
//	 * Get the model element that owns this property.
//	 * 
//	 * @return The owning model element.
//	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.IN)
//	TModelElement getOwner();
//
//	/**
//	 * Set the model element that owns this property.
//	 *
//	 * @param element            The new model element that owns this property.
//	 * @return the t element
//	 */
//	@Adjacency(label = TOwns.TRACE_TYPE, direction = Direction.IN)
//	TModelElement setOwner(TModelElement element);

	/**
	 * Get the execution traces that access this property.
	 *
	 * @return An {@link Iterable} containing all the relevant execution traces .
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	Iterable<NExecutionTrace> getExecutionTraces();

	/**
	 * Add a execution trace that accesses this property.
	 * 
	 * @param execution trace
	 *            The execution trace to add.
	 * @return the original execution trace parameter but linked to this property.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	NExecutionTrace addExecutionTrace(NExecutionTrace executionTrace);

	/**
	 * Remove a execution trace that accesses this property.
	 * 
	 * @param executionTrace
	 *            The scope to remove.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	void removeExecutionTrace(NExecutionTrace executionTrace);

}
