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
 * The Interface TIn.
 */
public interface TIn extends TraceComponent, EdgeFrame {
	
	/** The trace type. */
	String TRACE_TYPE = "in";

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	@InVertex
	TContext getContext();
	
	/**
	 * Gets the constraint.
	 *
	 * @return the constraint
	 */
	@OutVertex
	TConstraint getConstraint();
}
