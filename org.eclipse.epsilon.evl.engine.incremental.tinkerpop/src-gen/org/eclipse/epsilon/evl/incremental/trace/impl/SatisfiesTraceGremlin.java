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
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region SatisfiesTraceImports on begin **/
/** protected region SatisfiesTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ISatisfiesTrace. 
 */
public class SatisfiesTraceGremlin implements ISatisfiesTrace, GremlinWrapper<Vertex> {
    

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
    private ISatisfiesTraceHasInvariant invariant;

    /**
     * The satisfiedInvariants.
     */
    private ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants;

    /**
     * Empty constructor for deserialization.
     */    
    public SatisfiesTraceGremlin() {
    }
    
    /**
     * Instantiates a new SatisfiesTraceGremlin. The SatisfiesTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesTraceGremlin(
        IInvariantTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.satisfies().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.invariant = new SatisfiesTraceHasInvariantGremlin(this, gts);
        this.satisfiedInvariants = new SatisfiesTraceHasSatisfiedInvariantsGremlin(this, gts);
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
    public Boolean getAll() {
        GraphTraversalSource g = startTraversal();
        Boolean result = null;
        try {
	        try {
	            result = (Boolean) g.V(delegate).values("all").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region all on begin **/
	            // TODO Add default return value for SatisfiesTraceGremlin.getAll
	            throw new IllegalStateException("Add default return value for SatisfiesTraceGremlin.getAll", ex);
	            /** protected region all end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    
    @Override
    public void setAll(boolean value) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate).property("all", value).iterate();
        } finally {
            finishTraversal(g);
        }
  
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
    public ISatisfiesTraceHasInvariant invariant() {
        if (invariant == null) {
            invariant = new SatisfiesTraceHasInvariantGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("invariant");
                if (gt.hasNext()) {
                    ((SatisfiesTraceHasInvariantGremlin)invariant).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return invariant;
    }

    @Override
    public ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants() {
        if (satisfiedInvariants == null) {
            satisfiedInvariants = new SatisfiesTraceHasSatisfiedInvariantsGremlin(this, this.gts);
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("satisfiedInvariants");
                if (gt.hasNext()) {
                    ((SatisfiesTraceHasSatisfiedInvariantsGremlin)satisfiedInvariants).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return satisfiedInvariants;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ISatisfiesTrace other) {
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
        if (!(obj instanceof SatisfiesTraceGremlin))
            return false;
        SatisfiesTraceGremlin other = (SatisfiesTraceGremlin) obj;
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
