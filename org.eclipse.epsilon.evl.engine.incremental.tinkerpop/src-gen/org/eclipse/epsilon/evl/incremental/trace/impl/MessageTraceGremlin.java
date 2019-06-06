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
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
/** protected region MessageTraceImports on begin **/
/** protected region MessageTraceImports end **/
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
 * Implementation of IMessageTrace. 
 */
@SuppressWarnings("unused") 
public class MessageTraceGremlin implements IMessageTrace, TinkerpopDelegate<Vertex> {
    
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
    private IMessageTraceHasInvariant invariant;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public MessageTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.invariant = new MessageTraceHasInvariantGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new MessageTraceGremlin. The MessageTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public MessageTraceGremlin(
        IInvariantTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.invariant = new MessageTraceHasInvariantGremlin(this, gts, wrapperFactory);
        if (!container.message().create(this)) {
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
    public IMessageTraceHasInvariant invariant() {
        
        return invariant;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IMessageTrace other) {
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
        if (!(obj instanceof MessageTraceGremlin))
            return false;
        MessageTraceGremlin other = (MessageTraceGremlin) obj;
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
