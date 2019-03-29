package org.eclipse.epsilon.evl.incremental.arangodb;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import com.lambdazen.bitsy.BitsyGraph;


import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlTinkerpopGuiceModule;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;

import org.junit.Before;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class TinkerpopExecutionTests extends ExecutionTests<IncrementalEvlTinkerpopGuiceModule> {

	@Override
	public IncrementalEvlTinkerpopGuiceModule getEvlGuiceModule() {
		return new IncrementalEvlTinkerpopGuiceModule();
	}
	// Export Graph
	// arangoexport --type xgmml --graph-name IncrementalEvl --server.username gremlin --server.password gremlin --server.database tinkerpop
	
	@Before
	public void setup() throws Exception {
		super.setup();
		
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);
		
		IEvlModuleTraceRepository tr = module.getContext().getTraceManager().getExecutionTraceRepository();
		// We need to provide an Interface and implementations to do this so we can provide different
		// gremlin backends in the DT
		
//		Configuration conf = ArangoDBEvlUtil.getBaseConfiguration();
//		// This should be added based on the DT config, for example.
//		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + "." + ArangoDBGraph.PROPERTY_KEY_DB_NAME, "tinkerpop");
//		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + ".arangodb.user", "gremlin");
//		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + ".arangodb.password", "gremlin");
//		PropertiesConfiguration pconf = new PropertiesConfiguration();
//		pconf.append(conf);
//		pconf.save("EvlGraph.properties");
//		// For gremlin use
		// import org.apache.commons.configuration.PropertiesConfiguration
		// c = new org.apache.commons.configuration.PropertiesConfiguration("/Users/horacio/Documents/incrementalws/org.eclipse.epsilon.evl.engine.incremental.tinkerpop.test/EvlGraph.properties")
		// g = ArangoDBGraph.open(c)
		// Graph graph = GraphFactory.open(conf);
	
		// FIXME The default incremental configuration asks for a path
		Path dbPath = Paths.get("/Users/horacio/bitsy");
        BitsyGraph graph = new BitsyGraph(dbPath);
		GraphTraversalSource gts = new GraphTraversalSource(graph);
		((EvlModuleTraceGremlinRepositoryImpl)tr).setGraphTraversalSource(gts);
		IModelTraceRepository mtr = module.getContext().getTraceManager().getModelTraceRepository();
		((ModelTraceGremlinRepositoryImpl)mtr).setGraphTraversalSource(gts);
	}

}
