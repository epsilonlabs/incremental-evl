 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
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

import org.eclipse.epsilon.eol.incremental.trace.Property;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.PropertyHasElement;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyHasElementImpl;

/**
 * Implementation of Property. 
 */
public class PropertyImpl implements Property {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The element relation */
    private final PropertyHasElement element;

    /**
     * Instantiates a new Property. The Property is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyImpl(String name, ModelElement container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.element = new PropertyHasElementImpl(this);
        if (!container.properties().create(this)) {
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
    public PropertyHasElement element() {
        return element;
    }

    @Override
    public boolean sameIdentityAs(final Property other) {
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
        if (!(obj instanceof PropertyImpl))
            return false;
        PropertyImpl other = (PropertyImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        if (element.get() == null) {
            if (other.element.get() != null)
                return false;
        } else if (!element.get().equals(other.element.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((element.get() == null) ? 0 : element.get().hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
