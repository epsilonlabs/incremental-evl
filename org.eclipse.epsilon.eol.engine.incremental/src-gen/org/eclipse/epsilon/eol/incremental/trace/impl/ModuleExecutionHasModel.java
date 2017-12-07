 /*******************************************************************************
 * This file was automatically generated on: 2017-12-07.
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
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionHasModel reference. 
 */
public class ModuleExecutionHasModel extends Feature implements IModuleExecutionHasModel {
    
    /** The source(s) of the reference */
    protected IModuleExecution source;
    
    /** The target(s) of the reference */
    protected Queue<IModelTrace> target =  new ConcurrentLinkedQueue<IModelTrace>();
    
    /**
     * Instantiates a new IModuleExecutionHasModel.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionHasModel (IModuleExecution source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<IModelTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IModelTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModelTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModelTrace target) {
        this.target.remove(target);
    }

}