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
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IContextTrace. 
 */
public class ContextTraceGremlin implements IContextTrace, GremlinWrapper<Vertex> {
    
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
    public ContextTraceGremlin(String kind, int index, IModuleExecutionTrace container, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("kind", kind)
            .property("index", index)
            .iterate();
        this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this);
        this.guard = new GuardedElementTraceHasGuardGremlin(this);
        this.accesses = new ModuleElementTraceHasAccessesGremlin(this);
        this.constraints = new ContextTraceHasConstraintsGremlin(this);

        if (!container.moduleElements().create(this)) {
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
    public String getKind() {
        return (String) g.V(delegate).values("kind").next();
    }
    
    @Override
    public int getIndex() {
        return (int) g.V(delegate).values("index").next();
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
    public IContextModuleElementTraceHasExecutionContext executionContext() {
        if (executionContext == null) {
            this.executionContext = new ContextModuleElementTraceHasExecutionContextGremlin(this);
        }
        return executionContext;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        if (constraints == null) {
            this.constraints = new ContextTraceHasConstraintsGremlin(this);
        }
        return constraints;
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
    public IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException {
        ExecutionContextGremlin executionContext = null;
        try {
            Vertex v = g.addV("ExecutionContext").next();
            executionContext = new ExecutionContextGremlin(this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
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
        return executionContext;
    }      
                  
    @Override
    public IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException {
        InvariantTraceGremlin invariantTrace = null;
        try {
            Vertex v = g.addV("InvariantTrace").next();
            invariantTrace = new InvariantTraceGremlin(name, this, v, graph);
            this.constraints().create(invariantTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (invariantTrace != null) {
    	        return invariantTrace;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ContextTraceHasConstraintsGremlin) this.constraints).getRaw()
                .hasLabel("InvariantTrace")
                .has("name", name)
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested InvariantTrace was "
                        + "duplicate but previous one was not found.");
            }
            invariantTrace = new InvariantTraceGremlin();
            invariantTrace.delegate(gt.next());
            invariantTrace.graph(graph);
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
    public Graph graph() {
        return graph;    
    }

    @Override
    public void graph(Graph graph) {
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
    }
}
