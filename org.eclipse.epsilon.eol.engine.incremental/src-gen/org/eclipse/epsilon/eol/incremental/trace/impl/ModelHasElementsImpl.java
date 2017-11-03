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
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasElements;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of elements reference. 
 */
public class ModelHasElementsImpl extends Feature implements ModelHasElements {
    
    protected Model source;
    protected Queue<ModelElement> target =  new ConcurrentLinkedQueue<ModelElement>();
    
    /**
     * Instantiates a new ModelHasElements.
     *
     * @param source the source of the reference
     */
    public ModelHasElementsImpl (Model source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Queue<ModelElement> get() {
        return target;
    }
    
    @Override
    public void set(ModelElement target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ModelElement target) {
        this.target.remove(target);
    }
    
    @Override
    public boolean conflict(ModelElement  target) {
        boolean result = false;
        result |= get().contains(target);
        result |= target.model().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(ModelElement target) {
  
        return get().contains(target) & target.model().get().contains(source);
    }
    
    @Override
    public boolean create(ModelElement target) {
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
    public boolean destroy(ModelElement target) {
        if (!related(target)) {
            return false;
        }
        target.model().remove(source);
        remove(target);
        return true;
    }

}