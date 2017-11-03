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

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.ContextHasConstraints;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of constraints reference. 
 */
public class ContextHasConstraintsImpl extends Feature implements ContextHasConstraints {
    
    protected Context source;
    protected Queue<Invariant> target =  new ConcurrentLinkedQueue<Invariant>();
    
    /**
     * Instantiates a new ContextHasConstraints.
     *
     * @param source the source of the reference
     */
    public ContextHasConstraintsImpl (Context source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Queue<Invariant> get() {
        return target;
    }
    
    @Override
    public void set(Invariant target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(Invariant target) {
        this.target.remove(target);
    }
    
    @Override
    public boolean conflict(Invariant  target) {
        boolean result = false;
        result |= get().contains(target);
        return result;
    }
    
    @Override
    public boolean related(Invariant target) {
  
        return get().contains(target) ;
    }
    
    @Override
    public boolean create(Invariant target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
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

}