 /*******************************************************************************
 * This file was automatically generated on: 2018-09-07.
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
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
/** protected region ExecutionContextImports on begin **/
/** protected region ExecutionContextImports end **/
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IExecutionContext. 
 */
public class ExecutionContextGremlin implements IExecutionContext, GremlinWrapper<Vertex> {
    

    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The delegate Vertex */
    private Vertex delegate;
    
    /**
     * * The variables that make up the context.
     */
    private IExecutionContextHasContextVariables contextVariables;

    /**
     * * The different accesses that where recorded during execution.
     */
    private IExecutionContextHasAccesses accesses;

    /**
     * Empty constructor for deserialization.
     */    
    public ExecutionContextGremlin() { }
    
    /**
     * Instantiates a new ExecutionContextGremlin. The ExecutionContextGremlin is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ExecutionContextGremlin(
        IContextModuleElementTrace container, Vertex vertex, GraphTraversalSource gts) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.delegate = vertex;
        this.gts = gts;
        if (!container.executionContext().create(this)) {
            throw new TraceModelDuplicateElement();
        };
        this.contextVariables = new ExecutionContextHasContextVariablesGremlin(this, gts, BaseTraceFactory.getFactory());
        this.accesses = new ExecutionContextHasAccessesGremlin(this, gts, BaseTraceFactory.getFactory());
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
    public IExecutionContextHasContextVariables contextVariables() {
        if (contextVariables == null) {
            contextVariables = new ExecutionContextHasContextVariablesGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("contextVariables");
                if (gt.hasNext()) {
                    ((ExecutionContextHasContextVariablesGremlin)contextVariables).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return contextVariables;
    }

    @Override
    public IExecutionContextHasAccesses accesses() {
        if (accesses == null) {
            accesses = new ExecutionContextHasAccessesGremlin(this, this.gts, BaseTraceFactory.getFactory());
            GraphTraversalSource g = startTraversal();
            try {
                GraphTraversal<Vertex, Edge> gt = g.V(delegate).outE("accesses");
                if (gt.hasNext()) {
                    ((ExecutionContextHasAccessesGremlin)accesses).delegate(gt.next());
                }
            } finally {
                finishTraversal(g);
            }
        }
        return accesses;
    }

    @Override
    public IModelElementVariable getOrCreateModelElementVariable(String name, IModelElementTrace value) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ModelElementVariableGremlin modelElementVariable = null;
        try {
    	    GraphTraversal<Vertex, Vertex> gt = g.V(delegate).out("contextVariables").has("name", name);
    	    if (gt.hasNext()) {
    	        modelElementVariable = new ModelElementVariableGremlin();
    	        modelElementVariable.delegate(gt.next());
    	        modelElementVariable.graphTraversalSource(gts);
    	    }
    	    else {
    	        Vertex v = null;
    	        try {
    	            v = g.addV("ModelElementVariable").next();
    	            modelElementVariable = new ModelElementVariableGremlin(name, value, this, v, gts);
    	        } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
    	            g.V(v).as("v").properties().drop().select("v").drop();
    	            throw new EolIncrementalExecutionException("Error creating requested ModelElementVariable", e);
    	        }
    	    }
    	} finally {
            finishTraversal(g);
        }    
        return modelElementVariable;
    }      

    @Override
    public IElementAccess getOrCreateElementAccess(IModuleElementTrace from, IModelElementTrace element) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        ElementAccessGremlin elementAccess = null;
        try {
            Vertex v = null;
            try {
                v = g.addV("ElementAccess").next();
                elementAccess = new ElementAccessGremlin(from, element, this, v, gts);
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                g.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested ElementAccess", e);
            }
    	} finally {
            finishTraversal(g);
        }    
        return elementAccess;
    }      

    @Override
    public IAllInstancesAccess getOrCreateAllInstancesAccess(Boolean ofKind, IModuleElementTrace from, IModelTypeTrace type) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        AllInstancesAccessGremlin allInstancesAccess = null;
        try {
            Vertex v = null;
            try {
                v = g.addV("AllInstancesAccess").next();
                allInstancesAccess = new AllInstancesAccessGremlin(ofKind, from, type, this, v, gts);
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                g.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested AllInstancesAccess", e);
            }
    	} finally {
            finishTraversal(g);
        }    
        return allInstancesAccess;
    }      

    @Override
    public IPropertyAccess getOrCreatePropertyAccess(IModuleElementTrace from, IPropertyTrace property) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        PropertyAccessGremlin propertyAccess = null;
        try {
            Vertex v = null;
            try {
                v = g.addV("PropertyAccess").next();
                propertyAccess = new PropertyAccessGremlin(from, property, this, v, gts);
            } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                g.V(v).as("v").properties().drop().select("v").drop();
                throw new EolIncrementalExecutionException("Error creating requested PropertyAccess", e);
            }
    	} finally {
            finishTraversal(g);
        }    
        return propertyAccess;
    }      


    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IExecutionContext other) {
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
        if (!(obj instanceof ExecutionContextGremlin))
            return false;
        ExecutionContextGremlin other = (ExecutionContextGremlin) obj;
        if (!sameIdentityAs(other))
            return false;
    if (contextVariables == null) {
        if (other.contextVariables != null)
            return false;
    }
        if (!IncrementalUtils.equalUniqueIterators(contextVariables().get(), other.contextVariables().get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contextVariables().get() == null) ? 0 : contextVariables().get().hashCode());
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
