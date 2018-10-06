 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyAccess. 
 */
public class PropertyAccess implements IPropertyAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The value.
	 */
    private String value;

    /**
     * The from.
     */
    private final IAccessHasFrom from;

    /**
     * The property.
     */
    private final IPropertyAccessHasProperty property;

    /**
     * Instantiates a new PropertyAccess. The PropertyAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccess(IModuleElementTrace from,
                          IPropertyTrace property,
                          IExecutionContext container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.property = new PropertyAccessHasProperty(this);
        this.from = new AccessHasFrom(this);
        if (!this.property.create(property)) {
            throw new TraceModelDuplicateElement();
        }
        if (!this.from.create(from)) {
            throw new TraceModelDuplicateElement();
        }

    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
        this.id = value;
    }   
     
    @Override
    public String getValue() {
        return value;
    }
    
    
    @Override
    public void setValue(java.lang.String value) {
        this.value = value;
    }   
     
    @Override
    public IAccessHasFrom from() {
        return from;
    }

    @Override
    public IPropertyAccessHasProperty property() {
        return property;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
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
        if (property.get() == null) {
            if (other.property.get() != null)
                return false;
        }
        if (!property.get().equals(other.property.get())) {
            return false;
        }
        if (from.get() == null) {
            if (other.from.get() != null)
                return false;
        }
        if (!from.get().equals(other.from.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((property.get() == null) ? 0 : property.get().hashCode());
        result = prime * result + ((from.get() == null) ? 0 : from.get().hashCode());
        return result;
    }
}
