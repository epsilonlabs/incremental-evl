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

/**
 * The {@link EReaches} interface represents an edge between a scope and its root
 * model element.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface EReaches extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "reaches";

	/**
	 * Gets the scope.
	 *
	 * @return The execution trace that reaches the model element at {@link #getModelElement()}.
	 *         
	 */
	@InVertex
	NExecutionTrace getExecutionTrace();

	/**
	 * Gets the element.
	 *
	 * @return The model element that is reached by the execution trace at
	 *         {@link #getExecutionTrace()}.
	 */
	@OutVertex
	NModelElement getModelElement();

}
