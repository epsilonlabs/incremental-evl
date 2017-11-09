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

import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTypeHasModelImpl;

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
    public ModelTypeImpl(String name, Model container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.model = new ModelTypeHasModelImpl(this);
        if (!container.types().create(this)) {
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
    public boolean sameIdentityAs(final ModelType other) {
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
        if (!(obj instanceof ModelTypeImpl))
            return false;
        ModelTypeImpl other = (ModelTypeImpl) obj;
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
