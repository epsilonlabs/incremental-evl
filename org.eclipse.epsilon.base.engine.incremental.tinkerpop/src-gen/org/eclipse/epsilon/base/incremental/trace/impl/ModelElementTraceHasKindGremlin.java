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
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasKind;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.trace.gremlin.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModelElementTraceHasKind reference. 
 */
public class ModelElementTraceHasKindGremlin extends Feature
        implements IModelElementTraceHasKind, GremlinWrapper<Edge> {
    
    /** A reference to the graph to use in iterations */
    private Graph graph;
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
 
    
    /**
     * Instantiates a new IModelElementTraceHasKind.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasKindGremlin (IModelElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IModelTypeTrace> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IModelTypeTrace, Vertex>(getRaw(), graph);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        return g.V(source.getId()).outE("moduleElements").toV(Direction.OUT);
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
        if (isUnique) {
            result |= g.V(source.getId()).out("kind").hasId(target.getId()).hasNext();
        }
        return result;
    }
    
    @Override
    public boolean related(IModelTypeTrace target) {
    	if (target == null) {
			return false;
		}
		return g.V(source.getId()).out("kind").hasId(target.getId()).hasNext();
	}
	
	@Override
    public Edge delegate() {
        return null;
    }

    @Override
    public void delegate(Edge delegate) {
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
        g.V(source.getId()).addE("elements").to(g.V(target.getId())).iterate();
    }
    
    @Override
    public void remove(IModelTypeTrace target) {
        g.V(source.getId()).outE("elements").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
    }

}