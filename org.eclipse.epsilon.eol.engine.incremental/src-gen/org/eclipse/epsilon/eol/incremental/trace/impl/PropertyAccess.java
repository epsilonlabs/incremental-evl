 /*******************************************************************************
 * This file was automatically generated on: 2017-12-11.
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

import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IAccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccessHasProperty;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccessHasProperty;

/**
 * Implementation of IPropertyAccess. 
 */
public class PropertyAccess implements IPropertyAccess {

    /** The id */
    private Object id;

    /** The value */
    private String value;

    /** The execution relation */
    private final IAccessHasExecution execution;

    /** The property relation */
    private final IPropertyAccessHasProperty property;

    /**
     * Instantiates a new PropertyAccess. The PropertyAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccess(IPropertyTrace property, IExecutionTrace container) throws TraceModelDuplicateRelation {
        this.execution = new AccessHasExecution(this);
        this.property = new PropertyAccessHasProperty(this);
        if (!this.property.create(property)) {
            throw new TraceModelDuplicateRelation();
        }
        if (!container.accesses().create(this)) {
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
    public String getValue() {
        return value;
    }
    
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }   
     
    @Override
    public IAccessHasExecution execution() {
        return execution;
    }

    @Override
    public IPropertyAccessHasProperty property() {
        return property;
    }

    @Override
    public boolean sameIdentityAs(final IPropertyAccess other) {
        if (other == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PropertyAccess))
            return false;
        PropertyAccess other = (PropertyAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        // Will use execution for equals
        if (execution.get() == null) {
            if (other.execution.get() != null)
                return false;
        }
        else if (!execution.get().equals(other.execution.get())) {
            return false;
        }
        // Will use property for equals
        if (property.get() == null) {
            if (other.property.get() != null)
                return false;
        }
        else if (!property.get().equals(other.property.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((execution.get() == null) ? 0 : execution.get().hashCode());
        result = prime * result + ((property.get() == null) ? 0 : property.get().hashCode());
        return result;
    }

}
