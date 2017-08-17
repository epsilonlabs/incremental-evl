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

import java.util.List;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link NExecutionContext} interface represents an execution context vertex in the
 * trace graph.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface NExecutionContext extends TraceComponent, VertexFrame {
	
	/** Common name of this trace element. */
	String TRACE_TYPE = "execution_context";

	/** Key to the 'element_id' property of this context. */
	String SCRIPT_ID = "script_id";
	
	/** Key to the 'models_ids' property of this context. */
	String MODELS_IDS = "models_ids";

	/**
	 * Gets the script id.
	 *
	 * @return The id of this model element
	 */
	@Property(SCRIPT_ID)
	String getScriptId();

	/**
	 * Set the id of this model element.
	 *
	 * @param scriptId    The new id
	 */
	@Property(SCRIPT_ID)
	void setScriptId(String scriptId);
	
	
	@Property(MODELS_IDS)
	List<String> getModelsIds();
	
	@Property(MODELS_IDS)
	void setModelsIds(List<String> modelsIds);
	
	/**
	 * Get the execution traces that were created in this context.
	 * 
	 * @return An {@link Iterable} containing all the relevant execution traces.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	Iterable<NExecutionTrace> getExecutionTraces();

	/**
	 * Add an execution trace that was created in this context
	 * 
	 * @param executionTrace The execution trace  to add.
	 * @return the original execution trace  parameter but linked to this element.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	NExecutionTrace addExecutionTrace(NExecutionTrace executionTrace);

	/**
	 * Remove an execution trace that was created in this context.
	 * 
	 * @param executionTrace The execution trace to remove.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	void removeExecutionTrace(NExecutionTrace executionTrace);


	
	
}
