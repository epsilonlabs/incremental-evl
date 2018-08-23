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

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccessHasType;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import org.eclipse.epsilon.base.incremental.util.TraceFactory;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IAllInstancesAccessHasType reference. 
 */
public class AllInstancesAccessHasTypeGremlin extends Feature
        implements IAllInstancesAccessHasType, GremlinWrapper<Edge> {
    
    /** A reference to the graph to use in iterations */
    private Graph graph;
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The source(s) of the reference */
    protected IAllInstancesAccess source;
    
    /** Fast access for single-valued references */
    private Edge delegate;
 
    
    /**
     * Instantiates a new IAllInstancesAccessHasType.
     *
     * @param source the source of the reference
     */
    public AllInstancesAccessHasTypeGremlin (IAllInstancesAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelTypeTrace get() {
        Vertex to = g.E(delegate).outV().next();
        /*
        ModelTypeTraceGremlin retVal = new ModelTypeTraceGremlin();
        retVal.delegate(to);
        retVal.graph(graph);
        */
        return (IModelTypeTrace) TraceFactory.createModuleElementTrace(to, graph);
    }
    

    @Override
    public boolean create(IModelTypeTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTypeTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTypeTrace target) {
        boolean result = false;
        result |= g.E(delegate).outV().hasId(target.getId()).hasNext();
        return result;
    }
    
    @Override
    public boolean related(IModelTypeTrace target) {
    	if (target == null) {
			return false;
		}
		return g.E(delegate).outV().id().next().equals(target.getId());
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
    public void set(IModelTypeTrace target) {
        delegate = g.V(source.getId()).addE("modelTrace").to(g.V(target.getId())).next();
    }
    
    @Override
    public void remove(IModelTypeTrace target) {
        g.E(delegate).drop();
        delegate = null;
    }

}