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
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasKind;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import java.util.Map.Entry;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModelElementTraceHasKind reference. 
 */
public class ModelElementTraceHasKindGremlin extends Feature
        implements IModelElementTraceHasKind, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    
    /**
     * Instantiates a new IModelElementTraceHasKind.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasKindGremlin (
        IModelElementTrace source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory; 
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IModelTypeTrace> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IModelTypeTrace, Vertex>(getRaw(),
                gts, factory);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversalSource g = startTraversal();
        GraphTraversal<Vertex, Vertex> result = null;
        try {
            result = g.V(source.getId()).out("kind");
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IModelTypeTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IModelTypeTrace exists");
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
        GraphTraversalSource g = startTraversal();
        try {
	        if (isUnique()) {
	           GraphTraversal<Vertex, Vertex> gt =  g.V(source.getId()).out("kind");
                for (Entry<String, Object> id : target.getIdProperties().entrySet()) {
                    gt.has(id.getKey(), id.getValue());
                }
                result |= gt.hasNext();
            }
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelTypeTrace target) {
    	if (target == null) {
			return false;
		}
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
		  result = g.V(source.getId()).out("kind").hasId(target.getId()).hasNext();
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
    public void set(IModelTypeTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).addE("kind").to(g.V(target.getId())).iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IModelTypeTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).outE("kind").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
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