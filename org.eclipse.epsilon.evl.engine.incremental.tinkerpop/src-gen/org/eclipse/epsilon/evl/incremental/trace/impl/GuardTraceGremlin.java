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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region GuardTraceImports on begin **/
/** protected region GuardTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IGuardTrace. 
 */
public class GuardTraceGremlin implements IGuardTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private IModuleElementTraceHasAccesses accesses;

    /**
     * The limits.
     */
    private IGuardTraceHasLimits limits;

    /**
     * Empty constructor for deserialization.
     */    
    public GuardTraceGremlin() {
    }
    
    /**
     * Instantiates a new GuardTraceGremlin. The GuardTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardTraceGremlin(IGuardedElementTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .iterate();
        this.limits = new GuardTraceHasLimitsGremlin(this);
        this.accesses = new ModuleElementTraceHasAccessesGremlin(this);

        if (!container.guard().create(this)) {
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
    public boolean getResult() {
        return (boolean) g.V(delegate).values("result").next();
    }
    
    
    @Override
    public void setResult(boolean value) {
        g.V(delegate).property("result", value).iterate();
    }   
     
    @Override
    public IModuleElementTraceHasAccesses accesses() {
        if (accesses == null) {
            this.accesses = new ModuleElementTraceHasAccessesGremlin(this);
        }
        return accesses;
    }

    @Override
    public IGuardTraceHasLimits limits() {
        if (limits == null) {
            this.limits = new GuardTraceHasLimitsGremlin(this);
        }
        return limits;
    }

    @Override
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
        return null;
        /** protected region parentTrace end **/
    }

    @Override
    public boolean sameIdentityAs(final IGuardTrace other) {
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
        if (!(obj instanceof GuardTraceGremlin))
            return false;
        GuardTraceGremlin other = (GuardTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (limits.get() == null) {
            if (other.limits.get() != null)
                return false;
        }
        if (!limits.get().equals(other.limits.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((limits.get() == null) ? 0 : limits.get().hashCode());
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
