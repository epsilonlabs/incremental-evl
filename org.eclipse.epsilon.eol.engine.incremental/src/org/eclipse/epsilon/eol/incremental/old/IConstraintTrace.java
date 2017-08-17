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
package org.eclipse.epsilon.eol.incremental.old;

// TODO: Auto-generated Javadoc
/**
 * The {@link IConstraintTrace} interface represents a constraint vertex in the trace
 * graph.
 * 
 * @author Jonathan Co
 *
 */
// TODO: Split name and add in a TClass or TContext vertex
@Deprecated
public interface IConstraintTrace extends ITraceComponent {

	/**
	 * Gets the name.
	 *
	 * @return The name of this constraint
	 */
	String getName();

	/**
	 * Set the name of this constraint.
	 *
	 * @param name            The new name of this constraint
	 */
	void setName(String name);

	/**
	 * Get the scopes that are evaluated by this constraint.
	 * 
	 * @return An {@link Iterable} containing all the relevant scopes.
	 */
	Iterable<IExecutionTrace> getScopes();

	/**
	 * Add a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to add.
	 * @return the original scope parameter but linked to this constraint.
	 */
	IExecutionTrace addScope(IExecutionTrace scope);

	/**
	 * Remove a scope that is evaluated by this constraint.
	 * 
	 * @param scope
	 *            The scope to remove.
	 */
	void removeScope(IExecutionTrace scope);
	
	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	IContextTrace getContext();
	
	/**
	 * Adds the context.
	 *
	 * @param context the context
	 * @return the i context trace
	 */
	IContextTrace addContext(IContextTrace context);

}
