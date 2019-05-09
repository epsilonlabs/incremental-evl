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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.TinkerpopDelegate;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantTraceHasInvariantContext reference. 
 */
@SuppressWarnings("unused") 
public class InvariantTraceHasInvariantContextGremlin extends Feature
        implements IInvariantTraceHasInvariantContext, TinkerpopDelegate<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    /** Fast access for single-valued references */
    private Edge delegate;
    
    /**
     * Instantiates a new IInvariantTraceHasInvariantContext.
     *
     * @param source                the source element of the reference
     * @param delegate              the delegate edge
     * @param gts                   the graph taversal source   
     * @param factory               the factory used to instantiante the target
     */
    public InvariantTraceHasInvariantContextGremlin (
        IInvariantTrace source,
        Edge delegate,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory;
        this.delegate = delegate;
    }
    
   /**
     * Instantiates a new IInvariantTraceHasInvariantContext.
     *
     * @param source                the source element of the reference
     * @param gts                   the graph taversal source   
     * @param factory               the factory used to instantiante the target
     */
    public InvariantTraceHasInvariantContextGremlin (
        IInvariantTrace source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory;
    }
    
    
    // PUBLIC API
        
    @Override
    public IContextTrace get() {
        if (delegate == null) {
            try (ActiveTraversal agts = new ActiveTraversal(gts)) {
                GraphTraversal<Vertex, Edge> et = agts.V(source.getId()).outE("invariantContext");
                if (et.hasNext()) {
                    delegate = et.next();
                }
            } catch (Exception e) {
                throw new IllegalStateException("There was an error during graph traversal.", e);
            }
        }
        
        if (delegate == null) {
            return null;
        }
        Vertex to = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            to = agts.E(delegate).inV().next();
            
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return factory.createTraceElement(to, gts);
    }
    

    @Override
    public boolean create(IContextTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IContextTrace exists");
        }
        target.constraints().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IContextTrace target) {
        if (!related(target)) {
            return false;
        }
        target.constraints().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IContextTrace target) {
        boolean result = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            result |= delegate == null ?
                    agts.V(source.getId()).out("invariantContext").hasNext() :
                    agts.E(delegate).inV().hasId(target.getId()).hasNext();
            try (ActiveTraversal agts2 = new ActiveTraversal(gts)) {
                result |= delegate == null ? false : (target.constraints().isUnique() &&
                        agts2.V(target.getId()).out("constraints").hasId(source.getId()).hasNext());
            }
            catch (Exception ex) {
                result = false;
            }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
    }
    
    @Override
    public boolean related(IContextTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
        boolean result = false;
        boolean inTarget = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            inTarget = agts.V(target.getId()).out("constraints").hasId(source.getId()).hasNext();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
		  result = agts.E(delegate).inV().hasId(target.getId()).hasNext() && inTarget;
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return delegate;
    }
    
    @Override
    public GraphTraversalSource graphTraversalSource() {
        return gts;
    }
        
    // PRIVATE API
    
    @Override
    public void set(IContextTrace target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            delegate = agts.V(source.getId()).addE("invariantContext")
                    .to(agts.V(target.getId())).next();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        
    }
    
    @Override
    public void remove(IContextTrace target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.E(delegate).drop();
            delegate = null;
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
    }
}