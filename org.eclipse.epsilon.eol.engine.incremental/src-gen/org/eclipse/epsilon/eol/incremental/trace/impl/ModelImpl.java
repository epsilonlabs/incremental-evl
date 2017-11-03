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

import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasElements;

import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasTypes;


/**
 * Implementation of Model. 
 */
public class ModelImpl implements Model {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The elements relation */
    private final ModelHasElements elements;

    /** The types relation */
    private final ModelHasTypes types;

    /**
     * Instantiates a new Model. The Model is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelImpl(String name) {
        elements = new ModelHasElementsImpl(this);
        types = new ModelHasTypesImpl(this);
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
    public ModelHasElements elements() {
        return elements;
    }
    @Override
    public ModelHasTypes types() {
        return types;
    }
 

}