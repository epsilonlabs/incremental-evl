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
package org.eclipse.epsilon.evl.incremental.trace.impl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
/** protected region CheckResultImports on begin **/
/** protected region CheckResultImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ICheckResult. 
 */
@SuppressWarnings("unused") 
public class CheckResultGremlin implements ICheckResult, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The context.
     */
    private ICheckResultHasContext context;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public CheckResultGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.context = new CheckResultHasContextGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new CheckResultGremlin. The CheckResultGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckResultGremlin(
        IExecutionContext context,
        ICheckTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.context = new CheckResultHasContextGremlin(this, gts, wrapperFactory);
        if (!container.result().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        try {
            this.context.create(context);
        } catch (TraceModelConflictRelation ex) {
            ((CheckResultHasContextGremlin)this.context).delegate().remove();
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
    public Boolean getValue() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("value");
            if (values.hasNext()) {
                return (Boolean) values.next().value();
            }
            else {
                /** protected region value on begin **/
		        return false;
		        /** protected region value end **/
            }
            
        }
        return null;
    }
    
    
    @Override
    public void setValue(boolean value) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate).property("value", value).iterate();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
  
    }   
     
    @Override
    public ICheckResultHasContext context() {
        
        return context;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ICheckResult other) {
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
        if (!(obj instanceof CheckResultGremlin))
            return false;
        CheckResultGremlin other = (CheckResultGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
