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
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTraceGremlin implements IEvlModuleTrace, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
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
     * Empty constructor for deserialization.
     */    
    public EvlModuleTraceGremlin() { }
    
    /**
     * Instantiates a new EvlModuleTraceGremlin. The EvlModuleTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTraceGremlin(
        String uri, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        // Derived Features
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.models = new ModuleExecutionTraceHasModelsGremlin(this, gts, EvlTraceFactory.getFactory());
        // Derived Features
        this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this, gts, EvlTraceFactory.getFactory());
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
        GraphTraversalSource g = startTraversal();
        String result = null;
        try {
	        try {
	            result = (String) g.V(delegate).values("uri").next();
	        } catch (NoSuchElementException ex) {
	            /** protected region uri on begin **/
	            result = "";
	            /** protected region uri end **/
	        }
	    } finally {
            finishTraversal(g);
        }    
        return result;
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        if (moduleElements == null) {
            moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("moduleElements");
                if (gt.hasNext()) {
                    ((ModuleExecutionTraceHasModuleElementsGremlin)moduleElements).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        if (models == null) {
            models = new ModuleExecutionTraceHasModelsGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("models");
                if (gt.hasNext()) {
                    ((ModuleExecutionTraceHasModelsGremlin)models).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return models;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        if (accesses == null) {
            accesses = new ModuleExecutionTraceHasAccessesGremlin(this, this.gts, EvlTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("accesses");
                if (gt.hasNext()) {
                    ((ModuleExecutionTraceHasAccessesGremlin)accesses).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return accesses;
    }

    @Override
    public IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ContextTraceGremlin contextTrace = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("moduleElements").has("kind", kind)
                                                .has("index", index);
            if (gt.hasNext()) {
                contextTrace = new ContextTraceGremlin();
                contextTrace.delegate(gt.next());
                contextTrace.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("ContextTrace").property("tag", GremlinUtils.identityToString(kind, index, this)).next();
                    /* protected region contextTraceTypeOverride on begin */
                    contextTrace = new ContextTraceGremlin(kind, index, this, v, gts); 
                    /* protected region contextTraceTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ContextTrace", e);
                }
            }
        } finally {
            finishTraversal(g);
        }  
        return contextTrace;
    }      

    
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelAccessGremlin modelAccess = null;
        try {
            GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("models").has("modelName", modelName);
            if (gt.hasNext()) {
                modelAccess = new ModelAccessGremlin();
                modelAccess.delegate(gt.next());
                modelAccess.graphTraversalSource(gts);
            }
            else {
                Vertex v = null;
                try {
                    v = g.addV("ModelAccess").property("tag", GremlinUtils.identityToString(modelName, modelTrace, this)).next();
                    /* protected region modelAccessTypeOverride on begin */
                    modelAccess = new ModelAccessGremlin(modelName, modelTrace, this, v, gts); 
                    /* protected region modelAccessTypeOverride end */
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ModelAccess", e);
                }
            }
        } finally {
            finishTraversal(g);
        }  
        return modelAccess;
    }      
    
    @SuppressWarnings("unchecked")
    public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        A result = null;
        switch(accessClass.getSimpleName()) {
            case "ISatisfiesAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IModelElementTrace;
                ISatisfiesAccess satisfiesAccess = null;
                try {
                    Vertex v = null;
                    try {
                        v = g.addV("SatisfiesAccess").next();
                        /* protected region satisfiesAccessTypeOverride on begin */
                        satisfiesAccess = new SatisfiesAccessGremlin(
                        	(IModuleElementTrace) args[0],
                        	(IExecutionContext) args[1],
                        	(IModelElementTrace) args[2], this, v, gts);
                        /* protected region satisfiesAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        g.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested SatisfiesAccess", e);
                    }
                } finally {
                    finishTraversal(g);
                }  
                result = (A) satisfiesAccess;
                break;
            case "IElementAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IModelElementTrace;
                IElementAccess elementAccess = null;
                try {
                    Vertex v = null;
                    try {
                        v = g.addV("ElementAccess").next();
                        /* protected region elementAccessTypeOverride on begin */
                        elementAccess = new ElementAccessGremlin(
                        		(IModuleElementTrace) args[0],
                            	(IExecutionContext) args[1],
                            	(IModelElementTrace) args[2], this, v, gts);
                        /* protected region elementAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        g.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested ElementAccess", e);
                    }
                } finally {
                    finishTraversal(g);
                }  
                result = (A) elementAccess;
                break;
            case "IAllInstancesAccess":
                assert args[0] instanceof Boolean;
                assert args[1] instanceof IModuleElementTrace;
                assert args[2] instanceof IExecutionContext;
                assert args[3] instanceof IModelTypeTrace;
                IAllInstancesAccess allInstancesAccess = null;
                try {
                    Vertex v = null;
                    try {
                        v = g.addV("AllInstancesAccess").property("tag", GremlinUtils.identityToString((Boolean) args[0], (IModuleElementTrace) args[1], (IExecutionContext) args[2], (IModelTypeTrace) args[3], this)).next();
                        /* protected region allInstancesAccessTypeOverride on begin */
                        allInstancesAccess = new AllInstancesAccessGremlin(
                        		(Boolean) args[0],
                        		(IModuleElementTrace) args[1],
                        		(IExecutionContext) args[2],
                            	(IModelTypeTrace) args[3], this, v, gts);
                        /* protected region allInstancesAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        g.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested AllInstancesAccess", e);
                    }
                } finally {
                    finishTraversal(g);
                }  
                result = (A) allInstancesAccess;
                break;
            case "IPropertyAccess":
                assert args[0] instanceof IModuleElementTrace;
                assert args[1] instanceof IExecutionContext;
                assert args[2] instanceof IPropertyTrace;
                IPropertyAccess propertyAccess = null;
                try {
                    Vertex v = null;
                    try {
                        v = g.addV("PropertyAccess").next();
                        /* protected region propertyAccessTypeOverride on begin */
                        propertyAccess = new PropertyAccessGremlin(
                        		(IModuleElementTrace) args[0],
                            	(IExecutionContext) args[1],
                            	(IPropertyTrace) args[2], this, v, gts);
                        /* protected region propertyAccessTypeOverride end */
                    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                        g.V(v).as("v").properties().drop().select("v").drop();
                        throw new EolIncrementalExecutionException("Error creating requested PropertyAccess", e);
                    }
                } finally {
                    finishTraversal(g);
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
