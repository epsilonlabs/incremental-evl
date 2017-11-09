 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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
 * Implementation of ModelHasTypes reference. 
 */
public class ModelHasTypesImpl extends Feature implements ModelHasTypes {
    
    /** The source(s) of the reference */
    protected Model source;
    
    /** The target(s) of the reference */
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
    
    // PUBLIC API
        
    @Override
    public Queue<ModelType> get() {
        return target;
    }
    
    @Override
    public boolean create(ModelType target) {
        if (conflict(target)) {
            return false;
        }
        target.model().set(source);
        if (related(target)) {
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
        target.model().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ModelType target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.model().get() != null;
        return result;
    }
    
    @Override
    public boolean related(ModelType target) {
  
        return get().contains(target) & source.equals(target.model().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(ModelType target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ModelType target) {
        this.target.remove(target);
    }

}