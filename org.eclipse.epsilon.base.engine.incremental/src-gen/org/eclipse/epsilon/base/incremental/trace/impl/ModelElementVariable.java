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
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region ModelElementVariableImports on begin **/
/** protected region ModelElementVariableImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelElementVariable. 
 */
public class ModelElementVariable implements IModelElementVariable {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The name.
	 */
    private String name;

    /**
     * The value.
     */
    private final IModelElementVariableHasValue value;

    /**
     * Instantiates a new ModelElementVariable. The ModelElementVariable is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementVariable(String name,
                                IModelElementTrace value,
                                IExecutionContext container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.name = name;
        if (!container.contextVariables().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.value = new ModelElementVariableHasValue(this);
        if (!this.value.create(value)) {
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
    public String getName() {
        return name;
    }
    
    @Override
    public IModelElementVariableHasValue value() {
        return value;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", getName());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IModelElementVariable other) {
        if (other == null) {
            return false;
        }
        String name = getName();
        String otherName = other.getName();
        if (name == null) {
            if (otherName != null)
                return false;
        } else if (!name.equals(otherName)) {
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
        if (!(obj instanceof ModelElementVariable))
            return false;
        ModelElementVariable other = (ModelElementVariable) obj;
        if (!sameIdentityAs(other))
            return false;
        if (value.get() == null) {
            if (other.value.get() != null)
                return false;
        }
        if (!value.get().equals(other.value.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((value.get() == null) ? 0 : value.get().hashCode());
        return result;
    }
}
