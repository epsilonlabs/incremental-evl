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
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
/** protected region GuardResultImports on begin **/
/** protected region GuardResultImports end **/
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
 * Implementation of IGuardResult. 
 */
@SuppressWarnings("unused") 
public class GuardResultGremlin implements IGuardResult, TinkerpopDelegate<Vertex> {
    
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
     * The value.
     */
    private Boolean value;

    
    /**
     * The context.
     */
    private IGuardResultHasContext context;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public GuardResultGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.context = new GuardResultHasContextGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new GuardResultGremlin. The GuardResultGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardResultGremlin(
        IExecutionContext context,
        IGuardTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.context = new GuardResultHasContextGremlin(this, gts, wrapperFactory);
        if (!container.result().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        try {
            this.context.create(context);
        } catch (TraceModelConflictRelation ex) {
            ((GuardResultHasContextGremlin)this.context).delegate().remove();
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
        if (value == null) {
	        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		        try {
		            value = (Boolean) agts.V(delegate).values("value").next();
		        } catch (NoSuchElementException ex) {
		            /** protected region value on begin **/
	            	value = false;
	            	/** protected region value end **/
		        }
		    } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
	    }    
        return value;
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
    public IGuardResultHasContext context() {
        
        return context;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IGuardResult other) {
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
        if (!(obj instanceof GuardResultGremlin))
            return false;
        GuardResultGremlin other = (GuardResultGremlin) obj;
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
