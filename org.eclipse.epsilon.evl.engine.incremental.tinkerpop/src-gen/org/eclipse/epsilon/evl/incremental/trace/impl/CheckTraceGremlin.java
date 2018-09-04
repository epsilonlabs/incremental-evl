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
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region CheckTraceImports on begin **/
/** protected region CheckTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ICheckTrace. 
 */
public class CheckTraceGremlin implements ICheckTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The contextModuleElement.
     */
    private IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The invariant.
     */
    private ICheckTraceHasInvariant invariant;

    /**
     * The result.
     */
    private ICheckTraceHasResult result;

    /**
     * Empty constructor for deserialization.
     */    
    public CheckTraceGremlin() {
    }
    
    /**
     * Instantiates a new CheckTraceGremlin. The CheckTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckTraceGremlin(
        IInvariantTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.check().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.invariant = new CheckTraceHasInvariantGremlin(this, gts);
        this.result = new CheckTraceHasResultGremlin(this, gts);
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
    public ICheckTraceHasInvariant invariant() {
        if (invariant == null) {
            invariant = new CheckTraceHasInvariantGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("invariant");
                if (gt.hasNext()) {
                    ((CheckTraceHasInvariantGremlin)invariant).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return invariant;
    }

    @Override
    public ICheckTraceHasResult result() {
        if (result == null) {
            result = new CheckTraceHasResultGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("result");
                if (gt.hasNext()) {
                    ((CheckTraceHasResultGremlin)result).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return result;
    }

    @Override
    public ICheckResult getOrCreateCheckResult(IExecutionContext context) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        CheckResultGremlin checkResult = null;
        try {
            Vertex v = null;
            try {
                v = g.addV("CheckResult").next();
                checkResult = new CheckResultGremlin(context, this, v, gts);
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                g.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested CheckResult", e);
            }
    	} finally {
            finishTraversal(g);
        }    
        return checkResult;
    }      

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ICheckTrace other) {
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
        if (!(obj instanceof CheckTraceGremlin))
            return false;
        CheckTraceGremlin other = (CheckTraceGremlin) obj;
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
        result = prime * result + ((invariant().get() == null) ? 0 : invariant().get().hashCode());
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
