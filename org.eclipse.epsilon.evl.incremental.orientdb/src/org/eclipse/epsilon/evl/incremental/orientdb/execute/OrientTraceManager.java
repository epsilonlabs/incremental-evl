/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.*;


import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

/**
 * Implementation of {@link IExecutionTraceManager} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientTraceManager implements IExecutionTraceManager {

	/** The framed graph. */
	private FramedGraph<OrientBaseGraph> framedGraph;
	
	/** The base graph. */
	private OrientBaseGraph baseGraph;
	
	/**
	 * The execution context of this trace manager. If not unique for a session, the {@link OrientTraceManager#setExecutionContext(String, Collection)} 
	 * method can be invoked before creating or retrieving records form the database.
	 */
	private ExecutionContextOrientDBImpl executionContext;
	
	@Override
	public void configure(String[] configParameters) {
		
		assert configParameters.length == 3;
		String url = configParameters[0];
		String user = configParameters[1];
		String password = configParameters[2];
		// "memory:EVLTrace"
		baseGraph = new OrientGraphNoTx(url, user, password);
		setupSchema();
		FramedGraphFactory fgf = new FramedGraphFactory();
		framedGraph = fgf.create(this.baseGraph);
	}

	@Override
	public void executionStarted() {
		// DB is No transaction so nothing to do
	}

	@Override
	public void executionFinished() {
		// DB is No transaction so nothing to do
	}


	@Override
	public IExecutionContext setExecutionContext(String scriptId, List<String> modelsIds) {
		executionContext = (ExecutionContextOrientDBImpl) acquireExecutionContext(scriptId, modelsIds);
		return executionContext;
	}
	
	@Override
	public boolean createTraces(String moduleElementId, String elementId, List<String> properties) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createTrace(String moduleElementId, String elementId, String propertyName) {
		
		IModelElement modelElementTrace = this.createModelElement(elementId);
		IModuleElement moduleElementTrace = this.createModuleElement(moduleElementId);
		IExecutionTrace executionTrace = this.getExecutionTrace(modelElementTrace);
		if (executionTrace == null) {
			executionTrace = new ExecutionTraceOrientDBImpl(this.addVertex(NExecutionTrace.TRACE_TYPE, NExecutionTrace.class));
			executionTrace.setModuleElement(moduleElementTrace);
			executionTrace.addModelElementTrace(modelElementTrace);
			
		}
		IElementProperty propertyTrace = this.getProperty(propertyName, modelElementTrace, executionTrace);
		if (propertyTrace == null) {
			propertyTrace = this.createElementProperty(propertyName);
			executionTrace.addElementPropertyTrace(propertyTrace);
		}
		return true;
	}


	@Override
	public List<IExecutionTrace> getTraces(String objectId, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<IExecutionTrace> getTraces(String objectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//***************************
	//*******  DB Setup  ********
	//***************************
	
	/**
	 * Add vertex types to OrientDB, define keys and setup node properties
	 *
	 * @param graph
	 *            The graph to add vertex types to
	 */
	private void setupSchema() {
		
		// ExecutionContext. Indexed only by Script Id, will then have to filter to match by model
		OrientVertexType ecVertex = baseGraph.getVertexType(NExecutionContext.TRACE_TYPE);
		if (ecVertex == null) {
			ecVertex = baseGraph.createVertexType(NExecutionContext.TRACE_TYPE);
			ecVertex.createProperty(NExecutionContext.SCRIPT_ID, OType.STRING);
			ecVertex.createProperty(NExecutionContext.MODELS_IDS, OType.EMBEDDEDLIST);
			baseGraph.createKeyIndex(NExecutionContext.SCRIPT_ID, Vertex.class,
					new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
					new Parameter<String, String>("class", NExecutionContext.TRACE_TYPE));
			
		}
		
		// ExecutionTrace
		OrientVertexType etVertex = baseGraph.getVertexType(NExecutionTrace.TRACE_TYPE);
		if (etVertex == null) {
			etVertex = baseGraph.createVertexType(NExecutionTrace.TRACE_TYPE);
		}
		
		// ModelElement
		OrientVertexType element = baseGraph.getVertexType(NModelElement.TRACE_TYPE);
		if (element == null) {
			element = baseGraph.createVertexType(NModelElement.TRACE_TYPE);
			element.createProperty(NModelElement.ID, OType.STRING);
			baseGraph.createKeyIndex(NModelElement.ID, Vertex.class,
					new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),		// Element Ids can be the same in different models
					new Parameter<String, String>("class", NModelElement.TRACE_TYPE));
		}
		
		// ModuleElement
		OrientVertexType constraint = baseGraph.getVertexType(NModuleElement.TRACE_TYPE);
		if (constraint == null) {
			constraint = baseGraph.createVertexType(NModuleElement.TRACE_TYPE);
			constraint.createProperty(NModuleElement.ID, OType.STRING);
			baseGraph.createKeyIndex(NModuleElement.ID, Vertex.class,
					new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),		// Module Element Ids can be the same in different scripts 
					new Parameter<String, String>("class", NModuleElement.TRACE_TYPE));
		}
		
		// ElementProperty
		OrientVertexType property = baseGraph.getVertexType(NElementProperty.TRACE_TYPE);
		if (property == null) {
			property = baseGraph.createVertexType(NElementProperty.TRACE_TYPE);
			property.createProperty(NElementProperty.NAME, OType.STRING);
		}
		
		// Edges
		setupEdgeTypes(EAccesses.TRACE_TYPE,
				EFor.TRACE_TYPE,
				EReaches.TRACE_TYPE,
				ETraces.TRACE_TYPE);
		
		baseGraph.commit();
	}
	

	
	
	//***************************
	//*******    CRUD    ********
	//***************************
	
	/**
	 * Create an Element Property vertex in the Graph.
	 * 
	 * @param propertyName the property name
	 * @return
	 */
	private ElementPropertyOrientDBImpl createElementProperty(String propertyName) {
		ElementPropertyOrientDBImpl property = new ElementPropertyOrientDBImpl(
				this.addVertex(NElementProperty.TRACE_TYPE, NElementProperty.class));
		property.setName(propertyName);
		return property;
	}
	
	/**
	 * Creates an Execution Context vertex in the Graph.
	 * 
	 * @param scriptId	The script's ID
	 * @param modelsIds	The models' IDs
	 * @return
	 */
	private ExecutionContextOrientDBImpl createExecutionContext(String scriptId, List<String> modelsIds) {
		
		ExecutionContextOrientDBImpl ec = new ExecutionContextOrientDBImpl(this.addVertex(NExecutionContext.TRACE_TYPE, NExecutionContext.class));
		ec.setScriptId(scriptId);
		ec.setModelsIds(modelsIds);
		return ec;
	}
	
	/**
	 * Gets an Execution Context vertex from the graph identified by the IDs of the script and models used during
	 * execution.
	 * 
	 * @param scriptId The script's ID
	 * @param modelsIds The models' IDs
	 * @return
	 */
	private ExecutionContextOrientDBImpl getExecutionContext(String scriptId, List<String> modelsIds) {
		Iterable<Vertex> vertices = this.baseGraph.getVertices(
				this.getIndexName(NExecutionContext.TRACE_TYPE, NExecutionContext.SCRIPT_ID),
				scriptId);
		
		Vertex ec_vertex = null;
		for (Vertex v : vertices) {
			List<String> v_models = v.getProperty(NExecutionContext.MODELS_IDS);
			v_models.removeAll(modelsIds);
			if (v_models.isEmpty()) {
				ec_vertex = v;
				break;
			}
			
		}
		return ec_vertex == null ? null : new ExecutionContextOrientDBImpl(this.framedGraph.frame(ec_vertex,
				NExecutionContext.class));
	}
	
	/**
	 * Acquires an Execution Context vertex from the graph, creating one if a matching one cannot be found.
	 * 
	 * @param scriptId
	 * @param modelsIds
	 * @return
	 */
	private ExecutionContextOrientDBImpl acquireExecutionContext(String scriptId, List<String> modelsIds) {
		ExecutionContextOrientDBImpl ec = this.getExecutionContext(scriptId, modelsIds);
		if (ec == null) {
			ec = createExecutionContext(scriptId, modelsIds);
		}
		return ec;
	}
	
	/**
	 * Creates an Execution Trace vertex in the graph.
	 * 
	 * @return
	 */
	private ExecutionTraceOrientDBImpl createExecutionTrace() {
		ExecutionTraceOrientDBImpl et = new ExecutionTraceOrientDBImpl(
				this.addVertex(NExecutionTrace.TRACE_TYPE, NExecutionTrace.class));
		return et;
	}
	
	/**
	 * Gets the execution trace for a given module element and model element.
	 * 
	 * @param moduleElementId	The module element id
	 * @param modelElementId	The model element id
	 * @return
	 */
	public ExecutionTraceOrientDBImpl getExecutionTrace(String moduleElementId, String modelElementId) {
		
		ModuleElementOrientDBImpl mue = (ModuleElementOrientDBImpl) getModuleElement(moduleElementId);
		if (mue == null) {
			return null;	// FIXME Probable an exception is better indicating that the module is not persisted
		}
		final ModelElementOrientDBImpl moe = getModelElement(modelElementId, mue);
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(mue.asVertex())
			.in(ETraces.TRACE_TYPE)
			.as("et")
			.out(EReaches.TRACE_TYPE)
			.filter(new PipeFunction<Vertex, Boolean>() {
				
				@Override
				public Boolean compute(Vertex v) {
					return v == moe;
				}
			})
			.back("et");
		ExecutionTraceOrientDBImpl et = null;
		if (p.hasNext()) {
			et = new ExecutionTraceOrientDBImpl(this.framedGraph.frame(p.next(),
				NExecutionTrace.class));
		}
		return et;
	}
	

	/**
	 * Acquires an Execution Trace vertex from the graph, creating one if a matching one cannot be found.
	 * @param moduleElementId
	 * @return
	 */
	public ExecutionTraceOrientDBImpl acquireExecutionTrace(String moduleElementId, String modelElementId) {
		ExecutionTraceOrientDBImpl et = getExecutionTrace(moduleElementId, modelElementId);
		if (et == null) {
			et = createExecutionTrace();
			et.addModelElementTrace(acquireModelElement(modelElementId));
		}
		
	}

	
	public IExecutionTrace getScope(String elementId, IConstraintTrace constraint) {
		return this.geNExecutionTrace(this.getElement(elementId), constraint);
	}

	
	public IExecutionTrace getScope(IModelElement element, String constraintName,  String contextName) {
		return this.geNExecutionTrace(element, this.getConstraint(constraintName, contextName));
	}

	
	
	private ModelElementOrientDBImpl getModelElement(String modelElementId, IModuleElement mue) {
		//TODO Add implementation
		return null;
	}
	
	
	
	
	//////////////////////////////
	
	
	

	private IExecutionTrace getExecutionTrace(IModelElement element) {
		if (element == null) return null;
		
		TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tElement.asVertex())
		.outE(EReaches.TRACE_TYPE)
		.inV();
