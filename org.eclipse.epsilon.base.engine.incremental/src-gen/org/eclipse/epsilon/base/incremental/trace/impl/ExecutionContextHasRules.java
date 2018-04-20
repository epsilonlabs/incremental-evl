 /*******************************************************************************
 * This file was automatically generated on: 2018-04-20.
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
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContextHasRules;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IExecutionContextHasRules reference. 
 */
public class ExecutionContextHasRules extends Feature implements IExecutionContextHasRules {
    
    /** The source(s) of the reference */
    protected IExecutionContext source;
    
    /** The target(s) of the reference */
    protected Queue<IRuleTrace> target =  new ConcurrentSetQueue<IRuleTrace>();
    
    /**
     * Instantiates a new IExecutionContextHasRules.
     *
     * @param source the source of the reference
     */
    public ExecutionContextHasRules (IExecutionContext source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IRuleTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IRuleTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.executionContext().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IRuleTrace target) {
        if (!related(target)) {
            return false;
        }
        target.executionContext().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IRuleTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.executionContext().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IRuleTrace target) {
  
        return get().contains(target) && source.equals(target.executionContext().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IRuleTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IRuleTrace target) {
        this.target.remove(target);
    }

}