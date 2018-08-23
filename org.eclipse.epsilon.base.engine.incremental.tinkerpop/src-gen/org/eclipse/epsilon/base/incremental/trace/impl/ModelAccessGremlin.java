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
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelAccessImports on begin **/
/** protected region ModelAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelAccess. 
 */
public class ModelAccessGremlin implements IModelAccess, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The modelTrace.
     */
    private IModelAccessHasModelTrace modelTrace;

    /**
     * Empty constructor for deserialization.
     */    
    public ModelAccessGremlin() {
    }
    
    /**
     * Instantiates a new ModelAccessGremlin. The ModelAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelAccessGremlin(String modelName, IModelTrace modelTrace, IModuleExecutionTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("modelName", modelName)
            .iterate();
        this.modelTrace = new ModelAccessHasModelTraceGremlin(this);
        if (!this.modelTrace.create(modelTrace)) {
            throw new TraceModelDuplicateRelation();
        }

        if (!container.models().create(this)) {
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
    public String getModelName() {
        return (String) g.V(delegate).values("modelName").next();
    }
    
    @Override
    public IModelAccessHasModelTrace modelTrace() {
        if (modelTrace == null) {
            this.modelTrace = new ModelAccessHasModelTraceGremlin(this);
        }
        return modelTrace;
    }

    @Override
    public boolean sameIdentityAs(final IModelAccess other) {
        if (other == null) {
            return false;
        }
        String modelName = getModelName();
        String otherModelName = other.getModelName();
        if (modelName == null) {
            if (otherModelName != null)
                return false;
        } else if (!modelName.equals(otherModelName)) {
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
        if (!(obj instanceof ModelAccessGremlin))
            return false;
        ModelAccessGremlin other = (ModelAccessGremlin) obj;
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
        result = prime * result + ((getModelName() == null) ? 0 : getModelName().hashCode());
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
