 /*******************************************************************************
 * This file was automatically generated on: 2018-04-18.
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

import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IRuleTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IRuleTraceHasExecutionContext reference. 
 */
public class RuleTraceHasExecutionContext extends Feature implements IRuleTraceHasExecutionContext {
    
    /** The source(s) of the reference */
    protected IRuleTrace source;
    
    /** The target(s) of the reference */
    protected IExecutionContext target;
    
    /**
     * Instantiates a new IRuleTraceHasExecutionContext.
     *
     * @param source the source of the reference
     */
    public RuleTraceHasExecutionContext (IRuleTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IExecutionContext get() {
        return target;
    }
    
    @Override
    public boolean create(IExecutionContext target) {
        if (conflict(target)) {
            return false;
        }
        target.rules().set(source);
        if (related(target)) {
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
        target.rules().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        result |= get() != null;
        result |= target.rules().isUnique() && target.rules().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
  
        return target.equals(this.target) && target.rules().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionContext target) {
        this.target = target;
    }
    
    @Override
    public void remove(IExecutionContext target) {
        this.target = null;
    }

}