package org.eclipse.epsilon.evl.incremental.arangodb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlTinkerpopGuiceModule;
import org.junit.Before;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lambdazen.bitsy.BitsyGraph;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class TinkerpopExecutionTests extends ExecutionTests {

	private File evlFile;
	private IncrementalEvlModule module;
	
	@Before
	public void setup() throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);
		Path dbPath = Paths.get("/Users/horacio/bitsy");
		if (dbPath.toFile().exists()) {
			// Delete previous graph if exists
			Arrays.stream(dbPath.toFile().listFiles()).forEach(f -> f.delete());
		}
		else {
			dbPath.toFile().mkdir();
		}
        BitsyGraph graph = new BitsyGraph(dbPath);
		GraphTraversalSource gts = new GraphTraversalSource(graph);
		IncrementalEvlTinkerpopGuiceModule gModule = new IncrementalEvlTinkerpopGuiceModule();
		gModule.bindGraphTraversalSourceInstance(gts);
		Injector injector = Guice.createInjector(gModule);
		module = injector.getInstance(IncrementalEvlModule.class);
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
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
	
		
	}

	@Override
	public File evlFile() {
		return evlFile;
	}

	@Override
	public IncrementalEvlModule module() {
		return module;
	}

}
