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
/*******************************************************************************
 ** contains OrientDB Edge Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The EContains interface represents an edge
 * between a vertex of type ExecutionContext and a vertex of type
 * Trace.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface EContains extends EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "contains";
	
	/**
	 * Gets the ExecutionContext.
	 *
	 * @return The ExecutionContext that is accessing the property at
	 * {@link #getTrace()}.
	 */
	@OutVertex
	VExecutionContext getExecutionContext();

	/**
	 * Gets the Trace.
	 *
	 * @return The Trace that has been accessed at
	 * {@link #getExecutionContext()}.
	 */
	@InVertex
	VTrace getTrace();
	
}