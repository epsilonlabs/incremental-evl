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
import org.eclipse.epsilon.eol.incremental.trace.PropertyHasElement;
import org.eclipse.epsilon.eol.incremental.trace.PropertyHasAccessedBy;

/**
 * Implementation of Property. 
 */
public class PropertyImpl implements Property {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The element relation */
    private final PropertyHasElement element;

    /** The accessedBy relation */
    private final PropertyHasAccessedBy accessedBy;

    /**
     * Instantiates a new Property.
     */
    public PropertyImpl() {
        element = new PropertyHasElementImpl(this);
        accessedBy = new PropertyHasAccessedByImpl(this);
    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(Object value) {
        this.id = value;
    }   
     
    @Override
    public String getName() {
        return name;
    }
    
    
    @Override
    public void setName(String value) {
        this.name = value;
    }   
     
    @Override
    public PropertyHasElement element() {
        return element;
    }
    @Override
    public PropertyHasAccessedBy accessedBy() {
        return accessedBy;
    }
 

}