package org.eclipse.epsilon.evl.incremental.arangodb;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.evl.OfflineTests;
import org.eclipse.epsilon.evl.incremental.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlTinkerpopGuiceModule;
import org.eclipse.epsilon.evl.incremental.TinkerpopGraphProvider;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lambdazen.bitsy.BitsyGraph;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class TinkerpopOfflineTests extends OfflineTests<IncrementalEvlTinkerpopGuiceModule> {

	@Rule public TestName testName = new TestName();
	
	private BitsyGraph graph;

	
	@Before
	public void setup() throws Exception {
		Injector injector = Guice.createInjector(new IncrementalEvlTinkerpopGuiceModule());
		module = (IncrementalEvlModule) injector.getInstance(IEvlModuleIncremental.class);
		
		super.setup();
		
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		
		IEvlModuleTraceRepository tr = module.getContext().getTraceManager().getExecutionTraceRepository();
		
		Path dbPath = Paths.get("/Users/horacio/bitsy/", testName.getMethodName(), "/");
		if (dbPath.toFile().exists()) {
			// Delete previous graph if exists
			Arrays.stream(dbPath.toFile().listFiles()).forEach(f -> f.delete());
		}
		else {
			dbPath.toFile().mkdir();
		}
		TinkerpopGraphProvider gProvider = injector.getInstance(TinkerpopGraphProvider.class);
		StringProperties properties = new StringProperties();
		properties.setProperty(BitsyGraph.DB_PATH_KEY, dbPath.toString());
		Configuration config = gProvider.getBaseConfiguration();
		gProvider.mergeSettings(config, properties);
		//graph = new BitsyGraph(dbPath);
		graph = gProvider.openGraph(config);
        
		GraphTraversalSource gts = new GraphTraversalSource(graph);
		((EvlModuleTraceGremlinRepositoryImpl)tr).setGraphTraversalSource(gts);
		IModelTraceRepository mtr = module.getContext().getTraceManager().getModelTraceRepository();
		((ModelTraceGremlinRepositoryImpl)mtr).setGraphTraversalSource(gts);
	}
	
	@After
	public void teardown() throws Exception {
		super.teardown();
		graph.shutdown();
		
//		ArangoDB driver = null;
//		Properties properties = new Properties();
//		
//		properties.put(ArangoDBGraph.PROPERTY_KEY_DB_NAME, "tinkerpop");
//		properties.put("arangodb.user", "gremlin");
//		properties.put("arangodb.password", "gremlin");
//		
//		InputStream targetStream = null;
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		try {
//			properties.store(os, null);
//			targetStream = new ByteArrayInputStream(os.toByteArray());
//			driver = new ArangoDB.Builder().loadProperties(targetStream)
//					//.registerModule(new ArangoDBGraphModule())
//					.build();
//		} catch (IOException e) {
//			
//		} finally {
//			os.close();
//			targetStream.close();
//		}
//		if (driver != null) {
//			ArangoDatabase db = driver.db("tinkerpop");
//			ArangoGraph graph = db.graph("IncrementalEvl");
//			if (graph.exists()) {
//				Collection<String> edgeDefinitions = graph.getEdgeDefinitions();
//				Collection<String> vertexCollections =graph.getVertexCollections();
//				// Drop graph first because it will break if the graph collections do not exist
//				graph.drop();
//				for (String definitionName : edgeDefinitions) {
//					String collectioName = definitionName;
//					if (db.collection(collectioName).exists()) {
//						db.collection(collectioName).drop();
//					}
//				}
//				for (String vc : vertexCollections) {
//					String collectioName = vc;
//					if (db.collection(collectioName).exists()) {
//						db.collection(collectioName).drop();
//					}
//				}
//			}
//			driver.shutdown();
//		}	
	}
	// Export Graph
	// arangoexport --type xgmml --graph-name IncrementalEvl --server.username gremlin --server.password gremlin --server.database tinkerpop
	


}
