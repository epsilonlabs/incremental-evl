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
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IAllInstancesAccess. 
 */
public class AllInstancesAccessGremlin implements IAllInstancesAccess, GremlinWrapper<Vertex> {
    
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
     * The type.
     */
    private IAllInstancesAccessHasType type;

    /**
     * Empty constructor for deserialization.
     */    
    public AllInstancesAccessGremlin() {
    }
    
    /**
     * Instantiates a new AllInstancesAccessGremlin. The AllInstancesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccessGremlin(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type, IModuleExecutionTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("ofKind", ofKind)
            .iterate();
        this.type = new AllInstancesAccessHasTypeGremlin(this);
        if (!this.type.create(type)) {
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
    public boolean getOfKind() {
        return (boolean) g.V(delegate).values("ofKind").next();
    }
    
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        if (executionTrace == null) {
            this.executionTrace = new AccessHasExecutionTraceGremlin(this);
        }
        return executionTrace;
    }

    @Override
    public IAllInstancesAccessHasType type() {
        if (type == null) {
            this.type = new AllInstancesAccessHasTypeGremlin(this);
        }
        return type;
    }

    @Override
    public boolean sameIdentityAs(final IAllInstancesAccess other) {
        if (other == null) {
            return false;
        }
        Boolean ofKind = Boolean.valueOf(getOfKind());
        Boolean otherOfKind = Boolean.valueOf(other.getOfKind());
        if (ofKind == null) {
            if (otherOfKind != null)
                return false;
        } else if (!ofKind.equals(otherOfKind)) {
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
        if (!(obj instanceof AllInstancesAccessGremlin))
            return false;
        AllInstancesAccessGremlin other = (AllInstancesAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (type.get() == null) {
            if (other.type.get() != null)
                return false;
        }
        if (!type.get().equals(other.type.get())) {
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
        Boolean ofKind = Boolean.valueOf(getOfKind());
        result = prime * result + ((ofKind == null) ? 0 : ofKind.hashCode());
        result = prime * result + ((type.get() == null) ? 0 : type.get().hashCode());
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
