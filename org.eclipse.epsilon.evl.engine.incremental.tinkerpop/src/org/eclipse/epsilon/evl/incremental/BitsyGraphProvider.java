package org.eclipse.epsilon.evl.incremental;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Graph;

import com.lambdazen.bitsy.BitsyGraph;

public class BitsyGraphProvider implements TinkerpopGraphProvider {


	@Override
	public Configuration getBaseConfiguration() {
		final Configuration config = new BaseConfiguration();
		config.setProperty(Graph.GRAPH, BitsyGraph.class.getName());
		config.setProperty(BitsyGraph.CREATE_DIR_IF_MISSING_KEY, true);
		return config;
	}

}
