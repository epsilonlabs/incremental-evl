 /*******************************************************************************
 * This file was automatically generated on: 2018-04-13.
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

import org.eclipse.epsilon.base.incremental.trace.IExecutionAccess;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionAccessHasAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IExecutionAccessHasAccess reference. 
 */
public class ExecutionAccessHasAccess extends Feature implements IExecutionAccessHasAccess {
    
    /** The source(s) of the reference */
    protected IExecutionAccess source;
    
    /** The target(s) of the reference */
    protected IAccess target;
    
    /**
     * Instantiates a new IExecutionAccessHasAccess.
     *
     * @param source the source of the reference
     */
    public ExecutionAccessHasAccess (IExecutionAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IAccess get() {
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
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IAccess target) {
        this.target = target;
    }
    
    @Override
    public void remove(IAccess target) {
        this.target = null;
    }

}