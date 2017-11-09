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

import org.eclipse.epsilon.eol.incremental.trace.PropertyAccess;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.Execution;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.PropertyAccessHasProperty;
import org.eclipse.epsilon.eol.incremental.trace.impl.AccessHasExecutionImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccessHasPropertyImpl;

/**
 * Implementation of PropertyAccess. 
 */
public class PropertyAccessImpl implements PropertyAccess {

    /** The id */
    private Object id;

    /** The value */
    private String value;

    /** The execution relation */
    private final AccessHasExecution execution;

    /** The property relation */
    private final PropertyAccessHasProperty property;

    /**
     * Instantiates a new PropertyAccess. The PropertyAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccessImpl(Property property, Execution container) throws TraceModelDuplicateRelation {
        this.execution = new AccessHasExecutionImpl(this);
        this.property = new PropertyAccessHasPropertyImpl(this);
        this.property.create(property);
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
    public AccessHasExecution execution() {
        return execution;
    }

    @Override
    public PropertyAccessHasProperty property() {
        return property;
    }

    @Override
    public boolean sameIdentityAs(final PropertyAccess other) {
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
        if (!(obj instanceof PropertyAccessImpl))
            return false;
        PropertyAccessImpl other = (PropertyAccessImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        if (execution.get() == null) {
            if (other.execution.get() != null)
                return false;
        } else if (!execution.get().equals(other.execution.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((execution.get() == null) ? 0 : execution.get().hashCode());
        return result;
    }

}
