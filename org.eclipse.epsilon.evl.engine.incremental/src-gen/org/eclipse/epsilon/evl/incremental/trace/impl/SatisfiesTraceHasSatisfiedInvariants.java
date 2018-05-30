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

import java.util.Queue;
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasSatisfiedInvariants;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of ISatisfiesTraceHasSatisfiedInvariants reference. 
 */
public class SatisfiesTraceHasSatisfiedInvariants extends Feature implements ISatisfiesTraceHasSatisfiedInvariants {
    
    /** The source(s) of the reference */
    protected ISatisfiesTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IInvariantTrace> target =  new ConcurrentSetQueue<IInvariantTrace>();
    
    /**
     * Instantiates a new ISatisfiesTraceHasSatisfiedInvariants.
     *
     * @param source the source of the reference
     */
    public SatisfiesTraceHasSatisfiedInvariants (ISatisfiesTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IInvariantTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IInvariantTrace target) {
        if (conflict(target)) {
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
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IInvariantTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IInvariantTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IInvariantTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IInvariantTrace target) {
        this.target.remove(target);
    }

}