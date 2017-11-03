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
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of types reference. 
 */
public class ModelHasTypesImpl extends Feature implements ModelHasTypes {
    
    protected Model source;
    protected Queue<ModelType> target =  new ConcurrentLinkedQueue<ModelType>();
    
    /**
     * Instantiates a new ModelHasTypes.
     *
     * @param source the source of the reference
     */
    public ModelHasTypesImpl (Model source) {
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
        result &= target.model().get() != null;
        return result;
    }
    
    @Override
    public boolean related(ModelType target) {
  
        return get().contains(target) & source.equals(target.model().get());
    }
    
    @Override
    public boolean create(ModelType target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.model().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModelType target) {
        if (!related(target)) {
            return false;
        }
        target.model().remove(source);
        remove(target);
        return true;
    }

}