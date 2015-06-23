package org.eclipse.epsilon.evl.trace;

import java.util.HashMap;
import java.util.Map;

import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * Implementation of {@link TraceGraphFactory} that uses Orient-DB as its
 * underlying database engine.
 * 
 * @author Jonathan Co
 *
 */
public class OrientTraceGraphFactory implements TraceGraphFactory<OrientGraph> {

	private final Map<String, String> config;
	private OrientTraceGraph graphInstance = null;

	/**
	 * Default Constructor
	 *
	 * @param url
	 * @param user
	 * @param pass
	 */
	public OrientTraceGraphFactory(String url, String user, String pass) {
		// Build configuration
		// TODO: Make priv-sfs
		this.config = new HashMap<String, String>(4);
		this.config.put("blueprints.graph",
				"com.tinkerpop.blueprints.impls.orient.OrientGraph");
		this.config.put("blueprints.orientdb.url", url);
		this.config.put("blueprints.orientdb.username", user);
		this.config.put("blueprints.orientdb.password", pass);
		this.config.put("log.console.level", "fine");
		this.config.put("log.file.level", "fine");
	}

	@Override
	public TraceGraph<OrientGraph> getGraph() {
		// Init the graph if not already done so
		if (graphInstance == null || graphInstance.isOpen()) {
			OrientGraph orientGraph = (OrientGraph) GraphFactory
					.open(this.config);
			this.setupClasses(orientGraph);
			this.graphInstance = new OrientTraceGraph(orientGraph);
		}
		return this.graphInstance;
	}

	/**
	 * Private utility - add vertex types to Orient backed graph.
	 *
	 * @param graph
	 *            The graph to add vertex types to
	 */
	private void setupClasses(OrientGraph graph) {
		// Vertices
		setupVertexType(graph, 
				TContext.TRACE_TYPE,
				TConstraint.TRACE_TYPE,
				TElement.TRACE_TYPE,
				TProperty.TRACE_TYPE,
				TScope.TRACE_TYPE);

		// Edges
		setupEdgeType(graph, 
				TEvaluates.TRACE_TYPE,
				TOwns.TRACE_TYPE,
				TRootOf.TRACE_TYPE,
				TAccesses.TRACE_TYPE,
				TIn.TRACE_TYPE);
		
		// Indexes

//		// Unique Constraint name Index
//		graph.createKeyIndex(TConstraint.NAME, Vertex.class,
//				new Parameter<String, String>("type", "UNIQUE"),
//				new Parameter<String, String>("class", TConstraint.TRACE_TYPE));
//
//		// Unique Element ID index
//		graph.createKeyIndex(TElement.ELEMENT_ID, Vertex.class,
//				new Parameter<String, String>("type", "UNIQUE"),
//				new Parameter<String, String>("class", TElement.TRACE_TYPE));
	}
	
	private void setupEdgeType(OrientGraph graph, String... types) {
		for (String type : types) {
			if (graph.getEdgeType(type) == null) graph.createEdgeType(type);
		}
	}
	
	private void setupVertexType(OrientGraph graph, String... types) {
		for (String type : types) {
			if (graph.getVertexType(type) == null) graph.createVertexType(type);
		}
	}

}
