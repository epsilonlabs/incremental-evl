 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
/** protected region ModelTraceImports on begin **/
/** protected region ModelTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelTrace. 
 */
@SuppressWarnings("unused") 
public class ModelTraceGremlin implements IModelTrace, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The elements.
     */
    private IModelTraceHasElements elements;

    /**
     * The types.
     */
    private IModelTraceHasTypes types;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ModelTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.elements = new ModelTraceHasElementsGremlin(this, gts, wrapperFactory);
        this.types = new ModelTraceHasTypesGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new ModelTraceGremlin. The ModelTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTraceGremlin(
        String uri,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        this.elements = new ModelTraceHasElementsGremlin(this, gts, wrapperFactory);
        this.types = new ModelTraceHasTypesGremlin(this, gts, wrapperFactory);
    
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
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("uri");
            if (values.hasNext()) {
                return (String) values.next().value();
            }
            else {
                /** protected region uri on begin **/
	            // TODO Add default return value for ModelTraceGremlin.getUri
	            throw new IllegalStateException("Add default return value for ModelTraceGremlin.getUri");
	            /** protected region uri end **/
            }
            
        }
        return null;
    }
    
    @Override
    public IModelTraceHasElements elements() {
        
        return elements;
    }

    @Override
    public IModelTraceHasTypes types() {
        
        return types;
    }

    @Override
    public IModelElementTrace getOrCreateModelElementTrace(String uri, IModelTypeTrace type) throws EolIncrementalExecutionException {    
        ModelElementTraceGremlin modelElementTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("elements").has("uri", uri).out("type").hasId(type.getId());
            if (gt.hasNext()) {
                modelElementTrace = new ModelElementTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("ModelElementTrace").property("tag", GremlinUtils.identityToString(uri, type, this)).next();
                    /* protected region modelElementTraceTypeOverride on begin */
                    modelElementTrace = new ModelElementTraceGremlin(uri, type, this, v, gts, wrapperFactory); 
                    /* protected region modelElementTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ModelElementTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return modelElementTrace;
    }      
    
    @Override
    public IModelTypeTrace getOrCreateModelTypeTrace(String name) throws EolIncrementalExecutionException {    
        ModelTypeTraceGremlin modelTypeTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("types").has("name", name);
            if (gt.hasNext()) {
                modelTypeTrace = new ModelTypeTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("ModelTypeTrace").property("tag", GremlinUtils.identityToString(name, this)).next();
                    /* protected region modelTypeTraceTypeOverride on begin */
                    modelTypeTrace = new ModelTypeTraceGremlin(name, this, v, gts, wrapperFactory); 
                    /* protected region modelTypeTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ModelTypeTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return modelTypeTrace;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("uri", getUri());
        return result;
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
    public GraphTraversalSource graphTraversalSource() {
        return this.gts;
    }
    
}
