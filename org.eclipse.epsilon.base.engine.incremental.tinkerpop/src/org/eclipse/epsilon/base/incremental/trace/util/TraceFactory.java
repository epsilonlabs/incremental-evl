/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
**********************************************************************/
package org.eclipse.epsilon.base.incremental.trace.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public interface TraceFactory {

	/**
	 * Creates a new Trace object that wraps the provided vertex. The type of the object is 
	 * determined by the vertex's label
	 *
	 * @param delegate the delegate
	 * @param gts the gts
	 * @return the object
	 */
	Object createTraceElement(Vertex delegate, GraphTraversalSource gts);

}