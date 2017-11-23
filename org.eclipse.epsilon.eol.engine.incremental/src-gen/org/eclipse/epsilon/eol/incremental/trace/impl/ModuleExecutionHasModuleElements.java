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
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModuleElements;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionHasModuleElements reference. 
 */
public class ModuleExecutionHasModuleElements extends Feature implements IModuleExecutionHasModuleElements {
    
    /** The source(s) of the reference */
    protected IModuleExecution source;
    
    /** The target(s) of the reference */
    protected Queue<IModuleElementTrace> target =  new ConcurrentLinkedQueue<IModuleElementTrace>();
    
    /**
     * Instantiates a new IModuleExecutionHasModuleElements.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionHasModuleElements (IModuleExecution source) {
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