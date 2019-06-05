 /*******************************************************************************
 * This file was automatically generated on: 2019-06-04.
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
/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyAccess. 
 */
@SuppressWarnings("unused") 
public class PropertyAccessGremlin implements IPropertyAccess, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The module.
     */
    private IAccessHasModule module;

    /**
     * The from.
     */
    private IAccessHasFrom from;

    /**
     * The in.
     */
    private IAccessHasIn in;

    /**
     * The property.
     */
    private IPropertyAccessHasProperty property;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public PropertyAccessGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.module = new AccessHasModuleGremlin(this, gts, wrapperFactory);
        this.property = new PropertyAccessHasPropertyGremlin(this, gts, wrapperFactory);
        this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new PropertyAccessGremlin. The PropertyAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessGremlin(
        IModuleElementTrace from,
        IExecutionContext in,
        IPropertyTrace property,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
        this.module = new AccessHasModuleGremlin(this, gts, wrapperFactory);
        this.property = new PropertyAccessHasPropertyGremlin(this, gts, wrapperFactory);
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        try {
            this.from.create(from);
            this.in.create(in);
            this.property.create(property);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasFromGremlin)this.from).delegate().remove();
            ((AccessHasInGremlin)this.in).delegate().remove();
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
    public Object getValue() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("value");
            if (values.hasNext()) {
                return (Object) values.next().value();
            }
            else {
                /** protected region value on begin **/
	            // TODO Add default return value for PropertyAccessGremlin.getValue
	            throw new IllegalStateException("Add default return value for PropertyAccessGremlin.getValue");
	            /** protected region value end **/
            }
            
        }
        return null;
    }
    
    
    @Override
    public void setValue(java.lang.Object value) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate).property("value", value).iterate();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
  
    }   
     
    @Override
    public IAccessHasModule module() {
        
        return module;
    }

    @Override
    public IAccessHasFrom from() {
        
        return from;
    }

    @Override
    public IAccessHasIn in() {
        
        return in;
    }

    @Override
    public IPropertyAccessHasProperty property() {
        
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
    if (module == null) {
        if (other.module != null)
            return false;
    }
        if (!module().get().equals(other.module().get())) {
            return false;
        }
    if (property == null) {
        if (other.property != null)
            return false;
    }
        if (!property().get().equals(other.property().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IModuleExecutionTrace module = module().get();
        result = prime * result + ((module == null) ? 0 : module.hashCode());
        IPropertyTrace property = property().get();
        result = prime * result + ((property == null) ? 0 : property.hashCode());
        return result;
    }
    
    @Override
    public Vertex delegate() {
        return delegate;
    }
    
    @Override
    public GraphTraversalSource graphTraversalSource() {
        return this.gts;
    }
    
}
