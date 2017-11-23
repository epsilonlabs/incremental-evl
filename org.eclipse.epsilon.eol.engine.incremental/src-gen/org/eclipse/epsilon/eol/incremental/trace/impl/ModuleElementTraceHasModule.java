 /*******************************************************************************
 * This file was automatically generated on: 2017-11-23.
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
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleElementTraceHasModule reference. 
 */
public class ModuleElementTraceHasModule extends Feature implements IModuleElementTraceHasModule {
    
    /** The source(s) of the reference */
    protected IModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IModuleTrace> target =  new ConcurrentLinkedQueue<IModuleTrace>();
    
    /**
     * Instantiates a new IModuleElementTraceHasModule.
     *
     * @param source the source of the reference
     */
    public ModuleElementTraceHasModule (IModuleElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<IModuleTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IModuleTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModuleTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IModuleTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModuleTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModuleTrace target) {
        this.target.remove(target);
    }

}