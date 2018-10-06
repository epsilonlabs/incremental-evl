 /*******************************************************************************
 * This file was automatically generated on: 2018-09-13.
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
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region ModelElementTraceImports on begin **/
/** protected region ModelElementTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementTrace. 
 */
public class ModelElementTraceGremlin implements IModelElementTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
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
     * Empty constructor for deserialization.
     */    
    public ModelElementTraceGremlin() { }
    
    /**
     * Instantiates a new ModelElementTraceGremlin. The ModelElementTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTraceGremlin(
        String uri, IModelTypeTrace type, IModelTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
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
        if (!container.elements().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.modelTrace = new ModelElementTraceHasModelTraceGremlin(this, gts, BaseTraceFactory.getFactory());
        this.properties = new ModelElementTraceHasPropertiesGremlin(this, gts, BaseTraceFactory.getFactory());
        this.type = new ModelElementTraceHasTypeGremlin(this, gts, BaseTraceFactory.getFactory());
        this.kind = new ModelElementTraceHasKindGremlin(this, gts, BaseTraceFactory.getFactory());
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
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("uri").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region uri on begin **/
	            // TODO Add default return value for ModelElementTraceGremlin.getUri
	            throw new IllegalStateException("Add default return value for ModelElementTraceGremlin.getUri", ex);
	            /** protected region uri end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IModelElementTraceHasProperties properties() {
        if (properties == null) {
            properties = new ModelElementTraceHasPropertiesGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("properties");
                if (gt.hasNext()) {
                    ((ModelElementTraceHasPropertiesGremlin)properties).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return properties;
    }

    @Override
    public IModelElementTraceHasModelTrace modelTrace() {
        if (modelTrace == null) {
            modelTrace = new ModelElementTraceHasModelTraceGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("modelTrace");
                if (gt.hasNext()) {
                    ((ModelElementTraceHasModelTraceGremlin)modelTrace).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return modelTrace;
    }

    @Override
    public IModelElementTraceHasType type() {
        if (type == null) {
            type = new ModelElementTraceHasTypeGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("type");
                if (gt.hasNext()) {
                    ((ModelElementTraceHasTypeGremlin)type).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return type;
    }

    @Override
    public IModelElementTraceHasKind kind() {
        if (kind == null) {
            kind = new ModelElementTraceHasKindGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("kind");
                if (gt.hasNext()) {
                    ((ModelElementTraceHasKindGremlin)kind).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return kind;
    }

    @Override
    public IPropertyTrace getOrCreatePropertyTrace(String name) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        PropertyTraceGremlin propertyTrace = null;
        try {
    	    GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("properties").has("name", name);
    	    if (gt.hasNext()) {
    	        propertyTrace = new PropertyTraceGremlin();
    	        propertyTrace.delegate(gt.next());
    	        propertyTrace.graphTraversalSource(gts);
    	    }
    	    else {
    	        Vertex v = null;
    	        try {
                v = g.addV("PropertyTrace").property(T.id, GremlinUtils.identityToString(name, this)).next();
    	            propertyTrace = new PropertyTraceGremlin(name, this, v, gts);
    	        } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	            g.V(v).as("v").properties().drop().select("v").drop();
    	            throw new EolIncrementalExecutionException("Error creating requested PropertyTrace", e);
    	        }
    	    }
    	} finally {
            finishTraversal(g);
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
