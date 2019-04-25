 /*******************************************************************************
 * This file was automatically generated on: 2019-04-25.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
/** protected region ElementAccessImports on begin **/
/** protected region ElementAccessImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IElementAccess. 
 */
@SuppressWarnings("unused") 
public class ElementAccessGremlin implements IElementAccess, GremlinWrapper<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * The id.
     */
    private Object id;

    
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
     * The element.
     */
    private IElementAccessHasElement element;

    /**
     * Empty constructor for de/-serialization.
     */    
    // public ElementAccessGremlin() { }
    
    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public ElementAccessGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
    }
    
    /**
     * Instantiates a new ElementAccessGremlin. The ElementAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ElementAccessGremlin(
        IModuleElementTrace from,
        IExecutionContext in,
        IModelElementTrace element,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Equals References
        // this.element = new ElementAccessHasElementGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
        try {
	        this.from.create(from);
	        this.element.create(element);
	        this.in.create(in);
        } catch (TraceModelConflictRelation ex) {
            ((AccessHasFromGremlin)this.from).delegate().remove();
            ((ElementAccessHasElementGremlin)this.element).delegate().remove();
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
    public IAccessHasModule module() {
        if (module == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("module");
                if (gt.hasNext()) {
                    module = new AccessHasModuleGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return module;
    }

    @Override
    public IAccessHasFrom from() {
        if (from == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("from");
                if (gt.hasNext()) {
                    from = new AccessHasFromGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return from;
    }

    @Override
    public IAccessHasIn in() {
        if (in == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("in");
                if (gt.hasNext()) {
                    in = new AccessHasInGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return in;
    }

    @Override
    public IElementAccessHasElement element() {
        if (element == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("element");
                if (gt.hasNext()) {
                    element = new ElementAccessHasElementGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return element;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IElementAccess other) {
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
        if (!(obj instanceof ElementAccessGremlin))
            return false;
        ElementAccessGremlin other = (ElementAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (module == null) {
        if (other.module != null)
            return false;
    }
        if (!module().get().equals(other.module().get())) {
            return false;
        }
    if (element == null) {
        if (other.element != null)
            return false;
    }
        if (!element().get().equals(other.element().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        IModuleExecutionTrace module = module().get();
        result = prime * result + ((module == null) ? 0 : module.hashCode());
        IModelElementTrace element = element().get();
        result = prime * result + ((element == null) ? 0 : element.hashCode());
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
