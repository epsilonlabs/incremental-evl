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
 * The {@link TAccesses} interface represents an edge between a scope and a
 * single property accessed by that scope in the trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TAccesses extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "accesses";

	/**
	 * Gets the scope.
	 *
	 * @return The scope that is accessing the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	TScope getScope();

	/**
	 * Gets the property.
	 *
	 * @return The property that has been accessed at {@link #getScope()}.
	 */
	@InVertex
	TProperty getProperty();
	
}
