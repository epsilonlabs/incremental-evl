 /*******************************************************************************
 * This file was automatically generated on: 2018-04-26.
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

import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantTraceHasInvariantContext reference. 
 */
public class InvariantTraceHasInvariantContext extends Feature implements IInvariantTraceHasInvariantContext {
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** The target(s) of the reference */
    protected IContextTrace target;
    
    /**
     * Instantiates a new IInvariantTraceHasInvariantContext.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasInvariantContext (IInvariantTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IContextTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IContextTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.constraints().set(source);
        if (related(target)) {
            return false;
        }
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
        result |= get() != null;
        result |= target.constraints().isUnique() && target.constraints().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(IContextTrace target) {
  
        return target.equals(this.target) && target.constraints().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(IContextTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IContextTrace target) {
        this.target = null;
    }

}