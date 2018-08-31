package incremental.tinkerpop.dependencies;

import java.util.Properties;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.arangodb.tinkerpop.gremlin.client.ArangoDBGraphClient;
import com.arangodb.tinkerpop.gremlin.structure.ArangoDBGraph;

public class GremlinTest {
	
	public static void main(String... args) {
		System.out.println("running");
		final Configuration conf = new BaseConfiguration();
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + "." + ArangoDBGraph.CONFIG_DB_NAME, "epsilon");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + "." + ArangoDBGraph.CONFIG_GRAPH_NAME, "iEVL");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".arangodb.user", "epsilon");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".arangodb.password", "epsilon");
		// TODO Create an Ecore to ArangoDB graph schema
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "EvlModuleTrace");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ContextTrace");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "InvariantTrace");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "elements");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "invariantContext");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "elements:EvlModulerace->ContextTrace");
		conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "invariantContext:ContextTrace->InvariantTrace");
		
		ArangoDBGraph arangoG = null;
		
		try {
			clear(arangoG, conf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
//			TinkerGraph tg = TinkerGraph.open();
//			GraphTraversalSource g = tg.traversal();
			arangoG = new ArangoDBGraph(conf);
			GraphTraversalSource g = arangoG.traversal();
			Vertex delegate = g.addV("EvlModuleTrace").property("uri", "some/path/to/elv").next();
			Vertex to = g.addV("ContextTrace").property("kind", "SomeType").property("index", 0).next();
			g.V(delegate).addE("elements").to(to).iterate();
			System.out.println(g);
			try {
				g.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				clear(arangoG, conf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void clear(Graph graph, Configuration configuration) throws Exception {
		ArangoDBGraphClient client;
		if (graph == null) {
			Configuration arangoConfig = configuration.subset(ArangoDBGraph.ARANGODB_CONFIG_PREFIX);
			Properties arangoProperties = ConfigurationConverter.getProperties(arangoConfig);
			client = new ArangoDBGraphClient(arangoProperties, "tinkerpop", 0);
			client.deleteGraph(arangoConfig.getString(ArangoDBGraph.CONFIG_GRAPH_NAME));
		}
		else {
			ArangoDBGraph agraph = (ArangoDBGraph) graph;
			client = agraph.getClient();
			client.clear(agraph);
			agraph.close();
		}
		
	}

}
