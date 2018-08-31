 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelTraceImports on begin **/
/** protected region ModelTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelTrace. 
 */
public class ModelTraceGremlin implements IModelTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The elements.
     */
    private IModelTraceHasElements elements;

    /**
     * The types.
     */
    private IModelTraceHasTypes types;

    /**
     * Empty constructor for deserialization.
     */    
    public ModelTraceGremlin() {
    }
    
    /**
     * Instantiates a new ModelTraceGremlin. The ModelTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTraceGremlin(
        String uri, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        this.elements = new ModelTraceHasElementsGremlin(this, gts);
        this.types = new ModelTraceHasTypesGremlin(this, gts);
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
            // TODO Add default return value for ModelTraceGremlin.getgetUri
            throw new IllegalStateException(ex);
            /** protected region uri end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IModelTraceHasElements elements() {
        if (elements == null) {
            elements = new ModelTraceHasElementsGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("elements");
                if (gt.hasNext()) {
                    ((ModelTraceHasElementsGremlin)elements).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return elements;
    }

    @Override
    public IModelTraceHasTypes types() {
        if (types == null) {
            types = new ModelTraceHasTypesGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("types");
                if (gt.hasNext()) {
                    ((ModelTraceHasTypesGremlin)types).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return types;
    }

    @Override
    public IModelElementTrace getOrCreateModelElementTrace(String uri, IModelTypeTrace type) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelElementTraceGremlin modelElementTrace = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("ModelElementTrace").next();
    	        modelElementTrace = new ModelElementTraceGremlin(uri, type, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (modelElementTrace != null) {
    		        return modelElementTrace;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModelTraceHasElementsGremlin) this.elements).getRaw()
    	            .hasLabel("ModelElementTrace")
    	            .has("uri", uri)
    	            .as("a") 
    	            .out("type").hasId(type.getId())
    	            .in("type").where(P.eq("a"))
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElementTrace was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        modelElementTrace = new ModelElementTraceGremlin();
    	        modelElementTrace.delegate(gt.next());
    	        modelElementTrace.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return modelElementTrace;
    }      
                  
    @Override
    public IModelTypeTrace getOrCreateModelTypeTrace(String name) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelTypeTraceGremlin modelTypeTrace = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("ModelTypeTrace").next();
    	        modelTypeTrace = new ModelTypeTraceGremlin(name, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (modelTypeTrace != null) {
    		        return modelTypeTrace;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModelTraceHasTypesGremlin) this.types).getRaw()
    	            .hasLabel("ModelTypeTrace")
    	            .has("name", name)
    	            .as("a") 
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelTypeTrace was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        modelTypeTrace = new ModelTypeTraceGremlin();
    	        modelTypeTrace.delegate(gt.next());
    	        modelTypeTrace.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return modelTypeTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IModelTrace other) {
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
        if (!(obj instanceof ModelTraceGremlin))
            return false;
        ModelTraceGremlin other = (ModelTraceGremlin) obj;
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
