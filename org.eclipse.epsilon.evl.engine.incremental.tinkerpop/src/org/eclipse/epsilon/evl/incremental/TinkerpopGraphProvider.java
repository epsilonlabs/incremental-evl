package org.eclipse.epsilon.evl.incremental;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.common.util.StringProperties;

public interface TinkerpopGraphProvider extends AutoCloseable {
	
	
	default void mergeSettings(Configuration config, StringProperties properties) {
		for (Object k : properties.keySet()) {
			config.addProperty((String) k, properties.get(k));
		}
	}
	
	public GraphTraversalSource getTraversalSource();

}
