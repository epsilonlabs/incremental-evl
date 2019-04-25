 /*******************************************************************************
 * This file was automatically generated on: 2019-04-25.
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
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTraceHasElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IPropertyTraceHasElementTrace reference. 
 */
public class PropertyTraceHasElementTraceGremlin extends Feature
        implements IPropertyTraceHasElementTrace, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IPropertyTrace source;
    
    /** Factory used to wrap referenced elements */
    protected final TraceFactory factory;
    
    /** Fast access for single-valued references */
    private Edge delegate;
    
    /**
     * Instantiates a new IPropertyTraceHasElementTrace.
     *
     * @param source the source of the reference
     */
    public PropertyTraceHasElementTraceGremlin (
        IPropertyTrace source,
        Edge delegate,
        GraphTraversalSource gts, 
        TraceFactory factory) {
        super(true);
        this.source = source;
        this.gts = gts;
        this.factory = factory;
        this.delegate = delegate;
    }
    
    // PUBLIC API
        
    @Override
    public IModelElementTrace get() {
        if (delegate == null) {
            return null;
        }
        IModelElementTrace result = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            Vertex to = agts.E(delegate).inV().next();
            result = (IModelElementTrace) factory.createTraceElement(to, gts);
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
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
        target.properties().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.properties().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            result |= delegate == null ?
                    agts.V(source.getId()).out("elementTrace").hasNext() :
                    agts.E(delegate).inV().hasId(target.getId()).hasNext();
            try (ActiveTraversal agts2 = new ActiveTraversal(gts)) {
                result |= delegate == null ? false : (target.properties().isUnique() &&
                        agts2.V(target.getId()).out("properties").hasId(source.getId()).hasNext());
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
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
        boolean result = false;
        boolean inTarget = false;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            inTarget = agts.V(target.getId()).out("properties").hasId(source.getId()).hasNext();
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
    public void set(IModelElementTrace target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            delegate = agts.V(source.getId()).addE("elementTrace")
                    .to(agts.V(target.getId())).next();
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            agts.E(delegate).drop();
            delegate = null;
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
    }
}