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
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/
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
 * Implementation of IAllInstancesAccess. 
 */
@SuppressWarnings("unused") 
public class AllInstancesAccessGremlin implements IAllInstancesAccess, GremlinWrapper<Vertex> {
    
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
     * The ofKind.
     */
    private Boolean ofKind;

    
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
     * The type.
     */
    private IAllInstancesAccessHasType type;

    /**
     * Empty constructor for de/-serialization.
     */    
    // public AllInstancesAccessGremlin() { }
    
    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public AllInstancesAccessGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
    }
    
    /**
     * Instantiates a new AllInstancesAccessGremlin. The AllInstancesAccessGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccessGremlin(
        Boolean ofKind,
        IModuleElementTrace from,
        IExecutionContext in,
        IModelTypeTrace type,
        IModuleExecutionTrace container,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("ofKind", ofKind)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        // Equals References
        // this.type = new AllInstancesAccessHasTypeGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.from = new AccessHasFromGremlin(this, gts, wrapperFactory);
        // Derived Features
        // this.in = new AccessHasInGremlin(this, gts, wrapperFactory);
        try {
	        this.type.create(type);
	        this.from.create(from);
	        this.in.create(in);
        } catch (TraceModelConflictRelation ex) {
            ((AllInstancesAccessHasTypeGremlin)this.type).delegate().remove();
            ((AccessHasFromGremlin)this.from).delegate().remove();
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
    public Boolean getOfKind() {
        if (ofKind == null) {
	        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		        try {
		            ofKind = (Boolean) agts.V(delegate).values("ofKind").next();
		        } catch (NoSuchElementException ex) {
		            /** protected region ofKind on begin **/
	            // TODO Add default return value for AllInstancesAccessGremlin.getOfKind
	            throw new IllegalStateException("Add default return value for AllInstancesAccessGremlin.getOfKind", ex);
	            /** protected region ofKind end **/
		        }
		    } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
	    }    
        return ofKind;
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
    public IAllInstancesAccessHasType type() {
        if (type == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> gt = agts.V(delegate).outE("type");
                if (gt.hasNext()) {
                    type = new AllInstancesAccessHasTypeGremlin(this, gt.next(), this.gts, wrapperFactory);
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        return type;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("ofKind", getOfKind());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IAllInstancesAccess other) {
        if (other == null) {
            return false;
        }
        Boolean ofKind = Boolean.valueOf(getOfKind());
        Boolean otherOfKind = Boolean.valueOf(other.getOfKind());
        if (ofKind == null) {
            if (otherOfKind != null)
                return false;
        } else if (!ofKind.equals(otherOfKind)) {
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
        if (!(obj instanceof AllInstancesAccessGremlin))
            return false;
        AllInstancesAccessGremlin other = (AllInstancesAccessGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (type == null) {
        if (other.type != null)
            return false;
    }
        if (!type().get().equals(other.type().get())) {
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
        Boolean ofKind = Boolean.valueOf(getOfKind());
        result = prime * result + ((ofKind == null) ? 0 : ofKind.hashCode());
        IModelTypeTrace type = type().get();
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
