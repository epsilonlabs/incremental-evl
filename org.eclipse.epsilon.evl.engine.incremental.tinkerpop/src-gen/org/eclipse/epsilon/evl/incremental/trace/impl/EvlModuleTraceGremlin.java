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
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTraceGremlin implements IEvlModuleTrace, GremlinWrapper<Vertex> {
    
    /** A reference to the graph to use in factory methods and iterations */
    private Graph graph;

    /** The graph traversal source for all navigations */
    private GraphTraversalSource g;
    
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
    public EvlModuleTraceGremlin(String uri, Vertex vertex, Graph graph) throws TraceModelDuplicateRelation {
        this.delegate = vertex;
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
        g.V(delegate)
            .property("uri", uri)
            .iterate();
        this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this);
        this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this);
        this.models = new ModuleExecutionTraceHasModelsGremlin(this);

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
    public String getUri() {
        return (String) g.V(delegate).values("uri").next();
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        if (moduleElements == null) {
            this.moduleElements = new ModuleExecutionTraceHasModuleElementsGremlin(this);
        }
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        if (accesses == null) {
            this.accesses = new ModuleExecutionTraceHasAccessesGremlin(this);
        }
        return accesses;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        if (models == null) {
            this.models = new ModuleExecutionTraceHasModelsGremlin(this);
        }
        return models;
    }

    @Override
    public IContextTrace getOrCreateContextTrace(String kind, int index) throws EolIncrementalExecutionException {
        ContextTraceGremlin contextTrace = null;
        try {
            Vertex v = g.addV("ContextTrace").next();
            contextTrace = new ContextTraceGremlin(kind, index, this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasModuleElementsGremlin) this.moduleElements).getRaw()
                .hasLabel("ContextTrace")
                .has("kind", kind)
                .has("index", index)
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                        + "duplicate but previous one was not found.");
            }
            contextTrace = new ContextTraceGremlin();
            contextTrace.delegate(gt.next());
            contextTrace.graph(graph);
        }
        return contextTrace;
    }      

                  
    @Override
    public IElementAccess getOrCreateElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException {
        ElementAccessGremlin elementAccess = null;
        try {
            Vertex v = g.addV("ElementAccess").next();
            elementAccess = new ElementAccessGremlin(executionTrace, element, this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (elementAccess != null) {
    	        return elementAccess;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
                .hasLabel("ElementAccess")
                .as("v")
                .to(Direction.OUT, "value").hasId(executionTrace.getId())
                .select("v")
                .as("v")
                .to(Direction.OUT, "value").hasId(element.getId())
                .select("v")
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
                        + "duplicate but previous one was not found.");
            }
            elementAccess = new ElementAccessGremlin();
            elementAccess.delegate(gt.next());
            elementAccess.graph(graph);
        }
        return elementAccess;
    }      

    @Override
    public IAllInstancesAccess getOrCreateAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException {
        AllInstancesAccessGremlin allInstancesAccess = null;
        try {
            Vertex v = g.addV("AllInstancesAccess").next();
            allInstancesAccess = new AllInstancesAccessGremlin(ofKind, executionTrace, type, this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (allInstancesAccess != null) {
    	        return allInstancesAccess;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
                .hasLabel("AllInstancesAccess")
                .has("ofKind", ofKind)
                .as("v")
                .to(Direction.OUT, "value").hasId(executionTrace.getId())
                .select("v")
                .as("v")
                .to(Direction.OUT, "value").hasId(type.getId())
                .select("v")
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
                        + "duplicate but previous one was not found.");
            }
            allInstancesAccess = new AllInstancesAccessGremlin();
            allInstancesAccess.delegate(gt.next());
            allInstancesAccess.graph(graph);
        }
        return allInstancesAccess;
    }      

    @Override
    public IPropertyAccess getOrCreatePropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException {
        PropertyAccessGremlin propertyAccess = null;
        try {
            Vertex v = g.addV("PropertyAccess").next();
            propertyAccess = new PropertyAccessGremlin(executionTrace, property, this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyAccess != null) {
    	        return propertyAccess;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasAccessesGremlin) this.accesses).getRaw()
                .hasLabel("PropertyAccess")
                .as("v")
                .to(Direction.OUT, "value").hasId(executionTrace.getId())
                .select("v")
                .as("v")
                .to(Direction.OUT, "value").hasId(property.getId())
                .select("v")
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
                        + "duplicate but previous one was not found.");
            }
            propertyAccess = new PropertyAccessGremlin();
            propertyAccess.delegate(gt.next());
            propertyAccess.graph(graph);
        }
        return propertyAccess;
    }      

                  
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        ModelAccessGremlin modelAccess = null;
        try {
            Vertex v = g.addV("ModelAccess").next();
            modelAccess = new ModelAccessGremlin(modelName, modelTrace, this, v, graph);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelAccess != null) {
    	        return modelAccess;
    	    }
            GraphTraversal<Vertex, Vertex> gt = ((ModuleExecutionTraceHasModelsGremlin) this.models).getRaw()
                .hasLabel("ModelAccess")
                .has("modelName", modelName)
                .as("v")
                .to(Direction.OUT, "value").hasId(modelTrace.getId())
                .select("v")
                ;
            if (!gt.hasNext()) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelAccess was "
                        + "duplicate but previous one was not found.");
            }
            modelAccess = new ModelAccessGremlin();
            modelAccess.delegate(gt.next());
            modelAccess.graph(graph);
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
    public Graph graph() {
        return graph;    
    }

    @Override
    public void graph(Graph graph) {
        this.g = new GraphTraversalSource(graph);
        this.graph = graph;
    }
}
