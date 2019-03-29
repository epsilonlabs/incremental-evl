package org.eclipse.epsilon.evl.incremental;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.eclipse.epsilon.common.util.StringProperties;

public interface TinkerpopGraphProvider {
	
	/**
	 * Get the graph Configuration with base values for EVL. Per-run values (like db path,
	 * credentials, etc.) can be added afterwards or merged with RunConfigurations settings via
	 * 
	 * @return
	 */
	Configuration getBaseConfiguration();
	
	default void mergeSettings(Configuration config, StringProperties properties) {
		for (Object k : properties.keySet()) {
			config.addProperty((String) k, properties.get(k));
		}
	}
	
	@SuppressWarnings("unchecked")
	default <T extends Graph> T openGraph(Configuration config) {
		return (T) GraphFactory.open(config);
	}

}
