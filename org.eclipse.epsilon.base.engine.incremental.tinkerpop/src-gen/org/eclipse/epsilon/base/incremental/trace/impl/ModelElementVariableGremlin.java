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
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region ModelElementVariableImports on begin **/
/** protected region ModelElementVariableImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementVariable. 
 */
public class ModelElementVariableGremlin implements IModelElementVariable, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The value.
     */
    private IModelElementVariableHasValue value;

    /**
     * Empty constructor for deserialization.
     */    
    public ModelElementVariableGremlin() { }
    
    /**
     * Instantiates a new ModelElementVariableGremlin. The ModelElementVariableGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementVariableGremlin(
        String name, IModelElementTrace value, IExecutionContext container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("name", name)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        if (!container.contextVariables().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.value = new ModelElementVariableHasValueGremlin(this, gts, BaseTraceFactory.getFactory());
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
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("name").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region name on begin **/
	            // TODO Add default return value for ModelElementVariableGremlin.getName
	            throw new IllegalStateException("Add default return value for ModelElementVariableGremlin.getName", ex);
	            /** protected region name end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IModelElementVariableHasValue value() {
        if (value == null) {
            value = new ModelElementVariableHasValueGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("value");
                if (gt.hasNext()) {
                    ((ModelElementVariableHasValueGremlin)value).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
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
        result = prime * result + ((value().get() == null) ? 0 : value().get().hashCode());
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
