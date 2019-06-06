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
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
/** protected region InvariantTraceImports on begin **/
/** protected region InvariantTraceImports end **/
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
 * Implementation of IInvariantTrace. 
 */
@SuppressWarnings("unused") 
public class InvariantTraceGremlin implements IInvariantTrace, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The guard.
     */
    private IGuardedElementTraceHasGuard guard;

    /**
     * The contextModuleElement.
     */
    private IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The check.
     */
    private IInvariantTraceHasCheck check;

    /**
     * The message.
     */
    private IInvariantTraceHasMessage message;

    /**
     * The invariantContext.
     */
    private IInvariantTraceHasInvariantContext invariantContext;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public InvariantTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.invariantContext = new InvariantTraceHasInvariantContextGremlin(this, gts, wrapperFactory);
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, wrapperFactory);
        this.check = new InvariantTraceHasCheckGremlin(this, gts, wrapperFactory);
        this.message = new InvariantTraceHasMessageGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new InvariantTraceGremlin. The InvariantTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public InvariantTraceGremlin(
        String name,
        IContextTrace container,
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
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, wrapperFactory);
        this.check = new InvariantTraceHasCheckGremlin(this, gts, wrapperFactory);
        this.message = new InvariantTraceHasMessageGremlin(this, gts, wrapperFactory);
        this.invariantContext = new InvariantTraceHasInvariantContextGremlin(this, gts, wrapperFactory);
        if (!container.constraints().create(this)) {
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
    public String getName() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("name");
            if (values.hasNext()) {
                return (String) values.next().value();
            }
            else {
                /** protected region name on begin **/
                // TODO Add default return value for InvariantTraceGremlin.getName
                throw new IllegalStateException("Add default return value for InvariantTraceGremlin.getName");
                /** protected region name end **/
            }
            
        }
        return null;
    }
    
    @Override
    public IGuardedElementTraceHasGuard guard() {
        
        return guard;
    }

    @Override
    public IInContextModuleElementTraceHasContextModuleElement contextModuleElement() {
        /** protected region contextModuleElement on begin **/
        if (contextModuleElement == null) {
        	contextModuleElement = new InContextModuleElementTraceHasContextModuleElementGremlin(this, gts, wrapperFactory);
        	try {
				contextModuleElement.create(invariantContext.get());
			} catch (TraceModelConflictRelation e) {
				throw new IllegalStateException("Error creating context relationship", e);
			}
        }
        return contextModuleElement;
        /** protected region contextModuleElement end **/
    }

    @Override
    public IInvariantTraceHasCheck check() {
        
        return check;
    }

    @Override
    public IInvariantTraceHasMessage message() {
        
        return message;
    }

    @Override
    public IInvariantTraceHasInvariantContext invariantContext() {
        
        return invariantContext;
    }

    @Override
    public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {    
        GuardTraceGremlin guardTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("guard");
            if (gt.hasNext()) {
                guardTrace = new GuardTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("GuardTrace").next();
                    /* protected region guardTraceTypeOverride on begin */
                    guardTrace = new GuardTraceGremlin(this, v, gts, wrapperFactory); 
                    /* protected region guardTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested GuardTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return guardTrace;
    }      
    
    @Override
    public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {    
        CheckTraceGremlin checkTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("check");
            if (gt.hasNext()) {
                checkTrace = new CheckTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("CheckTrace").next();
                    /* protected region checkTraceTypeOverride on begin */
                    checkTrace = new CheckTraceGremlin(this, v, gts, wrapperFactory); 
                    /* protected region checkTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested CheckTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return checkTrace;
    }      
    
    @Override
    public IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException {    
        MessageTraceGremlin messageTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("message");
            if (gt.hasNext()) {
                messageTrace = new MessageTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("MessageTrace").next();
                    /* protected region messageTraceTypeOverride on begin */
                    messageTrace = new MessageTraceGremlin(this, v, gts, wrapperFactory); 
                    /* protected region messageTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested MessageTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return messageTrace;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", getName());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IInvariantTrace other) {
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
        if (!(obj instanceof InvariantTraceGremlin))
            return false;
        InvariantTraceGremlin other = (InvariantTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (invariantContext == null) {
        if (other.invariantContext != null)
            return false;
    }
        if (!invariantContext().get().equals(other.invariantContext().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        IContextTrace invariantContext = invariantContext().get();
        result = prime * result + ((invariantContext == null) ? 0 : invariantContext.hashCode());
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
