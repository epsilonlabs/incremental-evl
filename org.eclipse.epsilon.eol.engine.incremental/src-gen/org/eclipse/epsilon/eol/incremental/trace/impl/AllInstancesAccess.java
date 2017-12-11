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

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IAccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccessHasType;

/**
 * Implementation of IAllInstancesAccess. 
 */
public class AllInstancesAccess implements IAllInstancesAccess {

    /** The id */
    private Object id;

    /** The ofKind */
    private boolean ofKind;

    /** The execution relation */
    private final IAccessHasExecution execution;

    /** The type relation */
    private final IAllInstancesAccessHasType type;

    /**
     * Instantiates a new AllInstancesAccess. The AllInstancesAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccess(IModelTypeTrace type, IExecutionTrace container) throws TraceModelDuplicateRelation {
        this.execution = new AccessHasExecution(this);
        this.type = new AllInstancesAccessHasType(this);
        if (!this.type.create(type)) {
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
    public boolean getOfKind() {
        return ofKind;
    }
    
    
    @Override
    public void setOfKind(boolean value) {
        this.ofKind = value;
    }   
     
    @Override
    public IAccessHasExecution execution() {
        return execution;
    }

    @Override
    public IAllInstancesAccessHasType type() {
        return type;
    }

    @Override
    public boolean sameIdentityAs(final IAllInstancesAccess other) {
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
        if (!(obj instanceof AllInstancesAccess))
            return false;
        AllInstancesAccess other = (AllInstancesAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        // Will use type for equals
        if (type.get() == null) {
            if (other.type.get() != null)
                return false;
        }
        else if (!type.get().equals(other.type.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type.get() == null) ? 0 : type.get().hashCode());
        return result;
    }

}
