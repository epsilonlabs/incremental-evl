package org.eclipse.epsilon.evl.trace;

import java.util.HashMap;
import java.util.Map;

import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
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
		graph.createVertexType(TConstraint.TRACE_TYPE);
		graph.createVertexType(TElement.TRACE_TYPE);
		graph.createVertexType(TProperty.TRACE_TYPE);
		graph.createVertexType(TScope.TRACE_TYPE);

		// Edges
		graph.createEdgeType(TEvaluates.TRACE_TYPE);
		graph.createEdgeType(TOwns.TRACE_TYPE);
		graph.createEdgeType(TRootOf.TRACE_TYPE);
		graph.createEdgeType(TAccesses.TRACE_TYPE);

		// Indexes

		// Unique Constraint name Index
		graph.createKeyIndex(TConstraint.NAME, Vertex.class,
				new Parameter<String, String>("type", "UNIQUE"),
				new Parameter<String, String>("class", TConstraint.TRACE_TYPE));

		// Unique Element ID index
		graph.createKeyIndex(TElement.ELEMENT_ID, Vertex.class,
				new Parameter<String, String>("type", "UNIQUE"),
				new Parameter<String, String>("class", TElement.TRACE_TYPE));
	}

}
