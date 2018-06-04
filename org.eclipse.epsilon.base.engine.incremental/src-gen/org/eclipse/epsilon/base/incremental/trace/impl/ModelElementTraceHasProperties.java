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

import java.util.Queue;
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModelElementTraceHasProperties reference. 
 */
public class ModelElementTraceHasProperties extends Feature implements IModelElementTraceHasProperties {
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IPropertyTrace> target =  new ConcurrentSetQueue<IPropertyTrace>();
    
    /**
     * Instantiates a new IModelElementTraceHasProperties.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasProperties (IModelElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Queue<IPropertyTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IPropertyTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IPropertyTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IPropertyTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IPropertyTrace target) {
  
        return get().contains(target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IPropertyTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IPropertyTrace target) {
        this.target.remove(target);
    }

}