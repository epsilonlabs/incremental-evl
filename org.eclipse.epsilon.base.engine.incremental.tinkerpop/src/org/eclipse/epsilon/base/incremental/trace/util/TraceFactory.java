package org.eclipse.epsilon.base.incremental.trace.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;

public interface TraceFactory {

	Object createTraceElement(Element delegate, GraphTraversalSource gts);

}