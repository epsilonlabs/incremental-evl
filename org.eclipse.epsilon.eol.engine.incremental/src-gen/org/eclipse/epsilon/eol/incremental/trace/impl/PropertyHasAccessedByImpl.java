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
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.PropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.PropertyHasAccessedBy;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of accessedBy reference. 
 */
public class PropertyHasAccessedByImpl extends Feature implements PropertyHasAccessedBy {
    
    protected Property source;
    protected Queue<PropertyAccess> target =  new ConcurrentLinkedQueue<PropertyAccess>();
    
    /**
     * Instantiates a new PropertyHasAccessedBy.
     *
     * @param source the source of the reference
     */
    public PropertyHasAccessedByImpl (Property source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Queue<PropertyAccess> get() {
        return target;
    }
    
    @Override
    public void set(PropertyAccess target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(PropertyAccess target) {
        this.target.remove(target);
    }
    
    @Override
    public boolean conflict(PropertyAccess  target) {
        boolean result = false;
        result |= get().contains(target);
        result &= target.property().get() != null;
        return result;
    }
    
    @Override
    public boolean related(PropertyAccess target) {
  
        return get().contains(target) & source.equals(target.property().get());
    }
    
    @Override
    public boolean create(PropertyAccess target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.property().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(PropertyAccess target) {
        if (!related(target)) {
            return false;
        }
        target.property().remove(source);
        remove(target);
        return true;
    }

}