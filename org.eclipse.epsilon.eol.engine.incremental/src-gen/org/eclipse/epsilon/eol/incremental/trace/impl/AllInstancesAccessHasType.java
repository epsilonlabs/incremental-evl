 /*******************************************************************************
 * This file was automatically generated on: 2017-11-21.
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

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IAllInstancesAccessHasType reference. 
 */
public class AllInstancesAccessHasType extends Feature implements IAllInstancesAccessHasType {
    
    /** The source(s) of the reference */
    protected IAllInstancesAccess source;
    
    /** The target(s) of the reference */
    protected IModelTypeTrace target;
    
    /**
     * Instantiates a new IAllInstancesAccessHasType.
     *
     * @param source the source of the reference
     */
    public AllInstancesAccessHasType (IAllInstancesAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelTypeTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IModelTypeTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTypeTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTypeTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModelTypeTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModelTypeTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModelTypeTrace target) {
        this.target = null;
    }

}