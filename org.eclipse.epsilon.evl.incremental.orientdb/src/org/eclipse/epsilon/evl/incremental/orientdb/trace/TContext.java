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
 * The Interface TContext.
 */
public interface TContext extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "context";

	/** Key to the 'name' property of this constraint. */
	String NAME = "name";

	/**
	 * Gets the name.
	 *
	 * @return The name of this context
	 */
	@Property(NAME)
	String getName();

	/**
	 * Set the name of this context.
	 *
	 * @param name            The new name of this context
	 */
	@Property(NAME)
	void setName(String name);

	/**
	 * Gets the constraints.
	 *
	 * @return the constraints
	 */
	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	Iterable<TConstraint> getConstraints();

	/**
	 * Adds the constraint.
	 *
	 * @param constraint the constraint
	 * @return the t constraint
	 */
	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	TConstraint addConstraint(TConstraint constraint);

	/**
	 * Removes the constraint.
	 *
	 * @param constraint the constraint
	 */
	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.IN)
	void removeConstraint(TConstraint constraint);
}
