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

import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElement;
import org.eclipse.epsilon.evl.incremental.trace.GuardHasLimits;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of GuardHasLimits reference. 
 */
public class GuardHasLimitsImpl extends Feature implements GuardHasLimits {
    
    /** The source(s) of the reference */
    protected Guard source;
    
    /** The target(s) of the reference */
    protected GuardedElement target;
    
    /**
     * Instantiates a new GuardHasLimits.
     *
     * @param source the source of the reference
     */
    public GuardHasLimitsImpl (Guard source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public GuardedElement get() {
        return target;
    }
    
    @Override
    public boolean create(GuardedElement target) {
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
    public boolean destroy(GuardedElement target) {
        if (!related(target)) {
            return false;
        }
        target.guard().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(GuardedElement target) {
        boolean result = false;
        result |= get() != null;
        result |= target.guard().get() != null;
        return result;
    }
    
    @Override
    public boolean related(GuardedElement target) {
  
        return target.equals(this.target) & source.equals(target.guard().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(GuardedElement target) {
        this.target = target;
    }
    
    @Override
    public void remove(GuardedElement target) {
        this.target = null;
    }

}