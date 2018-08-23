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
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelElementVariableImports on begin **/
/** protected region ModelElementVariableImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementVariable. 
 */
public class ModelElementVariableGremlin implements IModelElementVariable, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The value.
     */
    private IModelElementVariableHasValue value;

    /**
     * Empty constructor for deserialization.
     */    
    public ModelElementVariableGremlin() {
    }
    
    /**
     * Instantiates a new ModelElementVariableGremlin. The ModelElementVariableGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementVariableGremlin(String name, IModelElementTrace value, IExecutionContext container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("name", name)
            .iterate();
        this.value = new ModelElementVariableHasValueGremlin(this);
        if (!this.value.create(value)) {
            throw new TraceModelDuplicateRelation();
        }

        if (!container.contextVariables().create(this)) {
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
    public String getName() {
        return (String) g.V(delegate).values("name").next();
    }
    
    @Override
    public IModelElementVariableHasValue value() {
        if (value == null) {
            this.value = new ModelElementVariableHasValueGremlin(this);
        }
        return value;
    }

    @Override
    public boolean sameIdentityAs(final IModelElementVariable other) {
        if (other == null) {
            return false;
        }
        String name = getName();
        String otherName = other.getName();
        if (name == null) {
            if (otherName != null)
                return false;
        } else if (!name.equals(otherName)) {
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
        if (!(obj instanceof ModelElementVariableGremlin))
            return false;
        ModelElementVariableGremlin other = (ModelElementVariableGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (value.get() == null) {
            if (other.value.get() != null)
                return false;
        }
        if (!value.get().equals(other.value.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((value.get() == null) ? 0 : value.get().hashCode());
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
