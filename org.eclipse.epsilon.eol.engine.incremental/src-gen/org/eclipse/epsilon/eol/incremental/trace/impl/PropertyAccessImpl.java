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

import org.eclipse.epsilon.eol.incremental.trace.Execution;
import org.eclipse.epsilon.eol.incremental.trace.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.AccessHasExecutionImpl;

/**
 * Implementation of PropertyAccess. 
 */
public class PropertyAccessImpl implements PropertyAccess {

    /** The id */
    private Object id;

    /** The value */
    private String value;

    /** The execution relation */
    private final AccessHasExecution execution;

    /** The property relation */
    private final PropertyAccessHasProperty property;

    /**
     * Instantiates a new PropertyAccess. The PropertyAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessImpl(Execution container) {
        execution = new AccessHasExecutionImpl(this);
        property = new PropertyAccessHasPropertyImpl(this);
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
    public String getValue() {
        return value;
    }
    
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }   
     
    @Override
    public AccessHasExecution execution() {
        return execution;
    }
    @Override
    public PropertyAccessHasProperty property() {
        return property;
    }
 

}