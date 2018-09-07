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

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
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
     * The from.
     */
    private IAccessHasFrom from;

    /**
     * The type.
     */
    private IAllInstancesAccessHasType type;

    /**
     * Empty constructor for deserialization.
     */    
    public AllInstancesAccessGremlin() { }
    
    /**
     * Instantiates a new AllInstancesAccessGremlin. The AllInstancesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccessGremlin(
        Boolean ofKind, IModuleElementTrace from, IModelTypeTrace type, IExecutionContext container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
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
        this.from = new AccessHasFromGremlin(this, gts, BaseTraceFactory.getFactory());
        this.type = new AllInstancesAccessHasTypeGremlin(this, gts, BaseTraceFactory.getFactory());
        try {
	        this.from.create(from);
	        this.type.create(type);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasFromGremlin)this.from).delegate().remove();
            ((AllInstancesAccessHasTypeGremlin)this.type).delegate().remove();
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
	            // TODO Add default return value for AllInstancesAccessGremlin.getOfKind
	            throw new IllegalStateException("Add default return value for AllInstancesAccessGremlin.getOfKind", ex);
	            /** protected region ofKind end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IAccessHasFrom from() {
        if (from == null) {
            from = new AccessHasFromGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("from");
                if (gt.hasNext()) {
                    ((AccessHasFromGremlin)from).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return from;
    }

    @Override
    public IAllInstancesAccessHasType type() {
        if (type == null) {
            type = new AllInstancesAccessHasTypeGremlin(this, this.gts, BaseTraceFactory.getFactory());
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

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("ofKind", getOfKind());
        return result;
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
    if (from == null) {
        if (other.from != null)
            return false;
    }
        if (!from().get().equals(other.from().get())) {
            return false;
        }
    if (type == null) {
        if (other.type != null)
            return false;
    }
        if (!type().get().equals(other.type().get())) {
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
        result = prime * result + ((from().get() == null) ? 0 : from().get().hashCode());
        result = prime * result + ((type().get() == null) ? 0 : type().get().hashCode());
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
