 /*******************************************************************************
 * This file was automatically generated on: 2018-06-04.
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

import org.eclipse.epsilon.base.incremental.trace.IInContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IInContextModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInContextModuleElementTraceHasParentTrace reference. 
 */
public class InContextModuleElementTraceHasParentTrace extends Feature implements IInContextModuleElementTraceHasParentTrace {
    
    /** The source(s) of the reference */
    protected IInContextModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected IContextModuleElementTrace target;
    
    /**
     * Instantiates a new IInContextModuleElementTraceHasParentTrace.
     *
     * @param source the source of the reference
     */
    public InContextModuleElementTraceHasParentTrace (IInContextModuleElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IContextModuleElementTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IContextModuleElementTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IContextModuleElementTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IContextModuleElementTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IContextModuleElementTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IContextModuleElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IContextModuleElementTrace target) {
        this.target = null;
    }

}