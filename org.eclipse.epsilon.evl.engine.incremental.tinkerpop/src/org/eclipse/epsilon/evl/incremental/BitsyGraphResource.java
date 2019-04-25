/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
**********************************************************************/
package org.eclipse.epsilon.evl.incremental;

import java.nio.file.Path;
import java.util.Arrays;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.eclipse.epsilon.common.util.StringProperties;

import com.lambdazen.bitsy.BitsyGraph;

public class BitsyGraphResource implements TinkerpopGraphProvider {
	
	private final BitsyGraph graph;
	 
	/**
	 * Instantiates a new Bitsy graph provider.
	 *
	 * @param dbPth 				the path (folder) where the DB is located
	 * @param dltExstng 			If true, any existing DB in the desired location will be deleted
	 */
	public BitsyGraphResource(Path dbPth, boolean dltExstng) {
		super();
		if (dltExstng) {
			if (dbPth.toFile().exists()) {
				// Delete previous graph if exists
				Arrays.stream(dbPth.toFile().listFiles()).forEach(f -> f.delete());
			}
		}
		dbPth.toFile().mkdirs();
		StringProperties properties = new StringProperties();
		properties.setProperty(BitsyGraph.DB_PATH_KEY, dbPth.toString());
		Configuration config = getBaseConfiguration();
		mergeSettings(config, properties);
		graph = (BitsyGraph) GraphFactory.open(config);
	}

	private Configuration getBaseConfiguration() {
		final Configuration config = new BaseConfiguration();
		config.setProperty(Graph.GRAPH, BitsyGraph.class.getName());
		config.setProperty(BitsyGraph.CREATE_DIR_IF_MISSING_KEY, true);
		return config;
	}

	@Override
	public void close() throws Exception {
		graph.close();
	}

	@Override
	public GraphTraversalSource getTraversalSource() {
		return graph.traversal();
	}
	


}
