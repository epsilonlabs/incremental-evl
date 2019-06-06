 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
/** protected region SatisfiesAccessImports on begin **/
/** protected region SatisfiesAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ISatisfiesAccess. 
 */
@SuppressWarnings("unused") 
public class SatisfiesAccessGremlin implements ISatisfiesAccess, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
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
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public SatisfiesAccessGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.modelElement = new SatisfiesAccessHasModelElementGremlin(this, gts, wrapperFactory);
        this.module = new AccessHasModuleGremlin(this, gts, wrapperFactory);
        this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
        this.satisfiedInvariants = new SatisfiesAccessHasSatisfiedInvariantsGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new SatisfiesAccessGremlin. The SatisfiesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesAccessGremlin(
        IModuleElementTrace from,
        IExecutionContext in,
        IModelElementTrace modelElement,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
 
        this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
        this.satisfiedInvariants = new SatisfiesAccessHasSatisfiedInvariantsGremlin(this, gts, wrapperFactory);
        this.modelElement = new SatisfiesAccessHasModelElementGremlin(this, gts, wrapperFactory);
        this.module = new AccessHasModuleGremlin(this, gts, wrapperFactory);
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
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
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("all");
            if (values.hasNext()) {
                return (Boolean) values.next().value();
            }
            else {
                /** protected region all on begin **/
	            // TODO Add default return value for SatisfiesAccessGremlin.getAll
	            throw new IllegalStateException("Add default return value for SatisfiesAccessGremlin.getAll");
	            /** protected region all end **/
            }
            
        }
        return null;
    }
    
    
    @Override
    public void setAll(boolean value) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate).property("all", value).iterate();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
  
    }   
     
    @Override
    public IAccessHasModule module() {
        
        return module;
    }

    @Override
    public IAccessHasFrom from() {
        
        return from;
    }

    @Override
    public IAccessHasIn in() {
        
        return in;
    }

    @Override
    public ISatisfiesAccessHasModelElement modelElement() {
        
        return modelElement;
    }

    @Override
    public ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants() {
        
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
    public GraphTraversalSource graphTraversalSource() {
        return this.gts;
    }
    
}
