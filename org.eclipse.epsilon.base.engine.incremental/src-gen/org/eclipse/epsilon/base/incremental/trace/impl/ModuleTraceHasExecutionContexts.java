 /*******************************************************************************
 * This file was automatically generated on: 2018-04-25.
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
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleTraceHasExecutionContexts;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleTraceHasExecutionContexts reference. 
 */
public class ModuleTraceHasExecutionContexts extends Feature implements IModuleTraceHasExecutionContexts {
    
    /** The source(s) of the reference */
    protected IModuleTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IExecutionContext> target =  new ConcurrentSetQueue<IExecutionContext>();
    
    /**
     * Instantiates a new IModuleTraceHasExecutionContexts.
     *
     * @param source the source of the reference
     */
    public ModuleTraceHasExecutionContexts (IModuleTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IExecutionContext> get() {
        return target;
    }
    
    @Override
    public boolean create(IExecutionContext target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionContext target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionContext target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IExecutionContext target) {
        this.target.remove(target);
    }

}