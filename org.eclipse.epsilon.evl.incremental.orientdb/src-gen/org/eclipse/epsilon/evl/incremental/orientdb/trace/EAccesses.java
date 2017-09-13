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
 ** accesses OrientDB Edge Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The EAccesses interface represents an edge
 * between a vertex of type Trace and a vertex of type
 * Property.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface EAccesses extends EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "accesses";
	
	/**
	 * Gets the Trace.
	 *
	 * @return The Trace that is accessing the property at
	 * {@link #getProperty()}.
	 */
	@OutVertex
	VTrace getTrace();

	/**
	 * Gets the Property.
	 *
	 * @return The Property that has been accessed at
	 * {@link #getTrace()}.
	 */
	@InVertex
	VProperty getProperty();
	
}