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
        // Equals References
        this.contextVariables = new ExecutionContextHasContextVariablesGremlin(this, gts, BaseTraceFactory.getFactory());
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
                    v = g.addV("ModelElementVariable").property("tag", GremlinUtils.identityToString(name, value, this)).next();
                    /* protected region modelElementVariableTypeOverride on begin */
                    modelElementVariable = new ModelElementVariableGremlin(name, value, this, v, gts); 
                    /* protected region modelElementVariableTypeOverride end */
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
        Iterator<IModelElementVariable> contextVariables = contextVariables().get();
        result = prime * result + ((contextVariables == null) ? 0 : contextVariables.hashCode());
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
