package org.eclipse.epsilon.evl.incremental.trace.orient;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.trace.TContext;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TEvaluates;
import org.eclipse.epsilon.evl.incremental.trace.TIn;
import org.eclipse.epsilon.evl.incremental.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TRootOf;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * Implementation of {@link IPropertyAccessTraceFactory} that uses Orient-DB as its
 * underlying database engine.
 * 
 * @author Jonathan Co
 *
 */
public class OrientPropertyAccessTraceFactory implements IPropertyAccessTraceFactory {

	private final Map<String, String> config;
	private OrientPropertyAccessTrace graphInstance = null;
	
	private static OrientPropertyAccessTraceFactory instance = new OrientPropertyAccessTraceFactory("memory:%s", "admin", "admin");

	/**
	 * Default Constructor
	 *
	 * @param url
	 * @param user
	 * @param pass
	 */
	private OrientPropertyAccessTraceFactory(String url, String user, String pass) {
		// Build configuration
		this.config = new HashMap<String, String>(4);
		this.config.put("blueprints.graph",
				"com.tinkerpop.blueprints.impls.orient.OrientGraph");
		this.config.put("blueprints.orientdb.url", url);
		this.config.put("blueprints.orientdb.username", user);
		this.config.put("blueprints.orientdb.password", pass);
		this.config.put("log.console.level", "fine");
		this.config.put("log.file.level", "fine");
	}
	
	public static OrientPropertyAccessTraceFactory getInstance() {
		return instance;
	}

	@Override
	public IPropertyAccessTrace getTrace() {
		// Init the graph if not already done so
		if (graphInstance == null || !graphInstance.isOpen()) {
//			OrientGraph orientGraph = (OrientGraph) GraphFactory.open(this.config);
			
			OrientGraphNoTx graph = new OrientGraphNoTx("memory:EVLTrace");
			
			this.setupClasses(graph);
			this.graphInstance = new OrientPropertyAccessTrace(graph);
		}
		return this.graphInstance;
	}

	/**
	 * Private utility - add vertex types to Orient backed graph.
	 *
	 * @param graph
	 *            The graph to add vertex types to
	 */
	private void setupClasses(OrientBaseGraph graph) {
		
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
			scope = graph.createVertexType(TScope.TRACE_TYPE);
			scope.createProperty(TScope.RESULT, OType.BOOLEAN);
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

	private void setupEdgeType(OrientBaseGraph graph, String... types) {
		for (String type : types) {
			if (graph.getEdgeType(type) == null) graph.createEdgeType(type);
		}
	}
	
}
