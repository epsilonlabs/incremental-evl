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

import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTraceHasModel;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTraceHasModel;

/**
 * Implementation of IModelTypeTrace. 
 */
public class ModelTypeTrace implements IModelTypeTrace {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The model relation */
    private final IModelTypeTraceHasModel model;

    /**
     * Instantiates a new ModelTypeTrace. The ModelTypeTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTypeTrace(String name, IModelTrace model) throws TraceModelDuplicateRelation {
        this.name = name;
        this.model = new ModelTypeTraceHasModel(this);
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
    public String getName() {
        return name;
    }
    
    @Override
    public IModelTypeTraceHasModel model() {
        return model;
    }

    @Override
    public boolean sameIdentityAs(final IModelTypeTrace other) {
        if (other == null) {
            return false;
        }
        if (getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!getName().equals(other.getName()))
            return false;
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ModelTypeTrace))
            return false;
        ModelTypeTrace other = (ModelTypeTrace) obj;
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((model.get() == null) ? 0 : model.get().hashCode());
        return result;
    }
}
