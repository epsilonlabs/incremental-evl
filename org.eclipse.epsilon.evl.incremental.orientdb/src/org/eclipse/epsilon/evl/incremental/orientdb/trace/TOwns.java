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
 * The {@link TOwns} interface represents an edge between an element and a
 * property owned by that element.
 * 
 * @author Jonathan Co
 *
 */
public interface TOwns extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "owns";

	/**
	 * Gets the property.
	 *
	 * @return The property that is owned by the element at
	 *         {@link #getElement()} .
	 */
	@InVertex
	TProperty getProperty();

	/**
	 * Gets the element.
	 *
	 * @return The element that is owned by the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	TElement getElement();

}
