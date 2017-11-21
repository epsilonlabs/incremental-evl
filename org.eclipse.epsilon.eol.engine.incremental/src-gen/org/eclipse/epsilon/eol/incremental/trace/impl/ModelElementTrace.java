 /*******************************************************************************
 * This file was automatically generated on: 2017-11-21.
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

import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTraceHasModel;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementTraceHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementTraceHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyTrace;

/**
 * Implementation of IModelElementTrace. 
 */
public class ModelElementTrace implements IModelElementTrace {

    /** The id */
    private Object id;

    /** The uri */
    private String uri;

    /** The model relation */
    private final IModelElementTraceHasModel model;

    /** The properties relation */
    private final IModelElementTraceHasProperties properties;

    /**
     * Instantiates a new ModelElementTrace. The ModelElementTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTrace(String uri, IModelTrace container) throws TraceModelDuplicateRelation {
        this.uri = uri;
        this.model = new ModelElementTraceHasModel(this);
        this.properties = new ModelElementTraceHasProperties(this);
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
    public IModelElementTraceHasModel model() {
        return model;
    }

    @Override
    public IModelElementTraceHasProperties properties() {
        return properties;
    }

    @Override
    public IPropertyTrace createPropertyTrace(String name) throws EolIncrementalExecutionException {
        IPropertyTrace propertyTrace = null;
        try {
            propertyTrace = new PropertyTrace(name, this);
            
            this.properties().create(propertyTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyTrace != null) {
    	        return propertyTrace;
    	    }
            try {
                propertyTrace = this.properties.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return propertyTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IModelElementTrace other) {
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
        if (!(obj instanceof ModelElementTrace))
            return false;
        ModelElementTrace other = (ModelElementTrace) obj;
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
