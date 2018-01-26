 /*******************************************************************************
 * This file was automatically generated on: 2018-01-26.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasModel;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTraceHasModel;

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

    /**
     * Instantiates a new ModelElementTrace. The ModelElementTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTrace(String uri, IModelTrace model) throws TraceModelDuplicateRelation {
        this.uri = uri;
        this.model = new ModelElementTraceHasModel(this);
        if (!this.model.create(model)) {
            throw new TraceModelDuplicateRelation();
        }
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
    public IModelElementTraceHasModel model() {
        return model;
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
        // Will use model for equals
        if (model.get() == null) {
            if (other.model.get() != null)
                return false;
        }
        else if (!model.get().equals(other.model.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        result = prime * result + ((model.get() == null) ? 0 : model.get().hashCode());
        return result;
    }

}
