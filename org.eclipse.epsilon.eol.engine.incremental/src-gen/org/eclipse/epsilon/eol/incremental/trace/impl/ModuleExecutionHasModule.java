 /*******************************************************************************
 * This file was automatically generated on: 2017-12-11.
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

import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionHasModule reference. 
 */
public class ModuleExecutionHasModule extends Feature implements IModuleExecutionHasModule {
    
    /** The source(s) of the reference */
    protected IModuleExecution source;
    
    /** The target(s) of the reference */
    protected IModuleTrace target;
    
    /**
     * Instantiates a new IModuleExecutionHasModule.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionHasModule (IModuleExecution source) {
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
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModuleTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModuleTrace target) {
  
        return target.equals(this.target) ;
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