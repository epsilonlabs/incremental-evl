package org.eclipse.epsilon.eol.incremental.models;

import com.tinkerpop.gremlin.java.GremlinPipeline;

public interface TinkerpopSpecification<V, E> extends Specification {
	
	GremlinPipeline<V, V> toGremlinPipeline();
}