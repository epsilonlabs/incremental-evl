package org.eclipse.epsilon.evl.incremental.orientdb;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epsilon.evl.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IModelElement;
import org.eclipse.epsilon.evl.incremental.trace.IScopeConstraintTrace;
import org.eclipse.epsilon.evl.incremental.trace.IConstraintInContext;
import org.eclipse.epsilon.evl.incremental.trace.IElementOwnsProperty;
import org.eclipse.epsilon.evl.incremental.trace.IElementProperty;
import org.eclipse.epsilon.evl.incremental.trace.IElementRootOfScope;
import org.eclipse.epsilon.evl.incremental.trace.ITraceScope;

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
	public IIncrementalTraceManager getTrace() {
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
		OrientVertexType context = graph.getVertexType(IContextTrace.TRACE_TYPE);
		if (context == null) {
			context = graph.createVertexType(IContextTrace.TRACE_TYPE);
			context.createProperty(IContextTrace.NAME, OType.STRING);
			graph.createKeyIndex(IContextTrace.NAME, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", IContextTrace.TRACE_TYPE));
		}
		
		// Constraint
		OrientVertexType constraint = graph.getVertexType(IConstraintTrace.TRACE_TYPE);
		if (constraint == null) {
			constraint = graph.createVertexType(IConstraintTrace.TRACE_TYPE);
			constraint.createProperty(IConstraintTrace.NAME, OType.STRING);
			// FIXME: Currently uses traversal, faster to use the index and iterate?
//			graph.createKeyIndex(TConstraint.NAME, Vertex.class,
//					new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
//					new Parameter<String, String>("class",
//							TConstraint.TRACE_TYPE));
		}
		
		// Element
		OrientVertexType element = graph.getVertexType(IModelElement.TRACE_TYPE);
		if (element == null) {
			element = graph.createVertexType(IModelElement.TRACE_TYPE);
			element.createProperty(IModelElement.ELEMENT_ID, OType.STRING);
			graph.createKeyIndex(IModelElement.ELEMENT_ID, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", IModelElement.TRACE_TYPE));
		}
		
		// Property
		OrientVertexType property = graph.getVertexType(IElementProperty.TRACE_TYPE);
		if (property == null) {
			property = graph.createVertexType(IElementProperty.TRACE_TYPE);
			property.createProperty(IElementProperty.NAME, OType.STRING);
		}
		
		// Scope
		OrientVertexType scope = graph.getVertexType(ITraceScope.TRACE_TYPE);
		if (scope == null) {
			scope = graph.createVertexType(ITraceScope.TRACE_TYPE);
			scope.createProperty(ITraceScope.RESULT, OType.BOOLEAN);
		}
		
		// Edges
		setupEdgeType(graph, 
				IScopeConstraintTrace.TRACE_TYPE,
				IElementOwnsProperty.TRACE_TYPE,
				IElementRootOfScope.TRACE_TYPE,
				TAccesses.TRACE_TYPE,
				IConstraintInContext.TRACE_TYPE);
		
		graph.commit();
	}

	private void setupEdgeType(OrientBaseGraph graph, String... types) {
		for (String type : types) {
			if (graph.getEdgeType(type) == null) graph.createEdgeType(type);
		}
	}
	
}
