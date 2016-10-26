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
package org.eclipse.epsilon.eol.incremental.trace;

// TODO: Auto-generated Javadoc
/**
 * The Interface IContextTrace.
 */
public interface IContextTrace extends TraceComponent { //, VertexFrame {

	/**
  * Gets the name.
  *
  * @return The name of this context
  */
	String getName();

	/**
	 * Set the name of this context.
	 *
	 * @param name            The new name of this context
	 */
	void setName(String name);

	/**
	 * Gets the constraints.
	 *
	 * @return the constraints
	 */
	Iterable<IConstraintTrace> getConstraints();

	/**
	 * Adds the constraint.
	 *
	 * @param constraint the constraint
	 * @return the i constraint trace
	 */
	IConstraintTrace addConstraint(IConstraintTrace constraint);

	/**
	 * Removes the constraint.
	 *
	 * @param constraint the constraint
	 */
	void removeConstraint(IConstraintTrace constraint);
}
