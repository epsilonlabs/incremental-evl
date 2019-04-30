 /*******************************************************************************
 * This file was automatically generated on: 2019-04-30.
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
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
/** protected region ModelAccessImports on begin **/
/** protected region ModelAccessImports end **/
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
 * Implementation of IModelAccess. 
 */
@SuppressWarnings("unused") 
public class ModelAccessGremlin implements IModelAccess, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The id.
     */
    private Object id;

    /**
     * The modelName.
     */
    private String modelName;

    
    /**
     * The modelTrace.
     */
    private IModelAccessHasModelTrace modelTrace;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ModelAccessGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.modelTrace = new ModelAccessHasModelTraceGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new ModelAccessGremlin. The ModelAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelAccessGremlin(
        String modelName,
        IModelTrace modelTrace,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("modelName", modelName)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        this.modelTrace = new ModelAccessHasModelTraceGremlin(this, gts, wrapperFactory);
        if (!container.models().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        try {
            this.modelTrace.create(modelTrace);
        } catch (TraceModelConflictRelation ex) {
            ((ModelAccessHasModelTraceGremlin)this.modelTrace).delegate().remove();
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
    public String getModelName() {
        if (modelName == null) {
	        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		        try {
		            modelName = (String) agts.V(delegate).values("modelName").next();
		        } catch (NoSuchElementException ex) {
		            /** protected region modelName on begin **/
	            // TODO Add default return value for ModelAccessGremlin.getModelName
	            throw new IllegalStateException("Add default return value for ModelAccessGremlin.getModelName", ex);
	            /** protected region modelName end **/
		        }
		    } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
	    }    
        return modelName;
    }
    
    @Override
    public IModelAccessHasModelTrace modelTrace() {
        
        return modelTrace;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("modelName", getModelName());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IModelAccess other) {
        if (other == null) {
            return false;
        }
        String modelName = getModelName();
        String otherModelName = other.getModelName();
        if (modelName == null) {
            if (otherModelName != null)
                return false;
        } else if (!modelName.equals(otherModelName)) {
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
        if (!(obj instanceof ModelAccessGremlin))
            return false;
        ModelAccessGremlin other = (ModelAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (modelTrace == null) {
        if (other.modelTrace != null)
            return false;
    }
        if (!modelTrace().get().equals(other.modelTrace().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModelName() == null) ? 0 : getModelName().hashCode());
        IModelTrace modelTrace = modelTrace().get();
        result = prime * result + ((modelTrace == null) ? 0 : modelTrace.hashCode());
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
