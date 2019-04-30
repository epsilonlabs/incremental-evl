package org.eclipse.epsilon.base.incremental.trace.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

/**
 * A class that allows Trace model implementations to delegate to Tinkerpop graph elements.
 * @author Horacio Hoyos Rodriguez
 *
 * @param <E>	The Specific Tinkerpop element type
 */
public interface TinkerpopDelegate<E> {

	E delegate();

	GraphTraversalSource graphTraversalSource();

}
