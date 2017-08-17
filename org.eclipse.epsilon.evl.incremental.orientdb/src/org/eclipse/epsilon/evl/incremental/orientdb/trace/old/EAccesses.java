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

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

// TODO: Auto-generated Javadoc
/**
 * The {@link EAccesses} interface represents an edge between a scope and a
 * single property accessed by that scope in the trace graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface EAccesses extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "accesses";

	/**
	 * Gets the scope.
	 *
	 * @return The scope that is accessing the property at
	 *         {@link #getProperty()}.
	 */
	@InVertex
	NExecutionTrace getExecutionTrace();

	/**
	 * Gets the property.
	 *
	 * @return The property that has been accessed at {@link #getExecutionTrace()}.
	 */
	@OutVertex
	NElementProperty getProperty();
	
}
