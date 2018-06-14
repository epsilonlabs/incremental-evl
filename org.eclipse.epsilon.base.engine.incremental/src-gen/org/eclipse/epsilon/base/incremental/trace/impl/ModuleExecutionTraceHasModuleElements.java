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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.Queue;
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionTraceHasModuleElements reference. 
 */
public class ModuleExecutionTraceHasModuleElements extends Feature implements IModuleExecutionTraceHasModuleElements {
    
    /** The source(s) of the reference */
    protected IModuleExecutionTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IModuleElementTrace> target =  new ConcurrentSetQueue<IModuleElementTrace>();
    
    /**
     * Instantiates a new IModuleExecutionTraceHasModuleElements.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionTraceHasModuleElements (IModuleExecutionTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IModuleElementTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IModuleElementTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModuleElementTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleElementTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IModuleElementTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModuleElementTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModuleElementTrace target) {
        this.target.remove(target);
    }

}