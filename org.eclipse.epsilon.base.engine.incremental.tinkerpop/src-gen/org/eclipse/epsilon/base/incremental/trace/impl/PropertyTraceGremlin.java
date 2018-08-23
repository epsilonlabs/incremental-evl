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
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region PropertyTraceImports on begin **/
/** protected region PropertyTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyTrace. 
 */
public class PropertyTraceGremlin implements IPropertyTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The elementTrace.
     */
    private IPropertyTraceHasElementTrace elementTrace;

    /**
     * Empty constructor for deserialization.
     */    
    public PropertyTraceGremlin() {
    }
    
    /**
     * Instantiates a new PropertyTraceGremlin. The PropertyTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyTraceGremlin(String name, IModelElementTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("name", name)
            .iterate();
        this.elementTrace = new PropertyTraceHasElementTraceGremlin(this);

        if (!container.properties().create(this)) {
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
    public IPropertyTraceHasElementTrace elementTrace() {
        if (elementTrace == null) {
            this.elementTrace = new PropertyTraceHasElementTraceGremlin(this);
        }
        return elementTrace;
    }

    @Override
    public boolean sameIdentityAs(final IPropertyTrace other) {
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
        if (!(obj instanceof PropertyTraceGremlin))
            return false;
        PropertyTraceGremlin other = (PropertyTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (elementTrace.get() == null) {
            if (other.elementTrace.get() != null)
                return false;
        }
        if (!elementTrace.get().equals(other.elementTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((elementTrace.get() == null) ? 0 : elementTrace.get().hashCode());
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
