 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
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

import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasInvariant;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of ISatisfiesTraceHasInvariant reference. 
 */
public class SatisfiesTraceHasInvariant extends Feature implements ISatisfiesTraceHasInvariant {
    
    /** The source(s) of the reference */
    protected ISatisfiesTrace source;
    
    /** The target(s) of the reference */
    protected IInvariantTrace target;
    
    /**
     * Instantiates a new ISatisfiesTraceHasInvariant.
     *
     * @param source the source of the reference
     */
    public SatisfiesTraceHasInvariant (ISatisfiesTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IInvariantTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IInvariantTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.satisfies().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IInvariantTrace target) {
        if (!related(target)) {
            return false;
        }
        target.satisfies().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IInvariantTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.satisfies().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IInvariantTrace target) {
  
        return target.equals(this.target) && source.equals(target.satisfies().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IInvariantTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IInvariantTrace target) {
        this.target = null;
    }

}