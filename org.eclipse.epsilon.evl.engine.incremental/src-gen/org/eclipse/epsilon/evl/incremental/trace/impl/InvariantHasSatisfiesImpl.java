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

import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasSatisfies;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of InvariantHasSatisfies reference. 
 */
public class InvariantHasSatisfiesImpl extends Feature implements InvariantHasSatisfies {
    
    /** The source(s) of the reference */
    protected Invariant source;
    
    /** The target(s) of the reference */
    protected Satisfies target;
    
    /**
     * Instantiates a new InvariantHasSatisfies.
     *
     * @param source the source of the reference
     */
    public InvariantHasSatisfiesImpl (Invariant source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Satisfies get() {
        return target;
    }
    
    @Override
    public boolean create(Satisfies target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Satisfies target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Satisfies target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(Satisfies target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(Satisfies target) {
        this.target = target;
    }
    
    @Override
    public void remove(Satisfies target) {
        this.target = null;
    }

}