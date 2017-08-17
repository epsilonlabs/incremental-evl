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
 * The {@link NExecutionTrace} interface represents an execution trace vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface NExecutionTrace extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "execution_trace";
	
	/** The result. */
//	String RESULT = "result";  Boolean results only make sense in EVL

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
//	@Property(RESULT)
//	void setResult(boolean result);
	
	/**
	 * Gets the result.
	 *
	 * @param result the result
	 * @return the result
	 */
//	@Property(RESULT)
//	boolean getResult(boolean result);
	
	/**
	 * Get the module element that is traced by this execution trace.
	 * 
	 * @return The module element.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.IN)
	NModuleElement getModuleElement();

	/**
	 * Set the module element that is traced by this execution trace.
	 * 
	 * @param moduleElement the new module element.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.IN)
	void setModuleElement(NModuleElement moduleElement);

	/**
	 * Get the model elements that are reached by this execution trace.
	 *
	 * @return The model element.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	Iterable<NModelElement> getModelElements();

	/**
	 * Add a model element that is reached by this execution trace.
	 *
	 * @param element            the new model element
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	NModelElement addModelElement(NModelElement element);
	
	/**
	 * Remove a model element that is reached by this execution trace.
	 *
	 * @param element            the new model element
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	void removeModelElement(NModelElement element);
	
	/**
	 * Get the properties accessed by execution trace.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	Iterable<NElementProperty> getProperties();

	/**
	 * Add a property that is accessed execution trace.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this scope.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	NElementProperty addProperty(NElementProperty property);

	/**
	 * Remove a property that is accessed by this execution trace.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	void removeProperty(NElementProperty property);
	
	/**
	 * Gets the execution context in which this trace is valid.
	 *
	 * @return the context
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.OUT)
	NExecutionContext getExecutionContext();
	
	/**
	 * Set the execution context in which this trace is valid.
	 *
	 * @param context the context
	 * @return the t context
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.OUT)
	NExecutionContext setExecutionContext(NExecutionContext context);

}
