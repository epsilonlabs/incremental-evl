 /*******************************************************************************
 * This file was automatically generated on: 2018-05-04.
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

import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTraceHasModel;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModelTypeTraceHasModel reference. 
 */
public class ModelTypeTraceHasModel extends Feature implements IModelTypeTraceHasModel {
    
    /** The source(s) of the reference */
    protected IModelTypeTrace source;
    
    /** The target(s) of the reference */
    protected IModelTrace target;
    
    /**
     * Instantiates a new IModelTypeTraceHasModel.
     *
     * @param source the source of the reference
     */
    public ModelTypeTraceHasModel (IModelTypeTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IModelTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModelTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModelTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModelTrace target) {
        this.target = null;
    }

}