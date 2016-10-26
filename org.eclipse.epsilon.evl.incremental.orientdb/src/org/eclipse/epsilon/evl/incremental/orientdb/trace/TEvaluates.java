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

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

// TODO: Auto-generated Javadoc
/**
 * The {@link TEvaluates} interface represents an edge between a scope and the
 * constraint used to evaluate it.
 * 
 * @author Jonathan Co
 *
 */
public interface TEvaluates extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "evaluates";

	/**
	 * Gets the scope.
	 *
	 * @return The scope that is evaluated by the constraint at
	 *         {@link #getConstraint()}.
	 */
	@InVertex
	TScope getScope();

	/**
	 * Gets the constraint.
	 *
	 * @return The constraint that is used to evaluate the scope at
	 *         {@link #getScope()}.
	 */
	@OutVertex
	TConstraint getConstraint();

}
