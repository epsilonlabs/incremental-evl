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

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import org.eclipse.epsilon.evl.incremental.util.TraceFactory;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IGuardedElementTraceHasGuard reference. 
 */
public class GuardedElementTraceHasGuardGremlin extends Feature
        implements IGuardedElementTraceHasGuard, GremlinWrapper<Edge> {
    
    /** A reference to the graph to use in iterations */
    private Graph graph;
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The source(s) of the reference */
    protected IGuardedElementTrace source;
    
    /** Fast access for single-valued references */
    private Edge delegate;
 
    
    /**
     * Instantiates a new IGuardedElementTraceHasGuard.
     *
     * @param source the source of the reference
     */
    public GuardedElementTraceHasGuardGremlin (IGuardedElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IGuardTrace get() {
        Vertex to = g.E(delegate).outV().next();
        /*
        GuardTraceGremlin retVal = new GuardTraceGremlin();
        retVal.delegate(to);
        retVal.graph(graph);
        */
        return (IGuardTrace) TraceFactory.createModuleElementTrace(to, graph);
    }
    

    @Override
    public boolean create(IGuardTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.limits().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardTrace target) {
        if (!related(target)) {
            return false;
        }
        target.limits().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardTrace target) {
        boolean result = false;
        result |= g.E(delegate).outV().hasId(target.getId()).hasNext();
        result |= target.limits().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IGuardTrace target) {
    	if (target == null) {
			return false;
		}
		return g.E(delegate).outV().id().next().equals(target.getId()) && source.equals(target.limits().get());
	}
	
	@Override
    public Edge delegate() {
        return delegate;
    }

    @Override
    public void delegate(Edge delegate) {
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
        
    
    // PRIVATE API
    
    @Override
    public void set(IGuardTrace target) {
        delegate = g.V(source.getId()).addE("modelTrace").to(g.V(target.getId())).next();
    }
    
    @Override
    public void remove(IGuardTrace target) {
        g.E(delegate).drop();
        delegate = null;
    }

}