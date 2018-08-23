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
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyAccess. 
 */
public class PropertyAccessGremlin implements IPropertyAccess, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The executionTrace.
     */
    private IAccessHasExecutionTrace executionTrace;

    /**
     * The property.
     */
    private IPropertyAccessHasProperty property;

    /**
     * Empty constructor for deserialization.
     */    
    public PropertyAccessGremlin() {
    }
    
    /**
     * Instantiates a new PropertyAccessGremlin. The PropertyAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessGremlin(IModuleElementTrace executionTrace, IPropertyTrace property, IModuleExecutionTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .iterate();
        this.property = new PropertyAccessHasPropertyGremlin(this);
        if (!this.property.create(property)) {
            throw new TraceModelDuplicateRelation();
        }
        this.executionTrace = new AccessHasExecutionTraceGremlin(this);
        if (!this.executionTrace.create(executionTrace)) {
            throw new TraceModelDuplicateRelation();
        }

        if (!container.accesses().create(this)) {
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
    public String getValue() {
        return (String) g.V(delegate).values("value").next();
    }
    
    
    @Override
    public void setValue(String value) {
        g.V(delegate).property("value", value).iterate();
    }   
     
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        if (executionTrace == null) {
            this.executionTrace = new AccessHasExecutionTraceGremlin(this);
        }
        return executionTrace;
    }

    @Override
    public IPropertyAccessHasProperty property() {
        if (property == null) {
            this.property = new PropertyAccessHasPropertyGremlin(this);
        }
        return property;
    }

    @Override
    public boolean sameIdentityAs(final IPropertyAccess other) {
        if (other == null) {
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
        if (!(obj instanceof PropertyAccessGremlin))
            return false;
        PropertyAccessGremlin other = (PropertyAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (property.get() == null) {
            if (other.property.get() != null)
                return false;
        }
        if (!property.get().equals(other.property.get())) {
            return false;
        }
        if (executionTrace.get() == null) {
            if (other.executionTrace.get() != null)
                return false;
        }
        if (!executionTrace.get().equals(other.executionTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((property.get() == null) ? 0 : property.get().hashCode());
        result = prime * result + ((executionTrace.get() == null) ? 0 : executionTrace.get().hashCode());
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
