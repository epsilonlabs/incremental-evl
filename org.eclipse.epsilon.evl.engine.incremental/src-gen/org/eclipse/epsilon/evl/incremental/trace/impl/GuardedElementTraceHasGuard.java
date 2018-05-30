 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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

import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IGuardedElementTraceHasGuard reference. 
 */
public class GuardedElementTraceHasGuard extends Feature implements IGuardedElementTraceHasGuard {
    
    /** The source(s) of the reference */
    protected IGuardedElementTrace source;
    
    /** The target(s) of the reference */
    protected IGuardTrace target;
    
    /**
     * Instantiates a new IGuardedElementTraceHasGuard.
     *
     * @param source the source of the reference
     */
    public GuardedElementTraceHasGuard (IGuardedElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IGuardTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IGuardTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.limits().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardTrace target) {
        if (!related(target)) {
            return false;
        }
        target.limits().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.limits().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IGuardTrace target) {
  
        return target.equals(this.target) && source.equals(target.limits().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IGuardTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IGuardTrace target) {
        this.target = null;
    }

}