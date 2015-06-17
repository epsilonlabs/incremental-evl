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
		if (graph.getVertexType(TConstraint.TRACE_TYPE) == null)
			graph.createVertexType(TConstraint.TRACE_TYPE);
		
		if (graph.getVertexType(TElement.TRACE_TYPE) == null)
			graph.createVertexType(TElement.TRACE_TYPE);
		
		if (graph.getVertexType(TProperty.TRACE_TYPE) == null)
			graph.createVertexType(TProperty.TRACE_TYPE);
		
		if (graph.getVertexType(TScope.TRACE_TYPE) == null)
			graph.createVertexType(TScope.TRACE_TYPE);

		// Edges
		if (graph.getEdgeType(TEvaluates.TRACE_TYPE) == null)
		graph.createEdgeType(TEvaluates.TRACE_TYPE);
		
		if (graph.getEdgeType(TOwns.TRACE_TYPE) == null)
		graph.createEdgeType(TOwns.TRACE_TYPE);

		if (graph.getEdgeType(TRootOf.TRACE_TYPE) == null)
		graph.createEdgeType(TRootOf.TRACE_TYPE);
		
		if (graph.getEdgeType(TAccesses.TRACE_TYPE) == null)
		graph.createEdgeType(TAccesses.TRACE_TYPE);

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

}
