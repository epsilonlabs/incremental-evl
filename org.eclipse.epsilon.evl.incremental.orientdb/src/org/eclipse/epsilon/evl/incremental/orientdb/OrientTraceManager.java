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
package org.eclipse.epsilon.evl.incremental.orientdb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.impl.ConstraintTraceOrientDBImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.impl.ContextTraceOrientDBImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.impl.ElementPropertyOrientDBImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.impl.ModelElementOrientDBImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.impl.TraceScopeOrientDBImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TEvaluates;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TIn;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TRootOf;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

// TODO: Auto-generated Javadoc
/**
 * Implementation of {@link IIncrementalTraceManager} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientTraceManager implements IIncrementalTraceManager {

	/** The framed graph. */
	private final FramedGraph<OrientBaseGraph> framedGraph;
	
	/** The base graph. */
	private final OrientBaseGraph baseGraph;

	/**
	 * Package private constructor - use {@link OrientPropertyAccessTraceFactory} to
	 * create.
	 *
	 * @param baseGraph the base graph
	 */
	OrientTraceManager(OrientBaseGraph baseGraph) {
		this.baseGraph = baseGraph;
		FramedGraphFactory fgf = new FramedGraphFactory();
		this.framedGraph = fgf.create(this.baseGraph);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createContext(java.lang.String)
	 */
	@Override
	public IContextTrace createContext(String contextName) {
		IContextTrace context = this.getContext(contextName);
		if (context == null) {
			context = new ContextTraceOrientDBImpl(this.addVertex(TContext.TRACE_TYPE, TContext.class));
			context.setName(contextName);
		}
		return context;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createConstraint(java.lang.String, java.lang.String)
	 */
	@Override
	public IConstraintTrace createConstraint(String constraintName, String contextName) {
		return this.createConstraint(constraintName, this.createContext(contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createConstraint(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
	 */
	@Override
	public IConstraintTrace createConstraint(String constraintName, IContextTrace context) {
		if (context == null) return null;
		
		IConstraintTrace constraint = this.getConstraint(constraintName, context);
		if (constraint == null) {
			constraint = new ConstraintTraceOrientDBImpl(this.addVertex(TConstraint.TRACE_TYPE, TConstraint.class));
			constraint.setName(constraintName);
			context.addConstraint(constraint);
		}
		return constraint;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createElement(java.lang.String)
	 */
	@Override
	public IModelElement createElement(String elementId) {
		IModelElement element = this.getElement(elementId);
		if (element == null) {
			element = new ModelElementOrientDBImpl(this.addVertex(TElement.TRACE_TYPE, TElement.class));
			element.setElementId(elementId);
		}
		return element;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createScope(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ITraceScope createScope(String elementId, String constraintName, String contextName) {
		return this.createScope(this.createElement(elementId), 
				this.createConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createScope(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public ITraceScope createScope(String elementId, IConstraintTrace constraint) {
		return this.createScope(this.createElement(elementId), constraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, java.lang.String, java.lang.String)
	 */
	@Override
	public ITraceScope createScope(IModelElement element, String constraintName, String contextName) {
		return this.createScope(element, this.createConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public ITraceScope createScope(IModelElement element, final IConstraintTrace constraint) {
		if (element == null || constraint == null) return null;
		
		ITraceScope scope = this.getScope(element, constraint);
		if (scope == null) {
			scope = new TraceScopeOrientDBImpl(this.addVertex(TScope.TRACE_TYPE, TScope.class));
			scope.setRootElement(element);
			scope.setConstraint(constraint);
		}
		return scope;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public IElementProperty createProperty(String propertyName, String elementId) {
		return this.createProperty(propertyName, this.createElement(elementId));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#createProperty(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	@Override
	public IElementProperty createProperty(String propertyName, IModelElement element) {
		if (element == null) return null;
		
		IElementProperty property = this.getProperty(propertyName, element);
		if (property == null) {
			property = new ElementPropertyOrientDBImpl(this.addVertex(TProperty.TRACE_TYPE, TProperty.class));
			property.setOwner(element);
			property.setName(propertyName);
		}
		return property;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getContext(java.lang.String)
	 */
	@Override
	public IContextTrace getContext(String contextName) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TContext.TRACE_TYPE, TContext.NAME),
				contextName);
		return vertex == null ? null : new ContextTraceOrientDBImpl(this.framedGraph.frame(vertex,
				TContext.class));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getConstraint(java.lang.String, java.lang.String)
	 */
	@Override
	public IConstraintTrace getConstraint(String constraintName, String contextName) {
		return this.getConstraint(constraintName, this.getContext(contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getConstraint(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
	 */
	@Override
	public IConstraintTrace getConstraint(String constraintName, IContextTrace context) {
		if (context == null) return null;
		
		TContext tContext = ((ContextTraceOrientDBImpl) context).getDelegate();
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tContext.asVertex())
		.inE(TIn.TRACE_TYPE)
		.outV()
		.has(TConstraint.NAME, constraintName);
		
		return p.hasNext() 
				? new ConstraintTraceOrientDBImpl(this.framedGraph.frame(p.next(), TConstraint.class))
				: null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getElement(java.lang.String)
	 */
	@Override
	public IModelElement getElement(String elementId) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TElement.TRACE_TYPE, TElement.ELEMENT_ID),
				elementId);
		return vertex == null ? null : new ModelElementOrientDBImpl(this.framedGraph.frame(vertex,
				TElement.class));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScope(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ITraceScope getScope(String elementId, String constraintName, String contextName) {
		return this.getScope(this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScope(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public ITraceScope getScope(String elementId, IConstraintTrace constraint) {
		return this.getScope(this.getElement(elementId), constraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, java.lang.String, java.lang.String)
	 */
	@Override
	public ITraceScope getScope(IModelElement element, String constraintName,  String contextName) {
		return this.getScope(element, this.getConstraint(constraintName, contextName));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public ITraceScope getScope(IModelElement element, final IConstraintTrace constraint) {
		if (element == null || constraint == null) return null;
		
		TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tElement.asVertex())
		.outE(TRootOf.TRACE_TYPE)
		.inV()
		.as("scopes")
		.inE(TEvaluates.TRACE_TYPE)
		.outV()
		.filter(new PipeFunction<Vertex, Boolean>() {
			@Override
			public Boolean compute(Vertex vertex) {
				return vertex.getProperty(TConstraint.NAME).equals(constraint.getName());
			}
		})
		.back("scopes");
		
		return p.hasNext() 
				? new TraceScopeOrientDBImpl(this.framedGraph.frame(p.next(), TScope.class))
				: null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public IElementProperty getProperty(String propertyName, String elementId) {
		return this.getProperty(propertyName, this.getElement(elementId));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getProperty(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	@Override
	public IElementProperty getProperty(String propertyName, IModelElement element) {
		if (element == null) return null;
		
		TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tElement.asVertex())
		.out(TOwns.TRACE_TYPE)
		.has(TProperty.NAME, propertyName);
		
		return p.hasNext() 
				? new ElementPropertyOrientDBImpl(this.framedGraph.frame(p.next(), TProperty.class)) 
				: null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllContexts()
	 */
	@Override
	public Iterable<IContextTrace> getAllContexts() {
		List<IContextTrace> result = new ArrayList<IContextTrace>();
		for (TContext iter :this.getAll(TContext.TRACE_TYPE, TContext.class) ) {
			result.add(new ContextTraceOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllConstraints()
	 */
	@Override
	public Iterable<IConstraintTrace> getAllConstraints() {
		List<IConstraintTrace> result = new ArrayList<IConstraintTrace>();
		for (TConstraint iter :this.getAll(TConstraint.TRACE_TYPE, TConstraint.class) ) {
			result.add(new ConstraintTraceOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllElements()
	 */
	@Override
	public Iterable<IModelElement> getAllElements() {
		List<IModelElement> result = new ArrayList<IModelElement>();
		for (TElement iter :this.getAll(TElement.TRACE_TYPE, TElement.class) ) {
			result.add(new ModelElementOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllScopes()
	 */
	@Override
	public Iterable<ITraceScope> getAllScopes() {
		List<ITraceScope> result = new ArrayList<ITraceScope>();
		for (TScope iter :this.getAll(TScope.TRACE_TYPE, TScope.class) ) {
			result.add(new TraceScopeOrientDBImpl(iter));
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
	public Iterable<ITraceScope> getScopesOf(IModelElement element) {
		if (element == null) {
			return new LinkedList<ITraceScope>();
		}
		TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		final List<Vertex> results = new LinkedList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(tElement.asVertex())
			.out(TOwns.TRACE_TYPE)
			.in(TAccesses.TRACE_TYPE)
			.aggregate(results)
			.next();
		List<ITraceScope> result = new ArrayList<ITraceScope>();
		for (TScope iter : this.framedGraph.frameVertices(results, TScope.class)) {
			result.add(new TraceScopeOrientDBImpl(iter));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScopesOfId(java.lang.String)
	 */
	@Override
	public Iterable<ITraceScope> getScopesOfId(String elementId) {
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
			TContext tContext = ((ContextTraceOrientDBImpl) context).getDelegate();
			this.baseGraph.removeVertex(tContext.asVertex());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	@Override
	public void removeConstraint(IConstraintTrace constraint) {
		if (constraint != null) {
			TConstraint tConstraint = ((ConstraintTraceOrientDBImpl) constraint).getDelegate();
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
			TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
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
	public void removeScope(ITraceScope scope) {
		if (scope != null) {
			TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
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
		this.removeScope(this.getScope(element, constraint));
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