//		.as("scopes")
//		.inE(TTraces.TRACE_TYPE)
//		.outV()
//		.back("scopes");
		
		return p.hasNext() 
				? new ExecutionTraceOrientDBImpl(this.framedGraph.frame(p.next(), NExecutionTrace.class))
				: null;
	}
	
	
	
	
	
	
	/**
	 * Creates Module Element vertex in the Graph
	 * Create a Module Element in the database iff it does not exist. Module elements are uniquely identified by their
	 * id and the current execution context.
	 * @param id
	 * @return
	 */
	private IModuleElement createModuleElement(String id) {
		
		IModuleElement moduleElement = this.getModuleElement(id);
		if (moduleElement == null) {
			moduleElement = new ModuleElementOrientDBImpl(this.addVertex(NModuleElement.TRACE_TYPE, NModuleElement.class));
			moduleElement.setId(id);
		}
		return moduleElement;
	}
	

	private IModuleElement getModuleElement(String id) {
		
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(executionContext.asVertex())
			.in(EFor.TRACE_TYPE)
			.outV()
			.has(NModuleElement.ID, id);
		NModuleElement me_v = null;
		if (p.hasNext()) {
			me_v  = (NModuleElement) p.next(); 
		}
		return me_v == null ? null : new ModuleElementOrientDBImpl(this.framedGraph.frame(me_v, NModuleElement.class));
	}



	private IModelElement getElement(String elementId) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TModelElement.TRACE_TYPE, TModelElement.ELEMENT_ID),
				elementId);
		return vertex == null ? null : new ModelElementOrientDBImpl(this.framedGraph.frame(vertex,
				TModelElement.class));
	}
	
	
	
	
	
	

	private IModelElement createModelElement(String elementId) {
		IModelElement element = this.getElement(elementId);
		if (element == null) {
			element = new ModelElementOrientDBImpl(this.addVertex(TModelElement.TRACE_TYPE, TModelElement.class));
			element.setElementId(elementId);
		}
		return element;
	}


	


	



	

	

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllContexts()
	 */
	@Override
	public Iterable<IContextTrace> getAllContexts() {
		List<IContextTrace> result = new ArrayList<IContextTrace>();
		for (TContext iter :this.getAll(TContext.TRACE_TYPE, TContext.class) ) {
			result.add(new ExecutionContextOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllConstraints()
	 */
	@Override
	public Iterable<IConstraintTrace> getAllConstraints() {
		List<IConstraintTrace> result = new ArrayList<IConstraintTrace>();
		for (NModuleElement iter :this.getAll(NModuleElement.TRACE_TYPE, NModuleElement.class) ) {
			result.add(new ModuleElementOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllElements()
	 */
	@Override
	public Iterable<IModelElement> getAllElements() {
		List<IModelElement> result = new ArrayList<IModelElement>();
		for (TModelElement iter :this.getAll(TModelElement.TRACE_TYPE, TModelElement.class) ) {
			result.add(new ModelElementOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllScopes()
	 */
	@Override
	public Iterable<IExecutionTrace> getAllScopes() {
		List<IExecutionTrace> result = new ArrayList<IExecutionTrace>();
		for (NExecutionTrace iter :this.getAll(NExecutionTrace.TRACE_TYPE, NExecutionTrace.class) ) {
			result.add(new ExecutionTraceOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllProperties()
	 */
	@Override
	public Iterable<IElementProperty> getAllProperties() {
		List<IElementProperty> result = new ArrayList<IElementProperty>();
		for (TProperty iter :this.getAll(TProperty.TRACE_TYPE, TProperty.class) ) {
			result.add(new ElementPropertyOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScopesOf(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	@Override
	public Iterable<IExecutionTrace> getScopesOf(IModelElement element) {
		if (element == null) {
			return new LinkedList<IExecutionTrace>();
		}
		TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		final List<Vertex> results = new LinkedList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tElement.asVertex())
			.out(TOwns.TRACE_TYPE)
			.in(TAccesses.TRACE_TYPE)
			.aggregate(results)
			.next();
		
		
		List<IExecutionTrace> result = new ArrayList<IExecutionTrace>();
		for (NExecutionTrace iter : this.framedGraph.frameVertices(results, NExecutionTrace.class)) {
			result.add(new ExecutionTraceOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScopesOfId(java.lang.String)
	 */
	@Override
	public Iterable<IExecutionTrace> getScopesOfId(String elementId) {
		return this.getScopesOf(this.getElement(elementId));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeContext(java.lang.String)
	 */
	@Override
	public void removeContext(String contextName) {
		this.removeContext(this.getContext(contextName));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeContext(org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
	 */
	@Override
	public void removeContext(IContextTrace context) {
		if (context != null) {
			TContext tContext = ((ExecutionContextOrientDBImpl) context).getDelegate();
			this.baseGraph.removeVertex(tContext.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public void removeConstraint(IConstraintTrace constraint) {
		if (constraint != null) {
			NModuleElement tConstraint = ((ModuleElementOrientDBImpl) constraint).getDelegate();
			this.baseGraph.removeVertex(tConstraint.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeConstraint(String constraintName,
			String contextName) {
		this.removeConstraint(this.getConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
	 */
	@Override
	public void removeConstraint(String constraintName, IContextTrace context) {
		this.removeConstraint(this.getConstraint(constraintName, context));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeElement(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	@Override
	public void removeElement(IModelElement element) {
		if (element != null) {
			TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
			this.baseGraph.removeVertex(tElement.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeElement(java.lang.String)
	 */
	@Override
	public void removeElement(String elementId) {
		this.removeElement(this.getElement(elementId));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	@Override
	public void removeScope(IExecutionTrace scope) {
		if (scope != null) {
			NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) scope).getDelegate();
			this.baseGraph.removeVertex(tScope.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void removeScope(String elementId, String constraintName, String contextName) {
		this.removeScope(
				this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public void removeScope(String elementId, IConstraintTrace constraint) {
		this.removeScope(this.getElement(elementId), constraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, java.lang.String, java.lang.String)
	 */
	@Override
	public void removeScope(IModelElement element, String constraintName, String contextName) {
		this.removeScope(element, this.getConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public void removeScope(IModelElement element, IConstraintTrace constraint) {
		this.removeModelElementTrace(this.geNExecutionTrace(element, constraint));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
	 */
	@Override
	public void removeProperty(IElementProperty property) {
		if ( property != null) {
			TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
			this.baseGraph.removeVertex( tProperty.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeProperty(String propertyName, String elementId) {
		this.removeProperty(this.getProperty(propertyName, elementId));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	@Override
	public void removeProperty(String propertyName, IModelElement element) {
		this.removeProperty(this.getProperty(propertyName, element));
	}

	/**
	 * Gets the base graph.
	 *
	 * @return the base graph
	 */
	public OrientBaseGraph getBaseGraph() {
		return this.baseGraph;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return !this.baseGraph.isClosed();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#commit()
	 */
	@Override
	public void commit() {
		this.baseGraph.commit();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#shutdown()
	 */
	@Override
	public void shutdown() {
		this.baseGraph.shutdown();
	}

	/**
	 * Adds the vertex.
	 *
	 * @param <F> the generic type
	 * @param traceType the trace type
	 * @param clazz the clazz
	 * @return the f
	 */
	private <F> F addVertex(String traceType, Class<F> clazz) {
		return this.framedGraph.addVertex(
				String.format("class:%s", traceType),
				clazz);
	}

	/**
	 * Gets the index name.
	 *
	 * @param classname the classname
	 * @param key the key
	 * @return the index name
	 */
	private String getIndexName(String classname, String key) {
		return String.format("%s.%s", classname, key).toLowerCase();
	}
	
	/**
	 * Gets the all.
	 *
	 * @param <F> the generic type
	 * @param traceType the trace type
	 * @param clazz the clazz
	 * @return the all
	 */
	private <F> Iterable<F> getAll(String traceType, Class<F> clazz) {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(traceType),
				clazz);
	}










}
