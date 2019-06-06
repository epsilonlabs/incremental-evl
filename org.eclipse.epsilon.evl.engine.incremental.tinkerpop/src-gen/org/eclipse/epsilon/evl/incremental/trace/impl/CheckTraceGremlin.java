 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
/** protected region CheckTraceImports on begin **/
/** protected region CheckTraceImports end **/
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
 * Implementation of ICheckTrace. 
 */
@SuppressWarnings("unused") 
public class CheckTraceGremlin implements ICheckTrace, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The contextModuleElement.
     */
    private IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The invariant.
     */
    private ICheckTraceHasInvariant invariant;

    /**
     * The result.
     */
    private ICheckTraceHasResult result;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public CheckTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.invariant = new CheckTraceHasInvariantGremlin(this, gts, wrapperFactory);
        this.result = new CheckTraceHasResultGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new CheckTraceGremlin. The CheckTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckTraceGremlin(
        IInvariantTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.result = new CheckTraceHasResultGremlin(this, gts, wrapperFactory);
        this.invariant = new CheckTraceHasInvariantGremlin(this, gts, wrapperFactory);
        if (!container.check().create(this)) {
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
    	if (contextModuleElement == null) {
        	contextModuleElement = new InContextModuleElementTraceHasContextModuleElementGremlin(this, gts, wrapperFactory);
        	try {
				contextModuleElement.create(invariant.get().invariantContext().get());
			} catch (TraceModelConflictRelation e) {
				throw new IllegalStateException("Error creating context relationship", e);
			}
        }
        return contextModuleElement;
        /** protected region contextModuleElement end **/
    }

    @Override
    public ICheckTraceHasInvariant invariant() {
        
        return invariant;
    }

    @Override
    public ICheckTraceHasResult result() {
        
        return result;
    }

    @Override
    public ICheckResult getOrCreateCheckResult(IExecutionContext context) throws EolIncrementalExecutionException {    
        CheckResultGremlin checkResult = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            Vertex v = null;
            try {
                v = agts.addV("CheckResult").next();
                /* protected region checkResultTypeOverride on begin */
                checkResult = new CheckResultGremlin(context, this, v, gts, wrapperFactory);
                /* protected region checkResultTypeOverride end */
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                agts.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested CheckResult", e);
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return checkResult;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ICheckTrace other) {
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
        if (!(obj instanceof CheckTraceGremlin))
            return false;
        CheckTraceGremlin other = (CheckTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (invariant == null) {
        if (other.invariant != null)
            return false;
    }
        if (!invariant().get().equals(other.invariant().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IInvariantTrace invariant = invariant().get();
        result = prime * result + ((invariant == null) ? 0 : invariant.hashCode());
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
