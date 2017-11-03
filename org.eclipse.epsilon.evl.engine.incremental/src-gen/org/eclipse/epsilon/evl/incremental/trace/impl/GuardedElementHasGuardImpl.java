 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
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
 * Implementation of guard reference. 
 */
public class GuardedElementHasGuardImpl extends Feature implements GuardedElementHasGuard {
    
    protected GuardedElement source;
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
    
    @Override
    public Guard get() {
        return target;
    }
    
    @Override
    public void set(Guard target) {
        this.target = target;
    }
    
    @Override
    public void remove(Guard target) {
        this.target = null;
    }
    
    @Override
    public boolean conflict(Guard  target) {
        boolean result = false;
        result |= get() != null;
        result &= target.guards().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Guard target) {
  
        return target.equals(this.target) & source.equals(target.guards().get());
    }
    
    @Override
    public boolean create(Guard target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.guards().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Guard target) {
        if (!related(target)) {
            return false;
        }
        target.guards().remove(source);
        remove(target);
        return true;
    }

}