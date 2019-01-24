package org.eclipse.epsilon.evl.incremental.arangodb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.OfflineTests;
import org.eclipse.epsilon.evl.incremental.ArangoDBGuiceModule;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.incremental.util.ArangoDBEvlUtil;
import org.junit.After;
import org.junit.Before;
import org.slf4j.LoggerFactory;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoGraph;
import com.arangodb.tinkerpop.gremlin.structure.ArangoDBGraph;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class ArangoDBOfflineTests extends OfflineTests<ArangoDBGuiceModule> {

	@Override
	public ArangoDBGuiceModule getEvlGuiceModule() {
		return new ArangoDBGuiceModule();
	}
	
	@Before
	public void setup() throws Exception {
		super.setup();
		
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);
		
		IEvlModuleTraceRepository tr = module.getContext().getTraceManager().getExecutionTraceRepository();
		// We need to provide an Interface and implementations to do this so we can provide different
		// gremlin backends in the DT
		Configuration conf = ArangoDBEvlUtil.getBaseConfiguration();
		// This should be added based on the DT config, for example.
		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + "." + ArangoDBGraph.PROPERTY_KEY_DB_NAME, "tinkerpop");
		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + ".arangodb.user", "gremlin");
		conf.addProperty(ArangoDBGraph.PROPERTY_KEY_PREFIX + ".arangodb.password", "gremlin");
		Graph graph = GraphFactory.open(conf);
		GraphTraversalSource gts = new GraphTraversalSource(graph);
		((EvlModuleTraceGremlinRepositoryImpl)tr).setGraphTraversalSource(gts);
		IModelTraceRepository mtr = module.getContext().getTraceManager().getModelTraceRepository();
		((ModelTraceGremlinRepositoryImpl)mtr).setGraphTraversalSource(gts);
	}
	
	@After
	public void teardown() throws Exception {
		ArangoDB driver = null;
		Properties properties = new Properties();
		
		properties.put(ArangoDBGraph.PROPERTY_KEY_DB_NAME, "tinkerpop");
		properties.put("arangodb.user", "gremlin");
		properties.put("arangodb.password", "gremlin");
		
		InputStream targetStream = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			properties.store(os, null);
			targetStream = new ByteArrayInputStream(os.toByteArray());
			driver = new ArangoDB.Builder().loadProperties(targetStream)
					//.registerModule(new ArangoDBGraphModule())
					.build();
		} catch (IOException e) {
			
		} finally {
			os.close();
			targetStream.close();
		}
		if (driver != null) {
			ArangoDatabase db = driver.db("tinkerpop");
			ArangoGraph graph = db.graph("IncrementalEvl");
			if (graph.exists()) {
				Collection<String> edgeDefinitions = graph.getEdgeDefinitions();
				Collection<String> vertexCollections =graph.getVertexCollections();
				// Drop graph first because it will break if the graph collections do not exist
				graph.drop();
				for (String definitionName : edgeDefinitions) {
					String collectioName = definitionName;
					if (db.collection(collectioName).exists()) {
						db.collection(collectioName).drop();
					}
				}
				for (String vc : vertexCollections) {
					String collectioName = vc;
					if (db.collection(collectioName).exists()) {
						db.collection(collectioName).drop();
					}
				}
			}
			driver.shutdown();
		}	
	}
	// Export Graph
	// arangoexport --type xgmml --graph-name IncrementalEvl --server.username gremlin --server.password gremlin --server.database tinkerpop
	


}
