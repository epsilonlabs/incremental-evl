 /*******************************************************************************
 * This file was automatically generated on: 2018-09-13.
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


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region GuardResultImports on begin **/
/** protected region GuardResultImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IGuardResult. 
 */
public class GuardResultGremlin implements IGuardResult, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The context.
     */
    private IGuardResultHasContext context;

    /**
     * Empty constructor for deserialization.
     */    
    public GuardResultGremlin() { }
    
    /**
     * Instantiates a new GuardResultGremlin. The GuardResultGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardResultGremlin(
        IExecutionContext context, IGuardTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.result().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.context = new GuardResultHasContextGremlin(this, gts, EvlTraceFactory.getFactory());
        try {
	        this.context.create(context);
        } catch (TraceModelConflictRelation ex) {
            ((GuardResultHasContextGremlin)this.context).delegate().remove();
            throw ex;
        }
    }
    
    @Override
    public Object getId() {
        return (Object) delegate == null ? null : delegate.id();
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
        throw new UnsupportedOperationException("Id is final");
  
    }   
     
    @Override
    public Boolean getValue() {
        GraphTraversalSource g = startTraversal();
        Boolean result = null;
        try {
	        try {
	            result = (Boolean) g.V(delegate).values("value").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region value on begin **/
	            result = false;
	            /** protected region value end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    
    @Override
    public void setValue(boolean value) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate).property("value", value).iterate();
        } finally {
            finishTraversal(g);
        }
  
    }   
     
    @Override
    public IGuardResultHasContext context() {
        if (context == null) {
            context = new GuardResultHasContextGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("context");
                if (gt.hasNext()) {
                    ((GuardResultHasContextGremlin)context).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return context;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IGuardResult other) {
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
        if (!(obj instanceof GuardResultGremlin))
            return false;
        GuardResultGremlin other = (GuardResultGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
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
