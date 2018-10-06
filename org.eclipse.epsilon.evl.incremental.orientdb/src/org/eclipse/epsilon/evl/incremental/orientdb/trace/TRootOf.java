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
 * The {@link TRootOf} interface represents an edge between a scope and its root
 * model element.
 * 
 * @author Jonathan Co
 *
 */
public interface TRootOf extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "root_of";

	/**
	 * Gets the scope.
	 *
	 * @return The scope that uses the model element at {@link #getProperty()}
	 *         as its root.
	 */
	@InVertex
	TScope getScope();

	/**
	 * Gets the element.
	 *
	 * @return The model element used as the root of the scope at
	 *         {@link #getScope()}.
	 */
	@OutVertex
	TElement getElement();

}
