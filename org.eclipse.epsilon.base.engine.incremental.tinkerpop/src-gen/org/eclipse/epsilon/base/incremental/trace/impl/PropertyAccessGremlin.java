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
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
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
     * The from.
     */
    private IAccessHasFrom from;

    /**
     * The property.
     */
    private IPropertyAccessHasProperty property;

    /**
     * Empty constructor for deserialization.
     */    
    public PropertyAccessGremlin() { }
    
    /**
     * Instantiates a new PropertyAccessGremlin. The PropertyAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessGremlin(
        IModuleElementTrace from, IPropertyTrace property, IExecutionContext container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.property = new PropertyAccessHasPropertyGremlin(this, gts, BaseTraceFactory.getFactory());
        this.from = new AccessHasFromGremlin(this, gts, BaseTraceFactory.getFactory());
        try {
	        this.property.create(property);
	        this.from.create(from);
        } catch (TraceModelConflictRelation ex) {
            ((PropertyAccessHasPropertyGremlin)this.property).delegate().remove();
            ((AccessHasFromGremlin)this.from).delegate().remove();
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
	            // TODO Add default return value for PropertyAccessGremlin.getValue
	            throw new IllegalStateException("Add default return value for PropertyAccessGremlin.getValue", ex);
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
    public IPropertyAccessHasProperty property() {
        if (property == null) {
            property = new PropertyAccessHasPropertyGremlin(this, this.gts, BaseTraceFactory.getFactory());
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

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
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
    if (property == null) {
        if (other.property != null)
            return false;
    }
        if (!property().get().equals(other.property().get())) {
            return false;
        }
    if (from == null) {
        if (other.from != null)
            return false;
    }
        if (!from().get().equals(other.from().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IPropertyTrace property = property().get();
        result = prime * result + ((property == null) ? 0 : property.hashCode());
        IModuleElementTrace from = from().get();
        result = prime * result + ((from == null) ? 0 : from.hashCode());
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
