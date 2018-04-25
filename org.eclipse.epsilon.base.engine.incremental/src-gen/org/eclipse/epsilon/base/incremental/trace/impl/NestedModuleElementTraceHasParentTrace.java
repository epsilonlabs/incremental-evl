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

import org.eclipse.epsilon.base.incremental.trace.INestedModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.INestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of INestedModuleElementTraceHasParentTrace reference. 
 */
public class NestedModuleElementTraceHasParentTrace extends Feature implements INestedModuleElementTraceHasParentTrace {
    
    /** The source(s) of the reference */
    protected INestedModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected IRuleTrace target;
    
    /**
     * Instantiates a new INestedModuleElementTraceHasParentTrace.
     *
     * @param source the source of the reference
     */
    public NestedModuleElementTraceHasParentTrace (INestedModuleElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IRuleTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IRuleTrace target) {
        if (conflict(target)) {
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
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IRuleTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IRuleTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IRuleTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IRuleTrace target) {
        this.target = null;
    }

}