 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelElementTraceImports on begin **/
/** protected region ModelElementTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementTrace. 
 */
public class ModelElementTraceGremlin implements IModelElementTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
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
    public ModelElementTraceGremlin() {
    }
    
    /**
     * Instantiates a new ModelElementTraceGremlin. The ModelElementTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTraceGremlin(String uri, IModelTypeTrace type, IModelTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("uri", uri)
            .iterate();
        this.modelTrace = new ModelElementTraceHasModelTraceGremlin(this);
        this.properties = new ModelElementTraceHasPropertiesGremlin(this);
        this.type = new ModelElementTraceHasTypeGremlin(this);
        if (!this.type.create(type)) {
            throw new TraceModelDuplicateRelation();
        }
        this.kind = new ModelElementTraceHasKindGremlin(this);

        if (!container.elements().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
    }
    
    @Override
    public Object getId() {
        return (Object) g.V(delegate).values("id").next();
    }
    
    
    @Override
    public void setId(Object value) {
        g.V(delegate).property("id", value).iterate();
    }   
     
    @Override
    public String getUri() {
        return (String) g.V(delegate).values("uri").next();
    }
    
    @Override
    public IModelElementTraceHasProperties properties() {
        if (properties == null) {
            this.properties = new ModelElementTraceHasPropertiesGremlin(this);
        }
        return properties;
    }

    @Override
    public IModelElementTraceHasModelTrace modelTrace() {
        if (modelTrace == null) {
            this.modelTrace = new ModelElementTraceHasModelTraceGremlin(this);
        }
        return modelTrace;
    }

    @Override
    public IModelElementTraceHasType type() {
        if (type == null) {
            this.type = new ModelElementTraceHasTypeGremlin(this);
        }
        return type;
    }

    @Override
    public IModelElementTraceHasKind kind() {
        if (kind == null) {
            this.kind = new ModelElementTraceHasKindGremlin(this);
        }
        return kind;
    }

    @Override
    public IPropertyTrace getOrCreatePropertyTrace(String name) throws EolIncrementalExecutionException {
        PropertyTraceGremlin propertyTrace = null;
        try {
            Vertex v = g.addV("PropertyTrace").next();
            propertyTrace = new PropertyTraceGremlin(name, this, v, graph);
            this.properties().create(propertyTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyTrace != null) {
    	        return propertyTrace;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModelElementTraceHasPropertiesGremlin) this.properties).getRaw()
                .hasLabel("PropertyTrace")
                .has("name", name)
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyTrace was "
                        + "duplicate but previous one was not found.");
            }
            propertyTrace = new PropertyTraceGremlin();
            propertyTrace.delegate(gt.next());
            propertyTrace.graph(graph);
        }
        return propertyTrace;
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
        if (modelTrace.get() == null) {
            if (other.modelTrace.get() != null)
                return false;
        }
        if (!modelTrace.get().equals(other.modelTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
        result = prime * result + ((modelTrace.get() == null) ? 0 : modelTrace.get().hashCode());
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
    public Graph graph() {
        return graph;    
    }

    @Override
    public void graph(Graph graph) {
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
    }
}
