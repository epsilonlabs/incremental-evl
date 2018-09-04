 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
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
     * The satisfies.
     */
    private IInvariantTraceHasSatisfies satisfies;

    /**
     * The invariantContext.
     */
    private IInvariantTraceHasInvariantContext invariantContext;

    /**
     * Empty constructor for deserialization.
     */    
    public InvariantTraceGremlin() {
    }
    
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
        this.invariantContext = new InvariantTraceHasInvariantContextGremlin(this, gts);
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts);
        this.check = new InvariantTraceHasCheckGremlin(this, gts);
        this.message = new InvariantTraceHasMessageGremlin(this, gts);
        this.satisfies = new InvariantTraceHasSatisfiesGremlin(this, gts);
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
            guard = new GuardedElementTraceHasGuardGremlin(this, this.gts);
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
            contextModuleElement = new InContextModuleElementTraceHasContextModuleElementGremlin(this, this.gts);
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
            check = new InvariantTraceHasCheckGremlin(this, this.gts);
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
            message = new InvariantTraceHasMessageGremlin(this, this.gts);
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
    public IInvariantTraceHasSatisfies satisfies() {
        if (satisfies == null) {
            satisfies = new InvariantTraceHasSatisfiesGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("satisfies");
                if (gt.hasNext()) {
                    ((InvariantTraceHasSatisfiesGremlin)satisfies).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return satisfies;
    }

    @Override
    public IInvariantTraceHasInvariantContext invariantContext() {
        if (invariantContext == null) {
            invariantContext = new InvariantTraceHasInvariantContextGremlin(this, this.gts);
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
    	            guardTrace = new GuardTraceGremlin(this, v, gts);
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
    	            checkTrace = new CheckTraceGremlin(this, v, gts);
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
    	            messageTrace = new MessageTraceGremlin(this, v, gts);
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

    @Override
    public ISatisfiesTrace getOrCreateSatisfiesTrace() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        SatisfiesTraceGremlin satisfiesTrace = null;
        try {
    	    GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("satisfies");
    	    if (gt.hasNext()) {
    	        satisfiesTrace = new SatisfiesTraceGremlin();
    	        satisfiesTrace.delegate(gt.next());
    	        satisfiesTrace.graphTraversalSource(gts);
    	    }
    	    else {
    	        Vertex v = null;
    	        try {
    	            v = g.addV("SatisfiesTrace").next();
    	            satisfiesTrace = new SatisfiesTraceGremlin(this, v, gts);
    	        } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	            g.V(v).as("v").properties().drop().select("v").drop();
    	            throw new EolIncrementalExecutionException("Error creating requested SatisfiesTrace", e);
    	        }
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return satisfiesTrace;
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
        result = prime * result + ((invariantContext().get() == null) ? 0 : invariantContext().get().hashCode());
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
    
    private GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    private void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }
}
