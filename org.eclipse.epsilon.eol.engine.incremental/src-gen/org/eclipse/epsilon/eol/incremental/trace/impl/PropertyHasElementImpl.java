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

import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.PropertyHasElement;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of element reference. 
 */
public class PropertyHasElementImpl extends Feature implements PropertyHasElement {
    
    protected Property source;
    protected ModelElement target;
    
    /**
     * Instantiates a new PropertyHasElement.
     *
     * @param source the source of the reference
     */
    public PropertyHasElementImpl (Property source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public ModelElement get() {
        return target;
    }
    
    @Override
    public void set(ModelElement target) {
        this.target = target;
    }
    
    @Override
    public void remove(ModelElement target) {
        this.target = null;
    }
    
    @Override
    public boolean conflict(ModelElement  target) {
        boolean result = false;
        result |= get() != null;
        result |= target.properties().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(ModelElement target) {
  
        return target.equals(this.target) & target.properties().get().contains(source);
    }
    
    @Override
    public boolean create(ModelElement target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.properties().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModelElement target) {
        if (!related(target)) {
            return false;
        }
        target.properties().remove(source);
        remove(target);
        return true;
    }

}