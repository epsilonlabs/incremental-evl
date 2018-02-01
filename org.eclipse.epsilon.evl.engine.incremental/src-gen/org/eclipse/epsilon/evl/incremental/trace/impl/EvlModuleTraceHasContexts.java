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
import org.eclipse.epsilon.eol.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceHasContexts;
import org.eclipse.epsilon.incremental.trace.impl.Feature;


/**
 * Implementation of IEvlModuleTraceHasContexts reference. 
 */
public class EvlModuleTraceHasContexts extends Feature implements IEvlModuleTraceHasContexts {
    
    /** The source(s) of the reference */
    protected IEvlModuleTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IContextTrace> target =  new ConcurrentSetQueue<IContextTrace>();
    
    /**
     * Instantiates a new IEvlModuleTraceHasContexts.
     *
     * @param source the source of the reference
     */
    public EvlModuleTraceHasContexts (IEvlModuleTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IContextTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IContextTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IContextTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IContextTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IContextTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IContextTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IContextTrace target) {
        this.target.remove(target);
    }

}