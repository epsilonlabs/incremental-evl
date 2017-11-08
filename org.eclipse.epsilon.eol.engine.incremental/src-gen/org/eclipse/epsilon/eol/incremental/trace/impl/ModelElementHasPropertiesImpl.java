 /*******************************************************************************
 * This file was automatically generated on: 2017-11-08.
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
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ModelElementHasProperties reference. 
 */
public class ModelElementHasPropertiesImpl extends Feature implements ModelElementHasProperties {
    
    /** The source(s) of the reference */
    protected ModelElement source;
    
    /** The target(s) of the reference */
    protected Queue<Property> target =  new ConcurrentLinkedQueue<Property>();
    
    /**
     * Instantiates a new ModelElementHasProperties.
     *
     * @param source the source of the reference
     */
    public ModelElementHasPropertiesImpl (ModelElement source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<Property> get() {
        return target;
    }
    
    @Override
    public boolean create(Property target) {
        if (isUnique && related(target)) {
            return true;
        }
        if (conflict(target)) {
            return false;
        }
        target.element().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Property target) {
        if (!related(target)) {
            return false;
        }
        target.element().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Property target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.element().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Property target) {
  
        return get().contains(target) & source.equals(target.element().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(Property target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(Property target) {
        this.target.remove(target);
    }

}