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
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.Module;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ExecutionTraceHasModule reference. 
 */
public class ExecutionTraceHasModuleImpl extends Feature implements ExecutionTraceHasModule {
    
    /** The source(s) of the reference */
    protected ExecutionTrace source;
    
    /** The target(s) of the reference */
    protected Queue<Module> target =  new ConcurrentLinkedQueue<Module>();
    
    /**
     * Instantiates a new ExecutionTraceHasModule.
     *
     * @param source the source of the reference
     */
    public ExecutionTraceHasModuleImpl (ExecutionTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<Module> get() {
        return target;
    }
    
    @Override
    public boolean create(Module target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Module target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Module target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(Module target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(Module target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(Module target) {
        this.target.remove(target);
    }

}