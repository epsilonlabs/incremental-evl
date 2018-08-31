 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;

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
     * * The different accesses that where recorded during execution.
     */
    private IModuleExecutionTraceHasAccesses accesses;

    /**
     * * The different models involved in the execution
     */
    private IModuleExecutionTraceHasModels models;

    /**
     * Empty constructor for deserialization.
     */    
    public EvlModuleTraceGremlin() {
    }
    
    /**
     * Instantiates a new EvlModuleTraceGremlin. The EvlModuleTraceGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTraceGremlin(
        String uri, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        // FIXME We need to destroy the created edges when any edge fails
        GraphTraversalSource g = startTraversal();
        try {
            g.V(delegate)
            .property("uri", uri)
            .iterate();
        }
        finally {
            finishTraversal(g);
        }
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, gts);
        this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this, gts);
        this.models = new ModuleExecutionTraceHasModelsGremlin(this, gts);
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
        // TODO Add default return value for EvlModuleTraceGremlin.getgetUri
        return (String) g.V(delegate).values("uri").next();
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
            moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this, this.gts);
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
    public IModuleExecutionTraceHasAccesses accesses() {
        if (accesses == null) {
            accesses = new ModuleExecutionTraceHasAccessesGremlin(this, this.gts);
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
    public IModuleExecutionTraceHasModels models() {
        if (models == null) {
            models = new ModuleExecutionTraceHasModelsGremlin(this, this.gts);
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
    public IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ContextTraceGremlin contextTrace = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("ContextTrace").next();
    	        contextTrace = new ContextTraceGremlin(kind, index, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (contextTrace != null) {
    		        return contextTrace;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasModuleElementsGremlin) this.moduleElements).getRaw()
    	            .hasLabel("ContextTrace")
    	            .has("kind", kind)
    	            .has("index", index)
    	            .as("a") 
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        contextTrace = new ContextTraceGremlin();
    	        contextTrace.delegate(gt.next());
    	        contextTrace.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return contextTrace;
    }      

                  
    @Override
    public IElementAccess getOrCreateElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ElementAccessGremlin elementAccess = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("ElementAccess").next();
    	        elementAccess = new ElementAccessGremlin(executionTrace, element, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (elementAccess != null) {
    		        return elementAccess;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
    	            .hasLabel("ElementAccess")
    	            .as("a") 
    	            .out("executionTrace").hasId(executionTrace.getId())
    	            .in("executionTrace").where(P.eq("a"))
    	            .out("element").hasId(element.getId())
    	            .in("element").where(P.eq("a"))
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        elementAccess = new ElementAccessGremlin();
    	        elementAccess.delegate(gt.next());
    	        elementAccess.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return elementAccess;
    }      

    @Override
    public IAllInstancesAccess getOrCreateAllInstancesAccess(Boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        AllInstancesAccessGremlin allInstancesAccess = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("AllInstancesAccess").next();
    	        allInstancesAccess = new AllInstancesAccessGremlin(ofKind, executionTrace, type, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (allInstancesAccess != null) {
    		        return allInstancesAccess;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
    	            .hasLabel("AllInstancesAccess")
    	            .has("ofKind", ofKind)
    	            .as("a") 
    	            .out("executionTrace").hasId(executionTrace.getId())
    	            .in("executionTrace").where(P.eq("a"))
    	            .out("type").hasId(type.getId())
    	            .in("type").where(P.eq("a"))
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        allInstancesAccess = new AllInstancesAccessGremlin();
    	        allInstancesAccess.delegate(gt.next());
    	        allInstancesAccess.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return allInstancesAccess;
    }      

    @Override
    public IPropertyAccess getOrCreatePropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        PropertyAccessGremlin propertyAccess = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("PropertyAccess").next();
    	        propertyAccess = new PropertyAccessGremlin(executionTrace, property, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (propertyAccess != null) {
    		        return propertyAccess;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
    	            .hasLabel("PropertyAccess")
    	            .as("a") 
    	            .out("executionTrace").hasId(executionTrace.getId())
    	            .in("executionTrace").where(P.eq("a"))
    	            .out("property").hasId(property.getId())
    	            .in("property").where(P.eq("a"))
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        propertyAccess = new PropertyAccessGremlin();
    	        propertyAccess.delegate(gt.next());
    	        propertyAccess.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return propertyAccess;
    }      

                  
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelAccessGremlin modelAccess = null;
        try {
    	    Vertex v = null;
    	    try {
    	        v = g.addV("ModelAccess").next();
    	        modelAccess = new ModelAccessGremlin(modelName, modelTrace, this, v, gts);
    	    } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	        v.remove();
    	    } finally {
    		    if (modelAccess != null) {
    		        return modelAccess;
    		    }
    	        GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasModelsGremlin) this.models).getRaw()
    	            .hasLabel("ModelAccess")
    	            .has("modelName", modelName)
    	            .as("a") 
    	            .out("modelTrace").hasId(modelTrace.getId())
    	            .in("modelTrace").where(P.eq("a"))
    	            .select("a");
    	        if (!gt.hasNext()) {
    	            throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelAccess was "
    	                    + "duplicate but previous one was not found.");
    	        }
    	        modelAccess = new ModelAccessGremlin();
    	        modelAccess.delegate(gt.next());
    	        modelAccess.graphTraversalSource(gts);
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return modelAccess;
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
