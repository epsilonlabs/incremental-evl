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

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IAllInstancesAccess. 
 */
public class AllInstancesAccessGremlin implements IAllInstancesAccess, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The executionTrace.
     */
    private IAccessHasExecutionTrace executionTrace;

    /**
     * The type.
     */
    private IAllInstancesAccessHasType type;

    /**
     * Empty constructor for deserialization.
     */    
    public AllInstancesAccessGremlin() {
    }
    
    /**
     * Instantiates a new AllInstancesAccessGremlin. The AllInstancesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccessGremlin(
        Boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("ofKind", ofKind)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.type = new AllInstancesAccessHasTypeGremlin(this, gts);
        this.executionTrace = new AccessHasExecutionTraceGremlin(this, gts);
        try {
	        this.type.create(type);
	        this.executionTrace.create(executionTrace);
        } catch (TraceModelConflictRelation ex) {
            ((AllInstancesAccessHasTypeGremlin)this.type).delegate().remove();
            ((AccessHasExecutionTraceGremlin)this.executionTrace).delegate().remove();
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
    public Boolean getOfKind() {
        GraphTraversalSource g = startTraversal();
        Boolean result = null;
        try {
	        try {
	            result = (Boolean) g.V(delegate).values("ofKind").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region ofKind on begin **/
            // TODO Add default return value for AllInstancesAccessGremlin.getgetOfKind
            throw new IllegalStateException(ex);
            /** protected region ofKind end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        if (executionTrace == null) {
            executionTrace = new AccessHasExecutionTraceGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("executionTrace");
                if (gt.hasNext()) {
                    ((AccessHasExecutionTraceGremlin)executionTrace).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return executionTrace;
    }

    @Override
    public IAllInstancesAccessHasType type() {
        if (type == null) {
            type = new AllInstancesAccessHasTypeGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("type");
                if (gt.hasNext()) {
                    ((AllInstancesAccessHasTypeGremlin)type).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return type;
    }

    @Override
    public boolean sameIdentityAs(final IAllInstancesAccess other) {
        if (other == null) {
            return false;
        }
        Boolean ofKind = Boolean.valueOf(getOfKind());
        Boolean otherOfKind = Boolean.valueOf(other.getOfKind());
        if (ofKind == null) {
            if (otherOfKind != null)
                return false;
        } else if (!ofKind.equals(otherOfKind)) {
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
        if (!(obj instanceof AllInstancesAccessGremlin))
            return false;
        AllInstancesAccessGremlin other = (AllInstancesAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (type.get() == null) {
            if (other.type.get() != null)
                return false;
        }
        if (!type.get().equals(other.type.get())) {
            return false;
        }
        if (executionTrace.get() == null) {
            if (other.executionTrace.get() != null)
                return false;
        }
        if (!executionTrace.get().equals(other.executionTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Boolean ofKind = Boolean.valueOf(getOfKind());
        result = prime * result + ((ofKind == null) ? 0 : ofKind.hashCode());
        result = prime * result + ((type.get() == null) ? 0 : type.get().hashCode());
        result = prime * result + ((executionTrace.get() == null) ? 0 : executionTrace.get().hashCode());
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
