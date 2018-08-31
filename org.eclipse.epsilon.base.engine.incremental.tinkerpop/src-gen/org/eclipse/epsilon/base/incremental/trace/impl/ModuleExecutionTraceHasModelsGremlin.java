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
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.trace.gremlin.util.GremlinUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModuleExecutionTraceHasModels reference. 
 */
public class ModuleExecutionTraceHasModelsGremlin extends Feature
        implements IModuleExecutionTraceHasModels, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModuleExecutionTrace source;
    
 
    
    /**
     * Instantiates a new IModuleExecutionTraceHasModels.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionTraceHasModelsGremlin (IModuleExecutionTrace source, GraphTraversalSource gts) {
        super(true);
        this.source = source;
        this.gts = gts;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IModelAccess> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IModelAccess, Vertex>(getRaw(), gts);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public  GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversalSource g = startTraversal();
        GraphTraversal<Vertex, Vertex> result = null;
        try {
            result = g.V(source.getId()).outE("models").toV(Direction.OUT);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IModelAccess target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelAccess exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelAccess target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelAccess target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
	        // FIXME We can just remove this during generation?
	        if (isUnique()) {
	            result |= g.V(source.getId()).out("models")
                    .has("modelName", target.getModelName())
                    .hasNext();
            }
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelAccess target) {
    	if (target == null) {
			return false;
		}
        GraphTraversalSource g = startTraversal();
        boolean result = false;
        try {
		  result = g.V(source.getId()).out("models").hasId(target.getId()).hasNext();
		}
		finally {
            finishTraversal(g);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return null;
    }

    @Override
    public void delegate(Edge delegate) {
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelAccess target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).addE("models").to(g.V(target.getId())).iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IModelAccess target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.V(source.getId()).outE("models").as("e").inV().hasId(target.getId()).select("e").drop().iterate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
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