 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IInvariantTrace. 
 */
public class InvariantTraceGremlin implements IInvariantTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
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
    public InvariantTraceGremlin(String name, IContextTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("name", name)
            .iterate();
        this.invariantContext = new InvariantTraceHasInvariantContextGremlin(this);
        this.guard = new GuardedElementTraceHasGuardGremlin(this);
        this.accesses = new ModuleElementTraceHasAccessesGremlin(this);
        this.check = new InvariantTraceHasCheckGremlin(this);
        this.message = new InvariantTraceHasMessageGremlin(this);
        this.satisfies = new InvariantTraceHasSatisfiesGremlin(this);

        if (!container.constraints().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
    }
    
    @Override
    public Object getId() {
        return (Object) g.V(delegate).values("id").next();
    }
    
    
    @Override
    public void setId(Object value) {
        g.V(delegate).property("id", value).iterate();
    }   
     
    @Override
    public String getName() {
        return (String) g.V(delegate).values("name").next();
    }
    
    @Override
    public boolean getResult() {
        return (boolean) g.V(delegate).values("result").next();
    }
    
    
    @Override
    public void setResult(boolean value) {
        g.V(delegate).property("result", value).iterate();
    }   
     
    @Override
    public IGuardedElementTraceHasGuard guard() {
        if (guard == null) {
            this.guard = new GuardedElementTraceHasGuardGremlin(this);
        }
        return guard;
    }

    @Override
    public IModuleElementTraceHasAccesses accesses() {
        if (accesses == null) {
            this.accesses = new ModuleElementTraceHasAccessesGremlin(this);
        }
        return accesses;
    }

    @Override
    public IInvariantTraceHasCheck check() {
        if (check == null) {
            this.check = new InvariantTraceHasCheckGremlin(this);
        }
        return check;
    }

    @Override
    public IInvariantTraceHasMessage message() {
        if (message == null) {
            this.message = new InvariantTraceHasMessageGremlin(this);
        }
        return message;
    }

    @Override
    public IInvariantTraceHasSatisfies satisfies() {
        if (satisfies == null) {
            this.satisfies = new InvariantTraceHasSatisfiesGremlin(this);
        }
        return satisfies;
    }

    @Override
    public IInvariantTraceHasInvariantContext invariantContext() {
        if (invariantContext == null) {
            this.invariantContext = new InvariantTraceHasInvariantContextGremlin(this);
        }
        return invariantContext;
    }

    @Override
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
        return null;
        /** protected region parentTrace end **/
    }

    @Override
    public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {
        GuardTraceGremlin guardTrace = null;
        try {
            Vertex v = g.addV("GuardTrace").next();
            guardTrace = new GuardTraceGremlin(this, v, graph);
            this.guard().create(guardTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
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
        return guardTrace;
    }      
                  
    @Override
    public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {
        CheckTraceGremlin checkTrace = null;
        try {
            Vertex v = g.addV("CheckTrace").next();
            checkTrace = new CheckTraceGremlin(this, v, graph);
            this.check().create(checkTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
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
        return checkTrace;
    }      
                  
    @Override
    public IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException {
        MessageTraceGremlin messageTrace = null;
        try {
            Vertex v = g.addV("MessageTrace").next();
            messageTrace = new MessageTraceGremlin(this, v, graph);
            this.message().create(messageTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
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
        return messageTrace;
    }      
                  
    @Override
    public ISatisfiesTrace getOrCreateSatisfiesTrace() throws EolIncrementalExecutionException {
        SatisfiesTraceGremlin satisfiesTrace = null;
        try {
            Vertex v = g.addV("SatisfiesTrace").next();
            satisfiesTrace = new SatisfiesTraceGremlin(this, v, graph);
            this.satisfies().create(satisfiesTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
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
    public Graph graph() {
        return graph;    
    }

    @Override
    public void graph(Graph graph) {
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
    }
}
