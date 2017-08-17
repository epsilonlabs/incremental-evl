/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epsilon.eol.incremental.old.IIncrementalTraceManager;
import org.eclipse.epsilon.eol.incremental.old.IPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NElementProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.TContext;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EAccesses;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.ETraces;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EFor;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EReaches;

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

	/** The config. */
	private final Map<String, String> config;
	
	/** The graph instance. */
	private OrientTraceManager graphInstance = null;
	
	/** The instance. */
	private static OrientPropertyAccessTraceFactory instance = new OrientPropertyAccessTraceFactory("memory:%s", "admin", "admin");

	/**
	 * Default Constructor.
	 *
	 * @param url the url
	 * @param user the user
	 * @param pass the pass
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
	
	/**
	 * Gets the single instance of OrientPropertyAccessTraceFactory.
	 *
	 * @return single instance of OrientPropertyAccessTraceFactory
	 */
	public static OrientPropertyAccessTraceFactory getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IPropertyAccessTraceFactory#getTrace()
	 */
	@Override
	public IIncrementalTraceManager getTrace() {
		// Init the graph if not already done so
		if (graphInstance == null || !graphInstance.isOpen()) {
//			OrientGraph orientGraph = (OrientGraph) GraphFactory.open(this.config);
			
			OrientGraphNoTx graph = new OrientGraphNoTx("memory:EVLTrace");
			
			this.setupClasses(graph);
			this.graphInstance = new OrientTraceManager(graph);
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
		OrientVertexType constraint = graph.getVertexType(NModuleElement.TRACE_TYPE);
		if (constraint == null) {
			constraint = graph.createVertexType(NModuleElement.TRACE_TYPE);
			constraint.createProperty(NModuleElement.ID, OType.STRING);
			// FIXME: Currently uses traversal, faster to use the index and iterate?
//			graph.createKeyIndex(TConstraint.NAME, Vertex.class,
//					new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
//					new Parameter<String, String>("class",
//							TConstraint.TRACE_TYPE));
		}
		
		// Element
		OrientVertexType element = graph.getVertexType(NModelElement.TRACE_TYPE);
		if (element == null) {
			element = graph.createVertexType(NModelElement.TRACE_TYPE);
			element.createProperty(NModelElement.ID, OType.STRING);
			graph.createKeyIndex(NModelElement.ID, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", NModelElement.TRACE_TYPE));
		}
		
		// Property
		OrientVertexType property = graph.getVertexType(NElementProperty.TRACE_TYPE);
		if (property == null) {
			property = graph.createVertexType(NElementProperty.TRACE_TYPE);
			property.createProperty(NElementProperty.NAME, OType.STRING);
		}
		
		// Scope
		OrientVertexType scope = graph.getVertexType(NExecutionTrace.TRACE_TYPE);
		if (scope == null) {
			scope = graph.createVertexType(NExecutionTrace.TRACE_TYPE);
			scope.createProperty(NExecutionTrace.RESULT, OType.BOOLEAN);
		}
		
		// Edges
		setupEdgeType(graph, 
				ETraces.TRACE_TYPE,
				TOwns.TRACE_TYPE,
				EReaches.TRACE_TYPE,
				EAccesses.TRACE_TYPE,
				EFor.TRACE_TYPE);
		
		graph.commit();
	}

	/**
	 * Setup edge type.
	 *
	 * @param graph the graph
	 * @param types the types
	 */
	private void setupEdgeType(OrientBaseGraph graph, String... types) {
		for (String type : types) {
			if (graph.getEdgeType(type) == null) graph.createEdgeType(type);
		}
	}
	
}
