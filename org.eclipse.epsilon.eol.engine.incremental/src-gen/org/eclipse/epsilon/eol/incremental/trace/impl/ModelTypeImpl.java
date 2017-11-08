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

import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasModel;


/**
 * Implementation of ModelType. 
 */
public class ModelTypeImpl implements ModelType {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The model relation */
    private final ModelTypeHasModel model;

    /**
     * Instantiates a new ModelType. The ModelType is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTypeImpl(String name,
                         Model container) {
        this.name = name;
        model = new ModelTypeHasModelImpl(this);
        model.create(container);
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
    public ModelTypeHasModel model() {
        return model;
    }
 

}