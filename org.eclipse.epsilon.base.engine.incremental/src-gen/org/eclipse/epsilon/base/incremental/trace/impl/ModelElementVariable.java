 /*******************************************************************************
 * This file was automatically generated on: 2018-05-04.
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

import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariableHasValue;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementVariableHasValue;

/**
 * Implementation of IModelElementVariable. 
 */
public class ModelElementVariable implements IModelElementVariable {

    /** The name */
    private String name;

    /** The value relation */
    private final IModelElementVariableHasValue value;

    /**
     * Instantiates a new ModelElementVariable. The ModelElementVariable is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementVariable(String name, IModelElementTrace value) throws TraceModelDuplicateRelation {
        this.name = name;
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@135673b2 (name: value) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.value = new ModelElementVariableHasValue(this);
        if (!this.value.create(value)) {
            throw new TraceModelDuplicateRelation();
        }

    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public IModelElementVariableHasValue value() {
        return value;
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
