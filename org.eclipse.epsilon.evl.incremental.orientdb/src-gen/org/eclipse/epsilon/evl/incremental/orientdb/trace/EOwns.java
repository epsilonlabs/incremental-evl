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
 ** owns OrientDB Edge Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The EOwns interface represents an edge
 * between a vertex of type ModelElement and a vertex of type
 * Property.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface EOwns extends EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "owns";
	
	/**
	 * Gets the ModelElement.
	 *
	 * @return The ModelElement that is accessing the property at
	 * {@link #getProperty()}.
	 */
	@OutVertex
	VModelElement getModelElement();

	/**
	 * Gets the Property.
	 *
	 * @return The Property that has been accessed at
	 * {@link #getModelElement()}.
	 */
	@InVertex
	VProperty getProperty();
	
}