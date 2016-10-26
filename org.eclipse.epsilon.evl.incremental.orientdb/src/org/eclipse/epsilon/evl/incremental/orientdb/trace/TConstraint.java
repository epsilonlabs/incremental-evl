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
 * The {@link TConstraint} interface represents a constraint vertex in the trace
 * graph.
 * 
 * @author Jonathan Co
 *
 */
// TODO: Split name and add in a TClass or TContext vertex
public interface TConstraint extends TraceComponent, VertexFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "constraint";

	/** Key to the 'name' property of this constraint. */
	String NAME = "name";

	/**
	 * Gets the name.
	 *
	 * @return The name of this constraint
	 */
	@Property(NAME)
	String getName();

	/**
	 * Set the name of this constraint.
	 *
	 * @param name            The new name of this constraint
	 */
	@Property(NAME)
	void setName(String name);

	/**
	 * Get the scopes that are evaluated by this constraint.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	Iterable<TScope> getScopes();

	/**
	 * Add a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	TScope addScope(TScope scope);

	/**
	 * Remove a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.OUT)
	void removeScope(TScope scope);
	
	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.OUT)
	TContext getContext();
	
	/**
	 * Adds the context.
	 *
	 * @param context the context
	 * @return the t context
	 */
	@Adjacency(label = TIn.TRACE_TYPE, direction = Direction.OUT)
	TContext addContext(TContext context);

}
