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

import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasModel;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasProperties;

/**
 * Implementation of ModelElement. 
 */
public class ModelElementImpl implements ModelElement {

    /** The id */
    private Object id;

    /** The uri */
    private String uri;

    /** The model relation */
    private final ModelElementHasModel model;

    /** The properties relation */
    private final ModelElementHasProperties properties;

    /**
     * Instantiates a new ModelElement.
     */
    public ModelElementImpl() {
        model = new ModelElementHasModelImpl(this);
        properties = new ModelElementHasPropertiesImpl(this);
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
    public String getUri() {
        return uri;
    }
    
    
    @Override
    public void setUri(String value) {
        this.uri = value;
    }   
     
    @Override
    public ModelElementHasModel model() {
        return model;
    }
    @Override
    public ModelElementHasProperties properties() {
        return properties;
    }
 

}