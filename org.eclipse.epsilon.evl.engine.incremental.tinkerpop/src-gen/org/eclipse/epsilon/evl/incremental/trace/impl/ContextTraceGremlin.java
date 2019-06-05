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
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/
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
 * Implementation of IContextTrace. 
 */
@SuppressWarnings("unused") 
public class ContextTraceGremlin implements IContextTrace, TinkerpopDelegate<Vertex> {
    
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
     * * The execution context in which this module was executed. 
     */
    private IContextModuleElementTraceHasExecutionContext executionContext;

    /**
     * The constraints.
     */
    private IContextTraceHasConstraints constraints;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ContextTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, gts, wrapperFactory);
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, wrapperFactory);
        this.constraints = new ContextTraceHasConstraintsGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new ContextTraceGremlin. The ContextTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTraceGremlin(
        String kind,
        Integer index,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("kind", kind)
            .property("index", index)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, wrapperFactory);
        this.constraints = new ContextTraceHasConstraintsGremlin(this, gts, wrapperFactory);
        this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, gts, wrapperFactory);
        if (!container.moduleElements().create(this)) {
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
    public String getKind() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("kind");
            if (values.hasNext()) {
                return (String) values.next().value();
            }
            else {
                /** protected region kind on begin **/
	            // TODO Add default return value for ContextTraceGremlin.getKind
	            throw new IllegalStateException("Add default return value for ContextTraceGremlin.getKind");
	            /** protected region kind end **/
            }
            
        }
        return null;
    }
    
    @Override
    public Integer getIndex() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("index");
            if (values.hasNext()) {
                return (Integer) values.next().value();
            }
            else {
                /** protected region index on begin **/
	            // TODO Add default return value for ContextTraceGremlin.getIndex
	            throw new IllegalStateException("Add default return value for ContextTraceGremlin.getIndex");
	            /** protected region index end **/
            }
            
        }
        return null;
    }
    
    @Override
    public IGuardedElementTraceHasGuard guard() {
        
        return guard;
    }

    @Override
    public IContextModuleElementTraceHasExecutionContext executionContext() {
        
        return executionContext;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        
        return constraints;
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
    public IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException {    
        ExecutionContextGremlin executionContext = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            Vertex v = null;
            try {
                v = agts.addV("ExecutionContext").next();
                /* protected region executionContextTypeOverride on begin */
                executionContext = new ExecutionContextGremlin(this, v, gts, wrapperFactory);
                /* protected region executionContextTypeOverride end */
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                agts.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested ExecutionContext", e);
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return executionContext;
    }      
    
    @Override
    public IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException {    
        InvariantTraceGremlin invariantTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("constraints").has("name", name);
            if (gt.hasNext()) {
                invariantTrace = new InvariantTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("InvariantTrace").property("tag", GremlinUtils.identityToString(name, this)).next();
                    /* protected region invariantTraceTypeOverride on begin */
                    invariantTrace = new InvariantTraceGremlin(name, this, v, gts, wrapperFactory); 
                    /* protected region invariantTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested InvariantTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return invariantTrace;
    }      
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("kind", getKind());
        result.put("index", getIndex());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IContextTrace other) {
        if (other == null) {
            return false;
        }
        String kind = getKind();
        String otherKind = other.getKind();
        if (kind == null) {
            if (otherKind != null)
                return false;
        } else if (!kind.equals(otherKind)) {
            return false;
        }
        Integer index = Integer.valueOf(getIndex());
        Integer otherIndex = Integer.valueOf(other.getIndex());
        if (index == null) {
            if (otherIndex != null)
                return false;
        } else if (!index.equals(otherIndex)) {
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
        if (!(obj instanceof ContextTraceGremlin))
            return false;
        ContextTraceGremlin other = (ContextTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (executionContext == null) {
        if (other.executionContext != null)
            return false;
    }
        if (!IncrementalUtils.equalIterators(executionContext().get(), other.executionContext().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getKind() == null) ? 0 : getKind().hashCode());
        Integer index = Integer.valueOf(getIndex());
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        Iterator<IExecutionContext> executionContext = executionContext().get();
        result = prime * result + ((executionContext == null) ? 0 : executionContext.hashCode());
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
