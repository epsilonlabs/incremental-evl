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

import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasModel;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasAccessedBy;

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

    /** The accessedBy relation */
    private final ModelTypeHasAccessedBy accessedBy;

    /**
     * Instantiates a new ModelType.
     */
    public ModelTypeImpl() {
        model = new ModelTypeHasModelImpl(this);
        accessedBy = new ModelTypeHasAccessedByImpl(this);
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
    @Override
    public ModelTypeHasAccessedBy accessedBy() {
        return accessedBy;
    }
 

}