 /*******************************************************************************
 * This file was automatically generated on: 2019-05-09.
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

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;


/**
 * Implementation of IModuleExecutionTraceHasAccesses reference. 
 */
@SuppressWarnings("unused") 
public class ModuleExecutionTraceHasAccessesGremlin extends Feature
        implements IModuleExecutionTraceHasAccesses, TinkerpopDelegate<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IModuleExecutionTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    
    /**
     * Instantiates a new IModuleExecutionTraceHasAccesses.
     *
     * @param source                the source element of the reference
     * @param delegate              the delegate edge
     * @param gts                   the graph taversal source   
     * @param factory               the factory used to instantiante the target
     */
    public ModuleExecutionTraceHasAccessesGremlin (
        IModuleExecutionTrace source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(false);
        this.source = source;
        this.gts = gts;
        this.factory = factory;
    }
    
    
    
    // PUBLIC API
        
    @Override
    public Iterator<IAccess> get() {
        return new GremlinUtils.IncrementalFactoryIterator<IAccess>(getRaw(),
                gts, factory);
    }
    
    /**
     * Get the Tinkerpop GraphTraversal iterator of the vertices that are part of the relation.
     */
    public GraphTraversal<Vertex, Vertex> getRaw() {
        GraphTraversal<Vertex, Vertex> result = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            result = agts.V(source.getId()).out("accesses");
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
    }
    

    @Override
    public boolean create(IAccess target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IAccess exists");
        }
        target.module().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IAccess target) {
        if (!related(target)) {
            return false;
        }
        target.module().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IAccess target) {
        boolean result = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
	        if (isUnique()) {
	            GraphTraversal<Vertex, Vertex> gt =  agts.V(source.getId()).out("accesses");
                for (Entry<String, Object> id : target.getIdProperties().entrySet()) {
                    gt.has(id.getKey(), id.getValue());
                }
                result |= gt.hasNext();
            }
            result |= target.module().get() != null;
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
    	if (target == null) {
			return false;
		}
        boolean result = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		  result = agts.V(source.getId()).out("accesses").hasId(target.getId()).hasNext() && source.equals(target.module().get());
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return null;
    }
    
    @Override
    public GraphTraversalSource graphTraversalSource() {
        return gts;
    }
        
    // PRIVATE API
    
    @Override
    public void set(IAccess target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(source.getId()).addE("accesses")
                    .to(agts.V(target.getId())).iterate();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        
    }
    
    @Override
    public void remove(IAccess target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.V(source.getId())
                    .outE("accesses")
                    .as("e").inV()
                    .hasId(target.getId())
                    .select("e").drop().iterate();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
    }
}