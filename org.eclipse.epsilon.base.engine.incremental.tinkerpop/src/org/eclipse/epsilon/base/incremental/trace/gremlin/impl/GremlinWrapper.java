package org.eclipse.epsilon.base.incremental.trace.gremlin.impl;

import org.apache.tinkerpop.gremlin.structure.Graph;

public interface GremlinWrapper<E> {

	E delegate();

	void delegate(E delegate);

	Graph graph();

	void graph(Graph graph);

}
