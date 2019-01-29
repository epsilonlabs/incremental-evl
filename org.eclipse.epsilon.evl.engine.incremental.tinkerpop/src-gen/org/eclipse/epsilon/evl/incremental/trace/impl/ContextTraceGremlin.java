 /*******************************************************************************
 * This file was automatically generated on: 2019-01-25.
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
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IContextTrace. 
 */
public class ContextTraceGremlin implements IContextTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
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
     * Empty constructor for deserialization.
     */    
    public ContextTraceGremlin() { }
    
    /**
     * Instantiates a new ContextTraceGremlin. The ContextTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTraceGremlin(
        String kind, Integer index, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("kind", kind)
            .property("index", index)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        if (!container.moduleElements().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Equals References
        this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.constraints = new ContextTraceHasConstraintsGremlin(this, gts, EvlTraceFactory.getFactory());
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
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("kind").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region kind on begin **/
	            // TODO Add default return value for ContextTraceGremlin.getKind
	            throw new IllegalStateException("Add default return value for ContextTraceGremlin.getKind", ex);
	            /** protected region kind end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public Integer getIndex() {
        GraphTraversalSource g = startTraversal();
        Integer result = null;
        try {
	        try {
	            result = (Integer) g.V(delegate).values("index").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region index on begin **/
	            // TODO Add default return value for ContextTraceGremlin.getIndex
	            throw new IllegalStateException("Add default return value for ContextTraceGremlin.getIndex", ex);
	            /** protected region index end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IGuardedElementTraceHasGuard guard() {
        if (guard == null) {
            guard = new GuardedElementTraceHasGuardGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("guard");
                if (gt.hasNext()) {
                    ((GuardedElementTraceHasGuardGremlin)guard).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return guard;
    }

    @Override
    public IContextModuleElementTraceHasExecutionContext executionContext() {
        if (executionContext == null) {
            executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("executionContext");
                if (gt.hasNext()) {
                    ((ContextModuleElementTraceHasExecutionContextGremlin)executionContext).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return executionContext;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        if (constraints == null) {
            constraints = new ContextTraceHasConstraintsGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("constraints");
                if (gt.hasNext()) {
                    ((ContextTraceHasConstraintsGremlin)constraints).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return constraints;
    }

    @Override
    public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        GuardTraceGremlin guardTrace = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("guard");
            if (gt.hasNext()) {
                guardTrace = new GuardTraceGremlin();
                guardTrace.delegate(gt.next());
                guardTrace.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("GuardTrace").next();
                    /* protected region guardTraceTypeOverride on begin */
                    guardTrace = new GuardTraceGremlin(this, v, gts); 
                    /* protected region guardTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested GuardTrace", e);
                }
            }
        } finally {
            finishTraversal(g);
        }  
        return guardTrace;
    }      
    
    @Override
    public IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ExecutionContextGremlin executionContext = null;
        try {
            Vertex v = null;
            try {
                v = g.addV("ExecutionContext").next();
                /* protected region executionContextTypeOverride on begin */
                executionContext = new EvlExecutionContextGremlin(this, v, gts);
                /* protected region executionContextTypeOverride end */
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                g.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested ExecutionContext", e);
            }
        } finally {
            finishTraversal(g);
        }  
        return executionContext;
    }      
    
    @Override
    public IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        InvariantTraceGremlin invariantTrace = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("constraints").has("name", name);
            if (gt.hasNext()) {
                invariantTrace = new InvariantTraceGremlin();
                invariantTrace.delegate(gt.next());
                invariantTrace.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("InvariantTrace").property(T.id, GremlinUtils.identityToString(name, this)).next();
                    /* protected region invariantTraceTypeOverride on begin */
                    invariantTrace = new InvariantTraceGremlin(name, this, v, gts); 
                    /* protected region invariantTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested InvariantTrace", e);
                }
            }
        } finally {
            finishTraversal(g);
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
    public void delegate(Vertex delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
    
    protected GraphTraversalSource graphTraversalSource() {
        return this.gts;
    }
    
    protected GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    protected void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }
}
