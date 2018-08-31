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
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ElementAccessImports on begin **/
/** protected region ElementAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

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
     * The executionTrace.
     */
    private IAccessHasExecutionTrace executionTrace;

    /**
     * The element.
     */
    private IElementAccessHasElement element;

    /**
     * Empty constructor for deserialization.
     */    
    public ElementAccessGremlin() {
    }
    
    /**
     * Instantiates a new ElementAccessGremlin. The ElementAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ElementAccessGremlin(
        IModuleElementTrace executionTrace, IModelElementTrace element, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.executionTrace = new AccessHasExecutionTraceGremlin(this, gts);
        this.element = new ElementAccessHasElementGremlin(this, gts);
        try {
	        this.executionTrace.create(executionTrace);
	        this.element.create(element);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasExecutionTraceGremlin)this.executionTrace).delegate().remove();
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
    public IElementAccessHasElement element() {
        if (element == null) {
            element = new ElementAccessHasElementGremlin(this, this.gts);
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
        if (executionTrace.get() == null) {
            if (other.executionTrace.get() != null)
                return false;
        }
        if (!executionTrace.get().equals(other.executionTrace.get())) {
            return false;
        }
        if (element.get() == null) {
            if (other.element.get() != null)
                return false;
        }
        if (!element.get().equals(other.element.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((executionTrace.get() == null) ? 0 : executionTrace.get().hashCode());
        result = prime * result + ((element.get() == null) ? 0 : element.get().hashCode());
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
