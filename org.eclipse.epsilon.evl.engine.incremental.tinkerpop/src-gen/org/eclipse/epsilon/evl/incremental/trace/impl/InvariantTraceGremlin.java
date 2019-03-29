 /*******************************************************************************
 * This file was automatically generated on: 2019-02-07.
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
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region InvariantTraceImports on begin **/
/** protected region InvariantTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IInvariantTrace. 
 */
public class InvariantTraceGremlin implements IInvariantTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
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
     * Empty constructor for deserialization.
     */    
    public InvariantTraceGremlin() { }
    
    /**
     * Instantiates a new InvariantTraceGremlin. The InvariantTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public InvariantTraceGremlin(
        String name, IContextTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
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
        if (!container.constraints().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Derived Features
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.check = new InvariantTraceHasCheckGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.message = new InvariantTraceHasMessageGremlin(this, gts, EvlTraceFactory.getFactory());
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
	            // TODO Add default return value for InvariantTraceGremlin.getName
	            throw new IllegalStateException("Add default return value for InvariantTraceGremlin.getName", ex);
	            /** protected region name end **/
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
    public IInContextModuleElementTraceHasContextModuleElement contextModuleElement() {
        if (contextModuleElement == null) {
            contextModuleElement = new InContextModuleElementTraceHasContextModuleElementGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("contextModuleElement");
                if (gt.hasNext()) {
                    ((InContextModuleElementTraceHasContextModuleElementGremlin)contextModuleElement).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return contextModuleElement;
    }

    @Override
    public IInvariantTraceHasCheck check() {
        if (check == null) {
            check = new InvariantTraceHasCheckGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("check");
                if (gt.hasNext()) {
                    ((InvariantTraceHasCheckGremlin)check).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return check;
    }

    @Override
    public IInvariantTraceHasMessage message() {
        if (message == null) {
            message = new InvariantTraceHasMessageGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("message");
                if (gt.hasNext()) {
                    ((InvariantTraceHasMessageGremlin)message).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return message;
    }

    @Override
    public IInvariantTraceHasInvariantContext invariantContext() {
        if (invariantContext == null) {
            invariantContext = new InvariantTraceHasInvariantContextGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("invariantContext");
                if (gt.hasNext()) {
                    ((InvariantTraceHasInvariantContextGremlin)invariantContext).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return invariantContext;
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
    public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        CheckTraceGremlin checkTrace = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("check");
            if (gt.hasNext()) {
                checkTrace = new CheckTraceGremlin();
                checkTrace.delegate(gt.next());
                checkTrace.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("CheckTrace").next();
                    /* protected region checkTraceTypeOverride on begin */
                    checkTrace = new CheckTraceGremlin(this, v, gts); 
                    /* protected region checkTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested CheckTrace", e);
                }
            }
        } finally {
            finishTraversal(g);
        }  
        return checkTrace;
    }      
    
    @Override
    public IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        MessageTraceGremlin messageTrace = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("message");
            if (gt.hasNext()) {
                messageTrace = new MessageTraceGremlin();
                messageTrace.delegate(gt.next());
                messageTrace.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("MessageTrace").next();
                    /* protected region messageTraceTypeOverride on begin */
                    messageTrace = new MessageTraceGremlin(this, v, gts); 
                    /* protected region messageTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested MessageTrace", e);
                }
            }
        } finally {
            finishTraversal(g);
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
