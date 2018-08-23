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
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ElementAccessImports on begin **/
/** protected region ElementAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IElementAccess. 
 */
public class ElementAccessGremlin implements IElementAccess, GremlinWrapper<Vertex> {
    
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
     * The element.
     */
    private IElementAccessHasElement element;

    /**
     * Empty constructor for deserialization.
     */    
    public ElementAccessGremlin() {
    }
    
    /**
     * Instantiates a new ElementAccessGremlin. The ElementAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ElementAccessGremlin(IModuleElementTrace executionTrace, IModelElementTrace element, IModuleExecutionTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .iterate();
        this.element = new ElementAccessHasElementGremlin(this);
        if (!this.element.create(element)) {
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
    public IAccessHasExecutionTrace executionTrace() {
        if (executionTrace == null) {
            this.executionTrace = new AccessHasExecutionTraceGremlin(this);
        }
        return executionTrace;
    }

    @Override
    public IElementAccessHasElement element() {
        if (element == null) {
            this.element = new ElementAccessHasElementGremlin(this);
        }
        return element;
    }

    @Override
    public boolean sameIdentityAs(final IElementAccess other) {
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
        if (!(obj instanceof ElementAccessGremlin))
            return false;
        ElementAccessGremlin other = (ElementAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (element.get() == null) {
            if (other.element.get() != null)
                return false;
        }
        if (!element.get().equals(other.element.get())) {
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
        result = prime * result + ((element.get() == null) ? 0 : element.get().hashCode());
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
