 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasResult;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IInvariantTraceHasResult reference. 
 */
public class InvariantTraceHasResultGremlin extends Feature
        implements IInvariantTraceHasResult, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
 
    
    /**
     * Instantiates a new IInvariantTraceHasResult.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasResultGremlin (IInvariantTrace source, GraphTraversalSource gts) {
        super(false);
        this.source = source;
        this.gts = gts;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IGuardResult> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IGuardResult, Vertex>(getRaw(),
                gts, EvlTraceFactory.getFactory());
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversalSource g = startTraversal();
        GraphTraversal<Vertex, Vertex> result = null;
        try {
            result = g.V(source.getId()).out("result");
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IGuardResult target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IInvariantResult exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardResult target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardResult target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
	        // FIXME We can just remove this during generation?
	        if (isUnique()) {
	            result |= g.V(source.getId()).out("result")
                    .hasNext();
            }
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IGuardResult target) {
    	if (target == null) {
			return false;
		}
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
		  result = g.V(source.getId()).out("result").hasId(target.getId()).hasNext();
		}
		finally {
            finishTraversal(g);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return null;
    }

    @Override
    public void delegate(Edge delegate) {
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
        
    
    // PRIVATE API
    
    @Override
    public void set(IGuardResult target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).addE("result").to(g.V(target.getId())).iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IGuardResult target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).outE("result").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
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