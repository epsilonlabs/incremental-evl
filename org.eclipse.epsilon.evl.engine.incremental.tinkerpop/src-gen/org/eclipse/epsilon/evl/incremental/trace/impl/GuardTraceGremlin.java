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
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
/** protected region GuardTraceImports on begin **/
/** protected region GuardTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
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
 * Implementation of IGuardTrace. 
 */
@SuppressWarnings("unused") 
public class GuardTraceGremlin implements IGuardTrace, TinkerpopDelegate<Vertex> {
    
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
     * The contextModuleElement.
     */
    private IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The limits.
     */
    private IGuardTraceHasLimits limits;

    /**
     * The result.
     */
    private IGuardTraceHasResult result;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public GuardTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.limits = new GuardTraceHasLimitsGremlin(this, gts, wrapperFactory);
        this.result = new GuardTraceHasResultGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new GuardTraceGremlin. The GuardTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardTraceGremlin(
        IGuardedElementTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.result = new GuardTraceHasResultGremlin(this, gts, wrapperFactory);
        this.limits = new GuardTraceHasLimitsGremlin(this, gts, wrapperFactory);
        if (!container.guard().create(this)) {
            throw new TraceModelDuplicateElement();
        };
    
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
    public IInContextModuleElementTraceHasContextModuleElement contextModuleElement() {
        /** protected region contextModuleElement on begin **/
        throw new UnsupportedOperationException("The reference contextModuleElement is derived and the getter hasn't been implemented");
        /** protected region contextModuleElement end **/
    }

    @Override
    public IGuardTraceHasLimits limits() {
        
        return limits;
    }

    @Override
    public IGuardTraceHasResult result() {
        
        return result;
    }

    @Override
    public IGuardResult getOrCreateGuardResult(IExecutionContext context) throws EolIncrementalExecutionException {    
        GuardResultGremlin guardResult = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            Vertex v = null;
            try {
                v = agts.addV("GuardResult").next();
                /* protected region guardResultTypeOverride on begin */
                guardResult = new GuardResultGremlin(context, this, v, gts, wrapperFactory);
                /* protected region guardResultTypeOverride end */
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                agts.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested GuardResult", e);
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return guardResult;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IGuardTrace other) {
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
        if (!(obj instanceof GuardTraceGremlin))
            return false;
        GuardTraceGremlin other = (GuardTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (limits == null) {
        if (other.limits != null)
            return false;
    }
        if (!limits().get().equals(other.limits().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IGuardedElementTrace limits = limits().get();
        result = prime * result + ((limits == null) ? 0 : limits.hashCode());
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
