 /*******************************************************************************
 * This file was automatically generated on: 2019-05-09.
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
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
/** protected region ModelElementVariableImports on begin **/
/** protected region ModelElementVariableImports end **/
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
 * Implementation of IModelElementVariable. 
 */
@SuppressWarnings("unused") 
public class ModelElementVariableGremlin implements IModelElementVariable, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The value.
     */
    private IModelElementVariableHasValue value;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ModelElementVariableGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.value = new ModelElementVariableHasValueGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new ModelElementVariableGremlin. The ModelElementVariableGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementVariableGremlin(
        String name,
        IModelElementTrace value,
        IExecutionContext container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("name", name)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        this.value = new ModelElementVariableHasValueGremlin(this, gts, wrapperFactory);
        if (!container.contextVariables().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        try {
            this.value.create(value);
        } catch (TraceModelConflictRelation ex) {
            ((ModelElementVariableHasValueGremlin)this.value).delegate().remove();
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
    public String getName() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("name");
            if (values.hasNext()) {
                return (String) values.next().value();
            }
            else {
                /** protected region name on begin **/
	            // TODO Add default return value for ModelElementVariableGremlin.getName
	            throw new IllegalStateException("Add default return value for ModelElementVariableGremlin.getName");
	            /** protected region name end **/
            }
            
        }
        return null;
    }
    
    @Override
    public IModelElementVariableHasValue value() {
        
        return value;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", getName());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IModelElementVariable other) {
        if (other == null) {
            return false;
        }
        String name = getName();
        String otherName = other.getName();
        if (name == null) {
            if (otherName != null)
                return false;
        } else if (!name.equals(otherName)) {
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
        if (!(obj instanceof ModelElementVariableGremlin))
            return false;
        ModelElementVariableGremlin other = (ModelElementVariableGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (value == null) {
        if (other.value != null)
            return false;
    }
        if (!value().get().equals(other.value().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        IModelElementTrace value = value().get();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
