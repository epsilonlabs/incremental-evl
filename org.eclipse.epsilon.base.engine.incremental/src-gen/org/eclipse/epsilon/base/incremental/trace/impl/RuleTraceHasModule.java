 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTraceHasModule;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IRuleTraceHasModule reference. 
 */
public class RuleTraceHasModule extends Feature implements IRuleTraceHasModule {
    
    /** The source(s) of the reference */
    protected IRuleTrace source;
    
    /** The target(s) of the reference */
    protected IModuleTrace target;
    
    /**
     * Instantiates a new IRuleTraceHasModule.
     *
     * @param source the source of the reference
     */
    public RuleTraceHasModule (IRuleTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModuleTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IModuleTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.ruleTraces().set(source);
        if (related(target)) {
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
        target.ruleTraces().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.ruleTraces().isUnique() && target.ruleTraces().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(IModuleTrace target) {
  
        return target.equals(this.target) && target.ruleTraces().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModuleTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModuleTrace target) {
        this.target = null;
    }

}