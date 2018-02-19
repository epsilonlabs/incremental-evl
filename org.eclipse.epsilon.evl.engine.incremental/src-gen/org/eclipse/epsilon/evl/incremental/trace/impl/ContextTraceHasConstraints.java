 /*******************************************************************************
 * This file was automatically generated on: 2018-02-01.
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

import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import org.eclipse.epsilon.eol.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;


/**
 * Implementation of IContextTraceHasConstraints reference. 
 */
public class ContextTraceHasConstraints extends Feature implements IContextTraceHasConstraints {
    
    /** The source(s) of the reference */
    protected IContextTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IInvariantTrace> target =  new ConcurrentSetQueue<IInvariantTrace>();
    
    /**
     * Instantiates a new IContextTraceHasConstraints.
     *
     * @param source the source of the reference
     */
    public ContextTraceHasConstraints (IContextTrace source) {
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
        target.invariantContext().set(source);
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
        target.invariantContext().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IInvariantTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.invariantContext().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IInvariantTrace target) {
  
        return get().contains(target) && source.equals(target.invariantContext().get());
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