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
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelTraceImports on begin **/
/** protected region ModelTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelTrace. 
 */
public class ModelTraceGremlin implements IModelTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
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
    public ModelTraceGremlin(String uri, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("uri", uri)
            .iterate();
        this.elements = new ModelTraceHasElementsGremlin(this);
        this.types = new ModelTraceHasTypesGremlin(this);

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
    public IModelTraceHasElements elements() {
        if (elements == null) {
            this.elements = new ModelTraceHasElementsGremlin(this);
        }
        return elements;
    }

    @Override
    public IModelTraceHasTypes types() {
        if (types == null) {
            this.types = new ModelTraceHasTypesGremlin(this);
        }
        return types;
    }

    @Override
    public IModelElementTrace getOrCreateModelElementTrace(String uri, IModelTypeTrace type) throws EolIncrementalExecutionException {
        ModelElementTraceGremlin modelElementTrace = null;
        try {
            Vertex v = g.addV("ModelElementTrace").next();
            modelElementTrace = new ModelElementTraceGremlin(uri, type, this, v, graph);
            this.elements().create(modelElementTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelElementTrace != null) {
    	        return modelElementTrace;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModelTraceHasElementsGremlin) this.elements).getRaw()
                .hasLabel("ModelElementTrace")
                .has("uri", uri)
                .as("v")
                .to(Direction.OUT, "value").hasId(type.getId())
                .select("v")
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElementTrace was "
                        + "duplicate but previous one was not found.");
            }
            modelElementTrace = new ModelElementTraceGremlin();
            modelElementTrace.delegate(gt.next());
            modelElementTrace.graph(graph);
        }
        return modelElementTrace;
    }      
                  
    @Override
    public IModelTypeTrace getOrCreateModelTypeTrace(String name) throws EolIncrementalExecutionException {
        ModelTypeTraceGremlin modelTypeTrace = null;
        try {
            Vertex v = g.addV("ModelTypeTrace").next();
            modelTypeTrace = new ModelTypeTraceGremlin(name, this, v, graph);
            this.types().create(modelTypeTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelTypeTrace != null) {
    	        return modelTypeTrace;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModelTraceHasTypesGremlin) this.types).getRaw()
                .hasLabel("ModelTypeTrace")
                .has("name", name)
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelTypeTrace was "
                        + "duplicate but previous one was not found.");
            }
            modelTypeTrace = new ModelTypeTraceGremlin();
            modelTypeTrace.delegate(gt.next());
            modelTypeTrace.graph(graph);
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
    public Graph graph() {
        return graph;    
    }

    @Override
    public void graph(Graph graph) {
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
    }
}
