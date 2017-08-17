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
 * The {@link NModuleElement} interface represents a module element vertex in the trace
 * graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface NModuleElement extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "module_element";

	/** Key to the 'name' property of this module element. */
	String ID = "id";

	/**
	 * Gets the name of the module element (constraint, rule, pattern, etc.)
	 *
	 * @return The name
	 */
	@Property(ID)
	String getId();

	/**
	 * Set the name of this module element.
	 *
	 * @param name            The new name of this module element
	 */
	@Property(ID)
	void setId(String id);

	/**
	 * Get the execution traces that trace this module element.
	 * 
	 * @return An {@link Iterable} containing all the relevant execution traces.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.OUT)
	Iterable<NExecutionTrace> getExecutionTraces();

	/**
	 * Add a execution trace that is evaluated by this module element.
	 * 
	 * @param executionTrace
	 *            The execution trace to add.
	 * @return the original execution trace parameter but linked to this module element.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.OUT)
	NExecutionTrace addExecutionTrace(NExecutionTrace executionTrace);

	/**
	 * Remove a execution trace that is evaluated by this module element.
	 * 
	 * @param executionTrace
	 *            The execution trace to remove.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.OUT)
	void removeExecutionTrace(NExecutionTrace executionTrace);


}
