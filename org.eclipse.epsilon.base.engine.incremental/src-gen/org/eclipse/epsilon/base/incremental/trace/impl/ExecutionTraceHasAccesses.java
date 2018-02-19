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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IExecutionTraceHasAccesses reference. 
 */
public class ExecutionTraceHasAccesses extends Feature implements IExecutionTraceHasAccesses {
    
    /** The source(s) of the reference */
    protected IExecutionTrace source;
    
    /** The target(s) of the reference */
    protected Set<IAccess> target =  ConcurrentHashMap.newKeySet();
    
    /**
     * Instantiates a new IExecutionTraceHasAccesses.
     *
     * @param source the source of the reference
     */
    public ExecutionTraceHasAccesses (IExecutionTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Set<IAccess> get() {
        return target;
    }
    
    @Override
    public boolean create(IAccess target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IAccess target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IAccess target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IAccess target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IAccess target) {
        this.target.remove(target);
    }

}