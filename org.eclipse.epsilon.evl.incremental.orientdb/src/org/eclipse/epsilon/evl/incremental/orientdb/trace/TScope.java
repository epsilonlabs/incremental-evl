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

import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

// TODO: Auto-generated Javadoc
/**
 * The {@link TScope} interface represents a constraint scope vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TScope extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "scope";
	
	/** The result. */
	String RESULT = "result";

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	@Property(RESULT)
	void setResult(boolean result);
	
	/**
	 * Gets the result.
	 *
	 * @param result the result
	 * @return the result
	 */
	@Property(RESULT)
	boolean getResult(boolean result);
	
	/**
	 * Get the constraint that is used to evaluate this scope.
	 * 
	 * @return The constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.IN)
	TConstraint getConstraint();

	/**
	 * Set the constraint that is used to evaluate this scope.
	 * 
	 * @param constraint
	 *            the new constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.IN)
	void setConstraint(TConstraint constraint);

	/**
	 * Get the model element that is used as the root of this scope.
	 *
	 * @return The model element.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	TElement getRootElement();

	/**
	 * Set the model element that is used as the root of this scope.
	 *
	 * @param element            the new model element
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	void setRootElement(TElement element);

	/**
	 * Get the properties accessed by this scope.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	Iterable<TProperty> getProperties();

	/**
	 * Add a property that is accessed this scope.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this scope.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	TProperty addProperty(TProperty property);

	/**
	 * Remove a property that is accessed by this scope.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	void removeProperty(TProperty property);

}
