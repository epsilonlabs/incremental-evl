 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.trace.gremlin.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModuleElementTraceHasAccesses reference. 
 */
public class ModuleElementTraceHasAccessesGremlin extends Feature
        implements IModuleElementTraceHasAccesses, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModuleElementTrace source;
    
 
    
    /**
     * Instantiates a new IModuleElementTraceHasAccesses.
     *
     * @param source the source of the reference
     */
    public ModuleElementTraceHasAccessesGremlin (IModuleElementTrace source, GraphTraversalSource gts) {
        super(true);
        this.source = source;
        this.gts = gts;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IAccess> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IAccess, Vertex>(getRaw(), gts);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversalSource g = startTraversal();
        GraphTraversal<Vertex, Vertex> result = null;
        try {
            result = g.V(source.getId()).outE("accesses").toV(Direction.OUT);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IAccess target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IAccess exists");
        }
        if (related(target)) {
            return false;
        }
        target.executionTrace().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IAccess target) {
        if (!related(target)) {
            return false;
        }
        target.executionTrace().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IAccess target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
	        // FIXME We can just remove this during generation?
	        if (isUnique()) {
	            result |= g.V(source.getId()).out("accesses")
                    .hasNext();
            }
            result |= target.executionTrace().get() != null;
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
    	if (target == null) {
			return false;
		}
        GraphTraversalSource g = startTraversal();
        boolean result = false;
        try {
		  result = g.V(source.getId()).out("accesses").hasId(target.getId()).hasNext() && source.equals(target.executionTrace().get());
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
    public void set(IAccess target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).addE("accesses").to(g.V(target.getId())).iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IAccess target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).outE("accesses").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
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