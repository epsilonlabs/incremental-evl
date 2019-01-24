 /*******************************************************************************
 * This file was automatically generated on: 2019-01-23.
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region ElementAccessImports on begin **/
/** protected region ElementAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IElementAccess. 
 */
public class ElementAccessGremlin implements IElementAccess, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The from.
     */
    private IAccessHasFrom from;

    /**
     * The element.
     */
    private IElementAccessHasElement element;

    /**
     * Empty constructor for deserialization.
     */    
    public ElementAccessGremlin() { }
    
    /**
     * Instantiates a new ElementAccessGremlin. The ElementAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ElementAccessGremlin(
        IModelElementTrace element, IExecutionContext container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.from = new AccessHasFromGremlin(this, gts, BaseTraceFactory.getFactory());
        this.element = new ElementAccessHasElementGremlin(this, gts, BaseTraceFactory.getFactory());
        try {
	        this.element.create(element);
        } catch (TraceModelConflictRelation ex) {
            ((ElementAccessHasElementGremlin)this.element).delegate().remove();
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
    public IElementAccessHasElement element() {
        if (element == null) {
            element = new ElementAccessHasElementGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("element");
                if (gt.hasNext()) {
                    ((ElementAccessHasElementGremlin)element).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return element;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IElementAccess other) {
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
        if (!(obj instanceof ElementAccessGremlin))
            return false;
        ElementAccessGremlin other = (ElementAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (from == null) {
        if (other.from != null)
            return false;
    }
        if (!from().get().equals(other.from().get())) {
            return false;
        }
    if (element == null) {
        if (other.element != null)
            return false;
    }
        if (!element().get().equals(other.element().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IExecutionContext from = from().get();
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        IModelElementTrace element = element().get();
        result = prime * result + ((element == null) ? 0 : element.hashCode());
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
    
    protected GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    protected void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }
}
