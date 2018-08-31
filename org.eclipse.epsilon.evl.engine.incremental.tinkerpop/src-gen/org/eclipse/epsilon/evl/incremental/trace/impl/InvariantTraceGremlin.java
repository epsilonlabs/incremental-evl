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
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region InvariantTraceImports on begin **/
/** protected region InvariantTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

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
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private IModuleElementTraceHasAccesses accesses;

    /**
     * The parentTrace.
     */
    private IInContextModuleElementTraceHasParentTrace parentTrace;

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
        // FIXME We need to destroy the created edges when any edge fails
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
        this.accesses = new ModuleElementTraceHasAccessesGremlin(this, gts);
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
            // TODO Add default return value for InvariantTraceGremlin.getgetName
            throw new IllegalStateException(ex);
            /** protected region name end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public Boolean getResult() {
        GraphTraversalSource g = startTraversal();
        Boolean result = null;
        try {
	        try {
	            result = (Boolean) g.V(delegate).values("result").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region result on begin **/
            // TODO Add default return value for InvariantTraceGremlin.getgetResult
            return false;
            /** protected region result end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    
    @Override
    public void setResult(boolean value) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate).property("result", value).iterate();
        } finally {
            finishTraversal(g);
        }
  
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
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        if (parentTrace == null) {
            parentTrace = new InContextModuleElementTraceHasParentTraceGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("parentTrace");
                if (gt.hasNext()) {
                    ((InContextModuleElementTraceHasParentTraceGremlin)parentTrace).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return parentTrace;
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
    public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        CheckTraceGremlin checkTrace = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("CheckTrace").next();
    	        checkTrace = new CheckTraceGremlin(this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (checkTrace != null) {
    		        return checkTrace;
    		    }
    	        checkTrace = (CheckTraceGremlin) this.check.get();
    	        if (checkTrace  == null) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested CheckTrace was "
    	                    + "duplicate but previous one was not found.");
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
    	    Vertex v = null;
    	    try {
    	        v = g.addV("MessageTrace").next();
    	        messageTrace = new MessageTraceGremlin(this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (messageTrace != null) {
    		        return messageTrace;
    		    }
    	        messageTrace = (MessageTraceGremlin) this.message.get();
    	        if (messageTrace  == null) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested MessageTrace was "
    	                    + "duplicate but previous one was not found.");
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
    	    Vertex v = null;
    	    try {
    	        v = g.addV("SatisfiesTrace").next();
    	        satisfiesTrace = new SatisfiesTraceGremlin(this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (satisfiesTrace != null) {
    		        return satisfiesTrace;
    		    }
    	        satisfiesTrace = (SatisfiesTraceGremlin) this.satisfies.get();
    	        if (satisfiesTrace  == null) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested SatisfiesTrace was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return satisfiesTrace;
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
        if (invariantContext.get() == null) {
            if (other.invariantContext.get() != null)
                return false;
        }
        if (!invariantContext.get().equals(other.invariantContext.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((invariantContext.get() == null) ? 0 : invariantContext.get().hashCode());
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
