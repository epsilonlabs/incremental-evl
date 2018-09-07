 /*******************************************************************************
 * This file was automatically generated on: 2018-09-07.
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
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasCheck;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IInvariantTraceHasCheck reference. 
 */
public class InvariantTraceHasCheckGremlin extends Feature
        implements IInvariantTraceHasCheck, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    /** Fast access for single-valued references */
    private Edge delegate;
    
    /**
     * Instantiates a new IInvariantTraceHasCheck.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasCheckGremlin (
        IInvariantTrace source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory; 
    }
    
    // PUBLIC API
        
    @Override
    public ICheckTrace get() {
        if (delegate == null) {
            return null;
        }
        GraphTraversalSource g = startTraversal();
        ICheckTrace result = null;
        try {
            Vertex to = g.E(delegate).outV().next();
            result = (ICheckTrace) factory.createTraceElement(to, gts);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(ICheckTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous ICheckTrace exists");
        }
        target.invariant().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ICheckTrace target) {
        if (!related(target)) {
            return false;
        }
        target.invariant().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ICheckTrace target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
            result |= delegate == null ? g.V(source.getId()).out("check").hasNext() : g.E(delegate).outV().hasId(target.getId()).hasNext();
            result |= target.invariant().get() != null;
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(ICheckTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
		  result = g.E(delegate).outV().hasId(target.getId()).hasNext() && source.equals(target.invariant().get());
		}
		finally {
            finishTraversal(g);
        }
        return result;
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
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
        
    
    // PRIVATE API
    
    @Override
    public void set(ICheckTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            delegate = g.V(source.getId()).addE("check").to(g.V(target.getId())).next();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(ICheckTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.E(delegate).drop();
            delegate = null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
    }
    
    private GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    private void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }

}