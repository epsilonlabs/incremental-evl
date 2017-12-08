 /*******************************************************************************
 * This file was automatically generated on: 2017-12-08.
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
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasExecutions;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionHasExecutions reference. 
 */
public class ModuleExecutionHasExecutions extends Feature implements IModuleExecutionHasExecutions {
    
    /** The source(s) of the reference */
    protected IModuleExecution source;
    
    /** The target(s) of the reference */
    protected Queue<IExecutionTrace> target =  new ConcurrentLinkedQueue<IExecutionTrace>();
    
    /**
     * Instantiates a new IModuleExecutionHasExecutions.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionHasExecutions (IModuleExecution source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<IExecutionTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IExecutionTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IExecutionTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IExecutionTrace target) {
        this.target.remove(target);
    }

}