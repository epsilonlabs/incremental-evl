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

import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccessHasExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IAccessHasExecutionTrace reference. 
 */
public class AccessHasExecutionTrace extends Feature implements IAccessHasExecutionTrace {
    
    /** The source(s) of the reference */
    protected IAccess source;
    
    /** The target(s) of the reference */
    protected IModuleElementTrace target;
    
    /**
     * Instantiates a new IAccessHasExecutionTrace.
     *
     * @param source the source of the reference
     */
    public AccessHasExecutionTrace (IAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModuleElementTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IModuleElementTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.accesses().set(source);
        if (related(target)) {
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
        target.accesses().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleElementTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.accesses().isUnique() && target.accesses().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(IModuleElementTrace target) {
  
        return target.equals(this.target) && target.accesses().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModuleElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModuleElementTrace target) {
        this.target = null;
    }

}