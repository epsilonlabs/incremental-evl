/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.old;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The Interface TFor represents an edge between an execution trace and the
 * execution context in which the trace was created.
 * 
 * @author Horacio Hoyos Rodriguez
 */
public interface EFor extends TraceComponent, EdgeFrame {
	
	/** The trace type. */
	String TRACE_TYPE = "for";

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	@InVertex
	NExecutionContext getExecutionContext();
	
	/**
	 * Gets the constraint.
	 *
	 * @return the constraint
	 */
	@OutVertex
	NModuleElement getModuleElement();
}
