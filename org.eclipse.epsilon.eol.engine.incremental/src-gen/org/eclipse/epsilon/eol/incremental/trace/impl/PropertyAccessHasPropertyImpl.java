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

import org.eclipse.epsilon.eol.incremental.trace.PropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.PropertyAccessHasProperty;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of property reference. 
 */
public class PropertyAccessHasPropertyImpl extends Feature implements PropertyAccessHasProperty {
    
    protected PropertyAccess source;
    protected Property target;
    
    /**
     * Instantiates a new PropertyAccessHasProperty.
     *
     * @param source the source of the reference
     */
    public PropertyAccessHasPropertyImpl (PropertyAccess source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Property get() {
        return target;
    }
    
    @Override
    public void set(Property target) {
        this.target = target;
    }
    
    @Override
    public void remove(Property target) {
        this.target = null;
    }
    
    @Override
    public boolean conflict(Property  target) {
        boolean result = false;
        result |= get() != null;
        result |= target.accessedBy().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(Property target) {
  
        return target.equals(this.target) & target.accessedBy().get().contains(source);
    }
    
    @Override
    public boolean create(Property target) {
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
    public boolean destroy(Property target) {
        if (!related(target)) {
            return false;
        }
        target.accessedBy().remove(source);
        remove(target);
        return true;
    }

}