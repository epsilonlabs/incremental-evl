 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
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

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of type reference. 
 */
public class AllInstancesAccessHasTypeImpl extends Feature implements AllInstancesAccessHasType {
    
    protected AllInstancesAccess source;
    protected Queue<ModelType> target =  new ConcurrentLinkedQueue<ModelType>();
    
    /**
     * Instantiates a new AllInstancesAccessHasType.
     *
     * @param source the source of the reference
     */
    public AllInstancesAccessHasTypeImpl (AllInstancesAccess source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Queue<ModelType> get() {
        return target;
    }
    
    @Override
    public void set(ModelType target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ModelType target) {
        this.target.remove(target);
    }
    
    @Override
    public boolean conflict(ModelType  target) {
        boolean result = false;
        result |= get().contains(target);
        result |= target.accessedBy().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(ModelType target) {
  
        return get().contains(target) & target.accessedBy().get().contains(source);
    }
    
    @Override
    public boolean create(ModelType target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.accessedBy().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModelType target) {
        if (!related(target)) {
            return false;
        }
        target.accessedBy().remove(source);
        remove(target);
        return true;
    }

}