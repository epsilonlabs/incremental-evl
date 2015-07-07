package org.eclipse.epsilon.evl.trace;

import java.util.HashMap;
import java.util.Map;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

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
	public OrientTraceGraph getGraph() {
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
		
		// Context
		OrientVertexType context = graph.getVertexType(TContext.TRACE_TYPE);
		if (context == null) {
			context = graph.createVertexType(TContext.TRACE_TYPE);
			context.createProperty(TContext.NAME, OType.STRING);
			graph.createKeyIndex(TContext.NAME, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", TContext.TRACE_TYPE));
		}
		
		// Constraint
		OrientVertexType constraint = graph.getVertexType(TConstraint.TRACE_TYPE);
		if (constraint == null) {
			constraint = graph.createVertexType(TConstraint.TRACE_TYPE);
			constraint.createProperty(TConstraint.NAME, OType.STRING);
			// FIXME: Currently uses traversal, faster to use the index and iterate?
//			graph.createKeyIndex(TConstraint.NAME, Vertex.class,
//					new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
//					new Parameter<String, String>("class",
//							TConstraint.TRACE_TYPE));
		}
		
		// Element
		OrientVertexType element = graph.getVertexType(TElement.TRACE_TYPE);
		if (element == null) {
			element = graph.createVertexType(TElement.TRACE_TYPE);
			element.createProperty(TElement.ELEMENT_ID, OType.STRING);
			graph.createKeyIndex(TElement.ELEMENT_ID, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", TElement.TRACE_TYPE));
		}
		
		// Property
		OrientVertexType property = graph.getVertexType(TProperty.TRACE_TYPE);
		if (property == null) {
			property = graph.createVertexType(TProperty.TRACE_TYPE);
			property.createProperty(TProperty.NAME, OType.STRING);
		}
		
		// Scope
		OrientVertexType scope = graph.getVertexType(TScope.TRACE_TYPE);
		if (scope == null) {
			graph.createVertexType(TScope.TRACE_TYPE);
		}
		
		// Edges
		setupEdgeType(graph, 
				TEvaluates.TRACE_TYPE,
				TOwns.TRACE_TYPE,
				TRootOf.TRACE_TYPE,
				TAccesses.TRACE_TYPE,
				TIn.TRACE_TYPE);
		
		graph.commit();
	}

	private void setupEdgeType(OrientGraph graph, String... types) {
		for (String type : types) {
			if (graph.getEdgeType(type) == null) graph.createEdgeType(type);
		}
	}
	
}
