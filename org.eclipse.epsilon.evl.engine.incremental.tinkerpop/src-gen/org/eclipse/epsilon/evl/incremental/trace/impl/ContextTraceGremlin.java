 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

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
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private IModuleElementTraceHasAccesses accesses;

    /**
     * * The execution context in which this module was executed. This is constitued
       * by the variables that define the context of the module element. In EVL this would
       * be ‘self’ (model element of the Context type) in ETL this would be the input (and 
       * output variables). 
     */
    private IContextModuleElementTraceHasExecutionContext executionContext;

    /**
     * The constraints.
     */
    private IContextTraceHasConstraints constraints;

    /**
     * Empty constructor for deserialization.
     */    
    public ContextTraceGremlin() {
    }
    
    /**
     * Instantiates a new ContextTraceGremlin. The ContextTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTraceGremlin(
        String kind, Integer index, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
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
        this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, gts);
        this.guard = new GuardedElementTraceHasGuardGremlin(this, gts);
        this.accesses = new ModuleElementTraceHasAccessesGremlin(this, gts);
        this.constraints = new ContextTraceHasConstraintsGremlin(this, gts);
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
        	// TODO Add default return value for ContextTraceGremlin.getgetKind
	        result = "";
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
        // TODO Add default return value for ContextTraceGremlin.getgetIndex
        return (Integer) g.V(delegate).values("index").next();
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
    public IModuleElementTraceHasAccesses accesses() {
        if (accesses == null) {
            accesses = new ModuleElementTraceHasAccessesGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("accesses");
                if (gt.hasNext()) {
                    ((ModuleElementTraceHasAccessesGremlin)accesses).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return accesses;
    }

    @Override
    public IContextModuleElementTraceHasExecutionContext executionContext() {
        if (executionContext == null) {
            executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this, this.gts);
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
            constraints = new ContextTraceHasConstraintsGremlin(this, this.gts);
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
    	    Vertex v = null;
    	    try {
    	        v = g.addV("GuardTrace").next();
    	        guardTrace = new GuardTraceGremlin(this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (guardTrace != null) {
    		        return guardTrace;
    		    }
    	        guardTrace = (GuardTraceGremlin) this.guard.get();
    	        if (guardTrace  == null) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
    	                    + "duplicate but previous one was not found.");
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
    	        executionContext = new ExecutionContextGremlin(this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (executionContext != null) {
    		        return executionContext;
    		    }
    	        executionContext = (ExecutionContextGremlin) this.executionContext.get();
    	        if (executionContext  == null) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ExecutionContext was "
    	                    + "duplicate but previous one was not found.");
    	        }
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
    	    Vertex v = null;
    	    try {
    	        v = g.addV("InvariantTrace").next();
    	        invariantTrace = new InvariantTraceGremlin(name, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (invariantTrace != null) {
    		        return invariantTrace;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ContextTraceHasConstraintsGremlin) this.constraints).getRaw()
    	            .hasLabel("InvariantTrace")
    	            .has("name", name)
    	            .as("a") 
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested InvariantTrace was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        invariantTrace = new InvariantTraceGremlin();
    	        invariantTrace.delegate(gt.next());
    	        invariantTrace.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return invariantTrace;
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
        if (executionContext.get() == null) {
            if (other.executionContext.get() != null)
                return false;
        }
        if (!executionContext.get().equals(other.executionContext.get())) {
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
        result = prime * result + ((executionContext.get() == null) ? 0 : executionContext.get().hashCode());
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
