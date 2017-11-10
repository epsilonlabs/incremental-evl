 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
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

import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of AllInstancesAccessHasType reference. 
 */
public class AllInstancesAccessHasTypeImpl extends Feature implements AllInstancesAccessHasType {
    
    /** The source(s) of the reference */
    protected AllInstancesAccess source;
    
    /** The target(s) of the reference */
    protected ModelType target;
    
    /**
     * Instantiates a new AllInstancesAccessHasType.
     *
     * @param source the source of the reference
     */
    public AllInstancesAccessHasTypeImpl (AllInstancesAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public ModelType get() {
        return target;
    }
    
    @Override
    public boolean create(ModelType target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModelType target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ModelType target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(ModelType target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(ModelType target) {
        this.target = target;
    }
    
    @Override
    public void remove(ModelType target) {
        this.target = null;
    }

}