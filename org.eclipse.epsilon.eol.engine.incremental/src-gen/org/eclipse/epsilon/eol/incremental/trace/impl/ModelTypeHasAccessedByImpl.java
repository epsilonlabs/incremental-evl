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
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasAccessedBy;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of accessedBy reference. 
 */
public class ModelTypeHasAccessedByImpl extends Feature implements ModelTypeHasAccessedBy {
    
    protected ModelType source;
    protected Queue<AllInstancesAccess> target =  new ConcurrentLinkedQueue<AllInstancesAccess>();
    
    /**
     * Instantiates a new ModelTypeHasAccessedBy.
     *
     * @param source the source of the reference
     */
    public ModelTypeHasAccessedByImpl (ModelType source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Queue<AllInstancesAccess> get() {
        return target;
    }
    
    @Override
    public void set(AllInstancesAccess target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(AllInstancesAccess target) {
        this.target.remove(target);
    }
    
    @Override
    public boolean conflict(AllInstancesAccess  target) {
        boolean result = false;
        result |= get().contains(target);
        result |= target.type().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(AllInstancesAccess target) {
  
        return get().contains(target) & target.type().get().contains(source);
    }
    
    @Override
    public boolean create(AllInstancesAccess target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.type().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(AllInstancesAccess target) {
        if (!related(target)) {
            return false;
        }
        target.type().remove(source);
        remove(target);
        return true;
    }

}