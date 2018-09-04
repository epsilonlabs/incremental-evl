 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTraceGremlin implements IEvlModuleTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * * The module elements that conform the module.
       * Each language shoud specialize this class to represent the structure of its AST.
     */
    private IModuleExecutionTraceHasModuleElements moduleElements;

    /**
     * * The different models involved in the execution
     */
    private IModuleExecutionTraceHasModels models;

    /**
     * Empty constructor for deserialization.
     */    
    public EvlModuleTraceGremlin() {
    }
    
    /**
     * Instantiates a new EvlModuleTraceGremlin. The EvlModuleTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTraceGremlin(
        String uri, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, gts);
        this.models = new ModuleExecutionTraceHasModelsGremlin(this, gts);
    }
    
    @Override
    public Object getId() {
        return (Object) delegate == null ? null : delegate.id();
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
        throw new UnsupportedOperationException("Id is final");
  
    }   
     
    @Override
    public String getUri() {
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("uri").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region uri on begin **/
	            // TODO Add default return value for EvlModuleTraceGremlin.getUri
	            throw new IllegalStateException("Add default return value for EvlModuleTraceGremlin.getUri", ex);
	            /** protected region uri end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        if (moduleElements == null) {
            moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("moduleElements");
                if (gt.hasNext()) {
                    ((ModuleExecutionTraceHasModuleElementsGremlin)moduleElements).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        if (models == null) {
            models = new ModuleExecutionTraceHasModelsGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("models");
                if (gt.hasNext()) {
                    ((ModuleExecutionTraceHasModelsGremlin)models).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return models;
    }

    @Override
    public IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ContextTraceGremlin contextTrace = null;
        try {
    	    GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("moduleElements").has("kind", kind)
    	                                        .has("index", index);
    	    if (gt.hasNext()) {
    	        contextTrace = new ContextTraceGremlin();
    	        contextTrace.delegate(gt.next());
    	        contextTrace.graphTraversalSource(gts);
    	    }
    	    else {
    	        Vertex v = null;
    	        try {
    	            v = g.addV("ContextTrace").next();
    	            contextTrace = new ContextTraceGremlin(kind, index, this, v, gts);
    	        } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	            g.V(v).as("v").properties().drop().select("v").drop();
    	            throw new EolIncrementalExecutionException("Error creating requested ContextTrace", e);
    	        }
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return contextTrace;
    }      


    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelAccessGremlin modelAccess = null;
        try {
    	    GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("models").has("modelName", modelName);
    	    if (gt.hasNext()) {
    	        modelAccess = new ModelAccessGremlin();
    	        modelAccess.delegate(gt.next());
    	        modelAccess.graphTraversalSource(gts);
    	    }
    	    else {
    	        Vertex v = null;
    	        try {
    	            v = g.addV("ModelAccess").next();
    	            modelAccess = new ModelAccessGremlin(modelName, modelTrace, this, v, gts);
    	        } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	            g.V(v).as("v").properties().drop().select("v").drop();
    	            throw new EolIncrementalExecutionException("Error creating requested ModelAccess", e);
    	        }
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return modelAccess;
    }      

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("uri", getUri());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IEvlModuleTrace other) {
        if (other == null) {
            return false;
        }
        String uri = getUri();
        String otherUri = other.getUri();
        if (uri == null) {
            if (otherUri != null)
                return false;
        } else if (!uri.equals(otherUri)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EvlModuleTraceGremlin))
            return false;
        EvlModuleTraceGremlin other = (EvlModuleTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
        return result;
    }
    
    @Override
    public Vertex delegate() {
        return delegate;
    }

    @Override
    public void delegate(Vertex delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
    
    private GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    private void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }
}
