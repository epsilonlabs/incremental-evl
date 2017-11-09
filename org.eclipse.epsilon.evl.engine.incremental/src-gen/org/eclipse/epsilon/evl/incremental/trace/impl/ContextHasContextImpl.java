 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.evl.incremental.trace.ContextHasContext;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ContextHasContext reference. 
 */
public class ContextHasContextImpl extends Feature implements ContextHasContext {
    
    /** The source(s) of the reference */
    protected Context source;
    
    /** The target(s) of the reference */
    protected Queue<ModelElement> target =  new ConcurrentLinkedQueue<ModelElement>();
    
    /**
     * Instantiates a new ContextHasContext.
     *
     * @param source the source of the reference
     */
    public ContextHasContextImpl (Context source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<ModelElement> get() {
        return target;
    }
    
    @Override
    public boolean create(ModelElement target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModelElement target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ModelElement target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(ModelElement target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(ModelElement target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ModelElement target) {
        this.target.remove(target);
    }

}