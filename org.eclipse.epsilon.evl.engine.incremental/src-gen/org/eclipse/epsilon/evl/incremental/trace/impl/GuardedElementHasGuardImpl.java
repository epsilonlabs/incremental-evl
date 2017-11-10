 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
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

import org.eclipse.epsilon.evl.incremental.trace.GuardedElement;
import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElementHasGuard;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of GuardedElementHasGuard reference. 
 */
public class GuardedElementHasGuardImpl extends Feature implements GuardedElementHasGuard {
    
    /** The source(s) of the reference */
    protected GuardedElement source;
    
    /** The target(s) of the reference */
    protected Guard target;
    
    /**
     * Instantiates a new GuardedElementHasGuard.
     *
     * @param source the source of the reference
     */
    public GuardedElementHasGuardImpl (GuardedElement source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Guard get() {
        return target;
    }
    
    @Override
    public boolean create(Guard target) {
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
    public boolean destroy(Guard target) {
        if (!related(target)) {
            return false;
        }
        target.limits().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Guard target) {
        boolean result = false;
        result |= get() != null;
        result |= target.limits().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Guard target) {
  
        return target.equals(this.target) & source.equals(target.limits().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(Guard target) {
        this.target = target;
    }
    
    @Override
    public void remove(Guard target) {
        this.target = null;
    }

}