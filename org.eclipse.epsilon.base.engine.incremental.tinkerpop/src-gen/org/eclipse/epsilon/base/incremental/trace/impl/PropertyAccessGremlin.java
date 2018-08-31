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
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyAccess. 
 */
public class PropertyAccessGremlin implements IPropertyAccess, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The executionTrace.
     */
    private IAccessHasExecutionTrace executionTrace;

    /**
     * The property.
     */
    private IPropertyAccessHasProperty property;

    /**
     * Empty constructor for deserialization.
     */    
    public PropertyAccessGremlin() {
    }
    
    /**
     * Instantiates a new PropertyAccessGremlin. The PropertyAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessGremlin(
        IModuleElementTrace executionTrace, IPropertyTrace property, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.executionTrace = new AccessHasExecutionTraceGremlin(this, gts);
        this.property = new PropertyAccessHasPropertyGremlin(this, gts);
        try {
	        this.executionTrace.create(executionTrace);
	        this.property.create(property);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasExecutionTraceGremlin)this.executionTrace).delegate().remove();
            ((PropertyAccessHasPropertyGremlin)this.property).delegate().remove();
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
    public String getValue() {
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("value").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region value on begin **/
            // TODO Add default return value for PropertyAccessGremlin.getgetValue
            throw new IllegalStateException(ex);
            /** protected region value end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    
    @Override
    public void setValue(java.lang.String value) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate).property("value", value).iterate();
        } finally {
            finishTraversal(g);
        }
  
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
    public IPropertyAccessHasProperty property() {
        if (property == null) {
            property = new PropertyAccessHasPropertyGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("property");
                if (gt.hasNext()) {
                    ((PropertyAccessHasPropertyGremlin)property).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return property;
    }

    @Override
    public boolean sameIdentityAs(final IPropertyAccess other) {
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
        if (!(obj instanceof PropertyAccessGremlin))
            return false;
        PropertyAccessGremlin other = (PropertyAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        if (executionTrace.get() == null) {
            if (other.executionTrace.get() != null)
                return false;
        }
        if (!executionTrace.get().equals(other.executionTrace.get())) {
            return false;
        }
        if (property.get() == null) {
            if (other.property.get() != null)
                return false;
        }
        if (!property.get().equals(other.property.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((executionTrace.get() == null) ? 0 : executionTrace.get().hashCode());
        result = prime * result + ((property.get() == null) ? 0 : property.get().hashCode());
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
