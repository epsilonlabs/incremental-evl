 /*******************************************************************************
 * This file was automatically generated on: 2019-01-25.
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
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceHasElements;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import java.util.Map.Entry;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModelTraceHasElements reference. 
 */
public class ModelTraceHasElementsGremlin extends Feature
        implements IModelTraceHasElements, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModelTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    
    /**
     * Instantiates a new IModelTraceHasElements.
     *
     * @param source the source of the reference
     */
    public ModelTraceHasElementsGremlin (
        IModelTrace source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory; 
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IModelElementTrace> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IModelElementTrace, Vertex>(getRaw(),
                gts, factory);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversalSource g = startTraversal();
        GraphTraversal<Vertex, Vertex> result = null;
        try {
            result = g.V(source.getId()).out("elements");
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IModelElementTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IModelElementTrace exists");
        }
        target.modelTrace().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.modelTrace().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
	        if (isUnique()) {
	           GraphTraversal<Vertex, Vertex> gt =  g.V(source.getId()).out("elements");
                for (Entry<String, Object> id : target.getIdProperties().entrySet()) {
                    gt.has(id.getKey(), id.getValue());
                }
                result |= gt.hasNext();
            }
            result |= target.modelTrace().get() != null;
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
		  result = g.V(source.getId()).out("elements").hasId(target.getId()).hasNext() && source.equals(target.modelTrace().get());
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
    public void set(IModelElementTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).addE("elements").to(g.V(target.getId())).iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).outE("elements").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
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