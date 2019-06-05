 /*******************************************************************************
 * This file was automatically generated on: 2019-06-04.
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
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
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
 * Implementation of IEvlModuleTrace. 
 */
@SuppressWarnings("unused") 
public class EvlModuleTraceGremlin implements IEvlModuleTrace, TinkerpopDelegate<Vertex> {
    
    /** The graph traversal source for all navigations */
    private final GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /** The factory used to wrap the vertex's incident vertices */
    private TraceFactory wrapperFactory;
    
    /**
     * * The module elements that conform the module.
       * Each language shoud specialize this class to represent the structure of its AST.
     */
    private IModuleExecutionTraceHasModuleElements moduleElements;

    /**
     * * The different models involved in the execution
     */
    private IModuleExecutionTraceHasModels models;

    /**
     * * The different accesses that where recorded during execution.
     */
    private IModuleExecutionTraceHasAccesses accesses;


    /**
     * Constructor for factory, only needs wrapped vertex, traversal source and factory
     */
    public EvlModuleTraceGremlin (
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, gts, wrapperFactory);
        this.models = new ModuleExecutionTraceHasModelsGremlin(this, gts, wrapperFactory);
        this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this, gts, wrapperFactory);
    }
    
    /**
     * Instantiates a new EvlModuleTraceGremlin. The EvlModuleTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTraceGremlin(
        String uri,
        Vertex vertex,
        GraphTraversalSource gts,
        TraceFactory wrapperFactory) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        this.wrapperFactory = wrapperFactory;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, gts, wrapperFactory);
        this.models = new ModuleExecutionTraceHasModelsGremlin(this, gts, wrapperFactory);
        this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this, gts, wrapperFactory);
    
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
    public String getUri() {
        if (delegate != null) {
            Iterator<VertexProperty<Object>> values = delegate.properties("uri");
            if (values.hasNext()) {
                return (String) values.next().value();
            }
            else {
                /** protected region uri on begin **/
	            return "";
	            /** protected region uri end **/
            }
            
        }
        return null;
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        
        return models;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        
        return accesses;
    }

    @Override
    public IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException {    
        ContextTraceGremlin contextTrace = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("moduleElements").has("kind", kind)
                                                .has("index", index);
            if (gt.hasNext()) {
                contextTrace = new ContextTraceGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("ContextTrace").property("tag", GremlinUtils.identityToString(kind, index, this)).next();
                    /* protected region contextTraceTypeOverride on begin */
                    contextTrace = new ContextTraceGremlin(kind, index, this, v, gts, wrapperFactory); 
                    /* protected region contextTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ContextTrace", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return contextTrace;
    }      

    
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {    
        ModelAccessGremlin modelAccess = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(delegate).out("models").has("modelName", modelName);
            if (gt.hasNext()) {
                modelAccess = new ModelAccessGremlin(gt.next(), gts, wrapperFactory);
            }
            else {
                Vertex v = null;
                try {
                    v = agts.addV("ModelAccess").property("tag", GremlinUtils.identityToString(modelName, modelTrace, this)).next();
                    /* protected region modelAccessTypeOverride on begin */
                    modelAccess = new ModelAccessGremlin(modelName, modelTrace, this, v, gts, wrapperFactory); 
                    /* protected region modelAccessTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    agts.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ModelAccess", e);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return modelAccess;
    }      
    
    @SuppressWarnings("unchecked")
    public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException {
        A result = null;
        switch(accessClass.getSimpleName()) {
            case "ISatisfiesAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IModelElementTrace;
                ISatisfiesAccess satisfiesAccess = null;
                try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                    Vertex v = null;
                    try {
                        v = agts.addV("SatisfiesAccess").next();
                        /* protected region satisfiesAccessTypeOverride on begin */
                        satisfiesAccess = new SatisfiesAccessGremlin(
                        	(IModuleElementTrace) args[0],
                        	(IExecutionContext) args[1],
                        	(IModelElementTrace) args[2], this, v, gts, wrapperFactory);
                        /* protected region satisfiesAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        agts.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested SatisfiesAccess", e);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("There was an error during graph traversal.", e);
                } 
                result = (A) satisfiesAccess;
                break;
            case "IElementAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IModelElementTrace;
                IElementAccess elementAccess = null;
                try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                    Vertex v = null;
                    try {
                        v = agts.addV("ElementAccess").next();
                        /* protected region elementAccessTypeOverride on begin */
                        elementAccess = new ElementAccessGremlin(
                        		(IModuleElementTrace) args[0],
                            	(IExecutionContext) args[1],
                            	(IModelElementTrace) args[2], this, v, gts, wrapperFactory);
                        /* protected region elementAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        agts.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested ElementAccess", e);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("There was an error during graph traversal.", e);
                } 
                result = (A) elementAccess;
                break;
            case "IAllInstancesAccess":
                assert args[0] instanceof Boolean;
                assert args[1] instanceof IModuleElementTrace;
                assert args[2] instanceof IExecutionContext;
                assert args[3] instanceof IModelTypeTrace;
                IAllInstancesAccess allInstancesAccess = null;
                try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                    Vertex v = null;
                    try {
                        v = agts.addV("AllInstancesAccess").property("tag", GremlinUtils.identityToString((Boolean) args[0], (IModuleElementTrace) args[1], (IExecutionContext) args[2], (IModelTypeTrace) args[3], this)).next();
                        /* protected region allInstancesAccessTypeOverride on begin */
                        allInstancesAccess = new AllInstancesAccessGremlin(
                        		(Boolean) args[0],
                        		(IModuleElementTrace) args[1],
                        		(IExecutionContext) args[2],
                            	(IModelTypeTrace) args[3], this, v, gts, wrapperFactory);
                        /* protected region allInstancesAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        agts.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested AllInstancesAccess", e);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("There was an error during graph traversal.", e);
                } 
                result = (A) allInstancesAccess;
                break;
            case "IPropertyAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IPropertyTrace;
                IPropertyAccess propertyAccess = null;
                try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                    Vertex v = null;
                    try {
                        v = agts.addV("PropertyAccess").next();
                        /* protected region propertyAccessTypeOverride on begin */
                        propertyAccess = new PropertyAccessGremlin(
                        		(IModuleElementTrace) args[0],
                            	(IExecutionContext) args[1],
                            	(IPropertyTrace) args[2], this, v, gts, wrapperFactory);
                        /* protected region propertyAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        agts.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested PropertyAccess", e);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("There was an error during graph traversal.", e);
                } 
                result = (A) propertyAccess;
                break;
        }
        return result;
    }
    
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("uri", getUri());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IEvlModuleTrace other) {
        if (other == null) {
            return false;
        }
        String uri = getUri();
        String otherUri = other.getUri();
        if (uri == null) {
            if (otherUri != null)
                return false;
        } else if (!uri.equals(otherUri)) {
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
        if (!(obj instanceof EvlModuleTraceGremlin))
            return false;
        EvlModuleTraceGremlin other = (EvlModuleTraceGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
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
