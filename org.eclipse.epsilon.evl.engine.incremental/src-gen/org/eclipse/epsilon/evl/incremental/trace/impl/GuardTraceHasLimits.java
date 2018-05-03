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

import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTraceHasLimits;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IGuardTraceHasLimits reference. 
 */
public class GuardTraceHasLimits extends Feature implements IGuardTraceHasLimits {
    
    /** The source(s) of the reference */
    protected IGuardTrace source;
    
    /** The target(s) of the reference */
    protected IGuardedElementTrace target;
    
    /**
     * Instantiates a new IGuardTraceHasLimits.
     *
     * @param source the source of the reference
     */
    public GuardTraceHasLimits (IGuardTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IGuardedElementTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IGuardedElementTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.guard().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardedElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.guard().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardedElementTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.guard().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IGuardedElementTrace target) {
  
        return target.equals(this.target) && source.equals(target.guard().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IGuardedElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IGuardedElementTrace target) {
        this.target = null;
    }

}