package org.eclipse.epsilon.base.incremental.trace.gremlin.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

public interface GremlinWrapper<E> {

	E delegate();

	void delegate(E delegate);


	void graphTraversalSource(GraphTraversalSource gts);

}
