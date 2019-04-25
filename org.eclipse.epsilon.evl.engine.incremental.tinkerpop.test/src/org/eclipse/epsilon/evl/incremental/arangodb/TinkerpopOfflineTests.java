package org.eclipse.epsilon.evl.incremental.arangodb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.OfflineTests;
import org.eclipse.epsilon.evl.incremental.BitsyGraphResource;
import org.eclipse.epsilon.evl.incremental.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlTinkerpopGuiceModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class TinkerpopOfflineTests extends OfflineTests<IncrementalEvlTinkerpopGuiceModule> {

	@Rule public TestName testName = new TestName();
	
	private BitsyGraphResource gr;

	private IncrementalEvlModule module;

	private File evlFile;

	private File tempModel;
	

	@Before
	public void setup() throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		Path dbPath = Paths.get("/Users/horacio/bitsy/", testName.getMethodName(), "/");
		gr = new BitsyGraphResource(dbPath, true);
		IncrementalEvlTinkerpopGuiceModule gModule = new IncrementalEvlTinkerpopGuiceModule(gr.getTraversalSource());
		Injector injector = Guice.createInjector(gModule);		
		module = injector.getInstance(IncrementalEvlModule.class);
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
		tempModel = File.createTempFile("temp-model", ".tmp");
	}


	
	@After
	public void teardown() throws Exception {
		gr.close();
		
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

	@Override
	protected IEvlModuleIncremental module() {
		return module;
	}

	@Override
	protected File evlFile() {
		return evlFile;
	}

	@Override
	protected File tempModel() {
		return tempModel;
	}
	


}
