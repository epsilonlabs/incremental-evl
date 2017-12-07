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

import org.eclipse.epsilon.eol.incremental.trace.IAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IAccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IAccessHasExecution reference. 
 */
public class AccessHasExecution extends Feature implements IAccessHasExecution {
    
    /** The source(s) of the reference */
    protected IAccess source;
    
    /** The target(s) of the reference */
    protected IExecutionTrace target;
    
    /**
     * Instantiates a new IAccessHasExecution.
     *
     * @param source the source of the reference
     */
    public AccessHasExecution (IAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IExecutionTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IExecutionTrace target) {
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
    public boolean destroy(IExecutionTrace target) {
        if (!related(target)) {
            return false;
        }
        target.accesses().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionTrace target) {
        boolean result = false;
        result |= get() != null;
        result |= target.accesses().isUnique() && target.accesses().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(IExecutionTrace target) {
  
        return target.equals(this.target) && target.accesses().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IExecutionTrace target) {
        this.target = null;
    }

}