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
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region SatisfiesAccessImports on begin **/
/** protected region SatisfiesAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ISatisfiesAccess. 
 */
public class SatisfiesAccessGremlin implements ISatisfiesAccess, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * The module.
     */
    private IAccessHasModule module;

    /**
     * The from.
     */
    private IAccessHasFrom from;

    /**
     * The in.
     */
    private IAccessHasIn in;

    /**
     * The modelElement.
     */
    private ISatisfiesAccessHasModelElement modelElement;

    /**
     * The satisfiedInvariants.
     */
    private ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants;

    /**
     * Empty constructor for deserialization.
     */    
    public SatisfiesAccessGremlin() { }
    
    /**
     * Instantiates a new SatisfiesAccessGremlin. The SatisfiesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesAccessGremlin(
        IModuleElementTrace from, IExecutionContext in, IModelElementTrace modelElement, IModuleExecutionTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Equals References
        this.modelElement = new SatisfiesAccessHasModelElementGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.from = new AccessHasFromGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.in = new AccessHasInGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.satisfiedInvariants = new SatisfiesAccessHasSatisfiedInvariantsGremlin(this, gts, EvlTraceFactory.getFactory());
        try {
	        this.from.create(from);
	        this.modelElement.create(modelElement);
	        this.in.create(in);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasFromGremlin)this.from).delegate().remove();
            ((SatisfiesAccessHasModelElementGremlin)this.modelElement).delegate().remove();
            ((AccessHasInGremlin)this.in).delegate().remove();
            throw ex;
        }
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
	            // TODO Add default return value for SatisfiesAccessGremlin.getAll
	            throw new IllegalStateException("Add default return value for SatisfiesAccessGremlin.getAll", ex);
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
    public IAccessHasModule module() {
        if (module == null) {
            module = new AccessHasModuleGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("module");
                if (gt.hasNext()) {
                    ((AccessHasModuleGremlin)module).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return module;
    }

    @Override
    public IAccessHasFrom from() {
        if (from == null) {
            from = new AccessHasFromGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("from");
                if (gt.hasNext()) {
                    ((AccessHasFromGremlin)from).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return from;
    }

    @Override
    public IAccessHasIn in() {
        if (in == null) {
            in = new AccessHasInGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("in");
                if (gt.hasNext()) {
                    ((AccessHasInGremlin)in).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return in;
    }

    @Override
    public ISatisfiesAccessHasModelElement modelElement() {
        if (modelElement == null) {
            modelElement = new SatisfiesAccessHasModelElementGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("modelElement");
                if (gt.hasNext()) {
                    ((SatisfiesAccessHasModelElementGremlin)modelElement).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return modelElement;
    }

    @Override
    public ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants() {
        if (satisfiedInvariants == null) {
            satisfiedInvariants = new SatisfiesAccessHasSatisfiedInvariantsGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("satisfiedInvariants");
                if (gt.hasNext()) {
                    ((SatisfiesAccessHasSatisfiedInvariantsGremlin)satisfiedInvariants).delegate(gt.next());
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
    public boolean sameIdentityAs(final ISatisfiesAccess other) {
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
        if (!(obj instanceof SatisfiesAccessGremlin))
            return false;
        SatisfiesAccessGremlin other = (SatisfiesAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (modelElement == null) {
        if (other.modelElement != null)
            return false;
    }
        if (!modelElement().get().equals(other.modelElement().get())) {
            return false;
        }
    if (module == null) {
        if (other.module != null)
            return false;
    }
        if (!module().get().equals(other.module().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IModelElementTrace modelElement = modelElement().get();
        result = prime * result + ((modelElement == null) ? 0 : modelElement.hashCode());
        IModuleExecutionTrace module = module().get();
        result = prime * result + ((module == null) ? 0 : module.hashCode());
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
