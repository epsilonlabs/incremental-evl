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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasModule;
import org.eclipse.epsilon.incremental.trace.impl.Feature;


/**
 * Implementation of IContextTraceHasModule reference. 
 */
public class ContextTraceHasModule extends Feature implements IContextTraceHasModule {
    
    /** The source(s) of the reference */
    protected IContextTrace source;
    
    /** The target(s) of the reference */
    protected IEvlModuleTrace target;
    
    /**
     * Instantiates a new IContextTraceHasModule.
     *
     * @param source the source of the reference
     */
    public ContextTraceHasModule (IContextTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IEvlModuleTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IEvlModuleTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IEvlModuleTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IEvlModuleTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IEvlModuleTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IEvlModuleTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IEvlModuleTrace target) {
        this.target = null;
    }

}