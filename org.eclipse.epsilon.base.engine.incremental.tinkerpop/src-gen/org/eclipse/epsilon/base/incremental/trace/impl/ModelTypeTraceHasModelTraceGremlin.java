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
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTraceHasModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IModelTypeTraceHasModelTrace reference. 
 */
public class ModelTypeTraceHasModelTraceGremlin extends Feature
        implements IModelTypeTraceHasModelTrace, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModelTypeTrace source;
    
    /** Fast access for single-valued references */
    private Edge delegate;
 
    
    /**
     * Instantiates a new IModelTypeTraceHasModelTrace.
     *
     * @param source the source of the reference
     */
    public ModelTypeTraceHasModelTraceGremlin (IModelTypeTrace source, GraphTraversalSource gts) {
        super(true);
        this.source = source;
        this.gts = gts;
    }
    
    // PUBLIC API
        
    @Override
    public IModelTrace get() {
        if (delegate == null) {
            return null;
        }
        GraphTraversalSource g = startTraversal();
        IModelTrace result = null;
        try {
            Vertex to = g.E(delegate).outV().next();
            result = (IModelTrace) BaseTraceFactory.getFactory().createModuleElementTrace(to, gts);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IModelTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IModelTrace exists");
        }
        target.types().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTrace target) {
        if (!related(target)) {
            return false;
        }
        target.types().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTrace target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
            result |= delegate == null ? g.V(source.getId()).out("modelTrace").hasNext() : g.E(delegate).outV().hasId(target.getId()).hasNext();
            GraphTraversalSource g2 = startTraversal();
            try {
                result |= delegate == null ? false : (target.types().isUnique() &&
                        g.V(target.getId()).out("types").hasId(source.getId()).hasNext());
            }
            catch (Exception ex) {
                result = false;
            }
            finally {
                finishTraversal(g2);
            }
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        boolean inTarget = false;
        try {
            inTarget = g.V(target.getId()).out("types").hasId(source.getId()).hasNext();
        }
        finally {
            finishTraversal(g);
        }
        g = startTraversal();
        try {
		  result = g.E(delegate).outV().hasId(target.getId()).hasNext() && inTarget;
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
    public void set(IModelTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            delegate = g.V(source.getId()).addE("modelTrace").to(g.V(target.getId())).next();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IModelTrace target) {
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