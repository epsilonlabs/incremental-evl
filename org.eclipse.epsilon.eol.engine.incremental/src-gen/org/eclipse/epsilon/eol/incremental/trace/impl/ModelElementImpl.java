 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasModel;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementHasModelImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementHasPropertiesImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyImpl;

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
     * Instantiates a new ModelElement. The ModelElement is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementImpl(String uri, Model container) throws TraceModelDuplicateRelation {
        this.uri = uri;
        this.model = new ModelElementHasModelImpl(this);
        this.properties = new ModelElementHasPropertiesImpl(this);
        if (!container.elements().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
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

    @Override
    public Property createProperty(String name) throws EolIncrementalExecutionException {
            try {
                return new PropertyImpl(name, this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            Property property = null;
            
            try {
                property = this.properties.get().stream()
                    .filter(mt -> mt.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Property was "
                        + "duplicate but previous one was not found.");
            }
            return property;
    }      
                  
    @Override
    public boolean sameIdentityAs(final ModelElement other) {
        if (other == null) {
            return false;
        }
        if (getUri() == null) {
            if (other.getUri() != null)
                return false;
        } else if (!getUri().equals(other.getUri()))
            return false;
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ModelElementImpl))
            return false;
        ModelElementImpl other = (ModelElementImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        if (model.get() == null) {
            if (other.model.get() != null)
                return false;
        } else if (!model.get().equals(other.model.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((model.get() == null) ? 0 : model.get().hashCode());
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }

}
