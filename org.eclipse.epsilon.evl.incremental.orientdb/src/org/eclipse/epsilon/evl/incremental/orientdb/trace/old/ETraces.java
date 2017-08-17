/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Generalization
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.old;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link ETraces} interface represents an edge between an execution trace and the
 * module element related to the trace.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
public interface ETraces extends TraceComponent, EdgeFrame {

	/** Common name of this trace element. */
	String TRACE_TYPE = "traces";

	/**
	 * Gets the execution trace.
	 *
	 * @return The execution trace that is created for the execution of the module element at
	 *         {@link #getModuleElement()}.
	 */
	@InVertex
	NExecutionTrace getExecutionTrace();

	/**
	 * Gets the module element.
	 *
	 * @return The module element that traced by the execution trace at
	 *         {@link #getExecutionTrace()}.
	 */
	@OutVertex
	NModuleElement getModuleElement();

}
