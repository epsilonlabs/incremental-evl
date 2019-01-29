 /*******************************************************************************
 * This file was automatically generated on: 2019-01-25.
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
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IElementAccessHasElement;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IElementAccessHasElement reference. 
 */
public class ElementAccessHasElementGremlin extends Feature
        implements IElementAccessHasElement, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IElementAccess source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    /** Fast access for single-valued references */
    private Edge delegate;
    
    /**
     * Instantiates a new IElementAccessHasElement.
     *
     * @param source the source of the reference
     */
    public ElementAccessHasElementGremlin (
        IElementAccess source,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory; 
    }
    
    // PUBLIC API
        
    @Override
    public IModelElementTrace get() {
        if (delegate == null) {
            return null;
        }
        GraphTraversalSource g = startTraversal();
        IModelElementTrace result = null;
        try {
            Vertex to = g.E(delegate).inV().next();
            result = (IModelElementTrace) factory.createTraceElement(to, gts);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IModelElementTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            if (related(target)) {
                return true;
            }
            throw new TraceModelConflictRelation("Relation to previous IModelElementTrace exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
            result |= delegate == null ? g.V(source.getId()).out("element").hasNext() : g.E(delegate).inV().hasId(target.getId()).hasNext();
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
		  result = g.E(delegate).inV().hasId(target.getId()).hasNext();
		}
		finally {
            finishTraversal(g);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return delegate;
    }

    @Override
    public void delegate(Edge delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelElementTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            delegate = g.V(source.getId()).addE("element").to(g.V(target.getId())).next();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.E(delegate).drop();
            delegate = null;
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