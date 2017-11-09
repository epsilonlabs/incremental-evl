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
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.SatisfiesHasInvariants;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of SatisfiesHasInvariants reference. 
 */
public class SatisfiesHasInvariantsImpl extends Feature implements SatisfiesHasInvariants {
    
    /** The source(s) of the reference */
    protected Satisfies source;
    
    /** The target(s) of the reference */
    protected Queue<Invariant> target =  new ConcurrentLinkedQueue<Invariant>();
    
    /**
     * Instantiates a new SatisfiesHasInvariants.
     *
     * @param source the source of the reference
     */
    public SatisfiesHasInvariantsImpl (Satisfies source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<Invariant> get() {
        return target;
    }
    
    @Override
    public boolean create(Invariant target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Invariant target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Invariant target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(Invariant target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(Invariant target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(Invariant target) {
        this.target.remove(target);
    }

}