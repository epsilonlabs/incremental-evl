 /*******************************************************************************
 * This file was automatically generated on: 2019-04-25.
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
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
/** protected region ModelElementTraceImports on begin **/
/** protected region ModelElementTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementTrace. 
 */
@SuppressWarnings("unused") 
public class ModelElementTraceGremlin implements IModelElementTrace, GremlinWrapper<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The id.
     */
    private Object id;

    /**
     * The uri.
     */
    private String uri;

    
    /**
     * The properties.
     */
    private IModelElementTraceHasProperties properties;

    /**
     * The modelTrace.
     */
    private IModelElementTraceHasModelTrace modelTrace;

    /**
     * The type.
     */
    private IModelElementTraceHasType type;

    /**
     * The kind.
     */
    private IModelElementTraceHasKind kind;

    /**
     * Empty constructor for de/-serialization.
     */    
    // public ModelElementTraceGremlin() { }
    
    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ModelElementTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
    }
    
    /**
     * Instantiates a new ModelElementTraceGremlin. The ModelElementTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTraceGremlin(
        String uri,
        IModelTypeTrace type,
        IModelTrace container,
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
        if (!container.elements().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Derived Features
        // this.properties = new ModelElementTraceHasPropertiesGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.type = new ModelElementTraceHasTypeGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.kind = new ModelElementTraceHasKindGremlin(this, gts, wrapperFactory);
        try {
	        this.type.create(type);
        } catch (TraceModelConflictRelation ex) {
            ((ModelElementTraceHasTypeGremlin)this.type).delegate().remove();
            throw ex;
        }
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
        if (uri == null) {
	        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		        try {
		            uri = (String) agts.V(delegate).values("uri").next();
		        } catch (NoSuchElementException ex) {
		            /** protected region uri on begin **/
	            // TODO Add default return value for ModelElementTraceGremlin.getUri
	            throw new IllegalStateException("Add default return value for ModelElementTraceGremlin.getUri", ex);
	            /** protected region uri end **/
		        }
		    } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
	    }    
        return uri;
    }
    
    @Override
    public IModelElementTraceHasProperties properties() {
        if (properties == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("properties");
                if (gt.hasNext()) {
                    properties = new ModelElementTraceHasPropertiesGremlin(this, this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return properties;
    }

    @Override
    public IModelElementTraceHasModelTrace modelTrace() {
        if (modelTrace == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("modelTrace");
                if (gt.hasNext()) {
                    modelTrace = new ModelElementTraceHasModelTraceGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return modelTrace;
    }

    @Override
    public IModelElementTraceHasType type() {
        if (type == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("type");
                if (gt.hasNext()) {
                    type = new ModelElementTraceHasTypeGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return type;
    }

    @Override
    public IModelElementTraceHasKind kind() {
        if (kind == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("kind");
                if (gt.hasNext()) {
                    kind = new ModelElementTraceHasKindGremlin(this, this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return kind;
    }

    @Override
    public IPropertyTrace getOrCreatePropertyTrace(String name) throws EolIncrementalExecutionException {    
        PropertyTraceGremlin propertyTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("properties").has("name", name);
            if (gt.hasNext()) {
                propertyTrace = new PropertyTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("PropertyTrace").property("tag", GremlinUtils.identityToString(name, this)).next();
                    /* protected region propertyTraceTypeOverride on begin */
                    propertyTrace = new PropertyTraceGremlin(name, this, v, gts, wrapperFactory); 
                    /* protected region propertyTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested PropertyTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return propertyTrace;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("uri", getUri());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IModelElementTrace other) {
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
        if (!(obj instanceof ModelElementTraceGremlin))
            return false;
        ModelElementTraceGremlin other = (ModelElementTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (modelTrace == null) {
        if (other.modelTrace != null)
            return false;
    }
        if (!modelTrace().get().equals(other.modelTrace().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
        IModelTrace modelTrace = modelTrace().get();
        result = prime * result + ((modelTrace == null) ? 0 : modelTrace.hashCode());
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
