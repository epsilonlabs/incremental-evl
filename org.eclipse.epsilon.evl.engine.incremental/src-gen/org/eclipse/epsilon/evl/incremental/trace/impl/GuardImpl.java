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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Guard;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.PropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccessImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccessImpl;
import org.eclipse.epsilon.evl.incremental.trace.GuardHasLimits;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElement;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardHasLimitsImpl;

/**
 * Implementation of Guard. 
 */
public class GuardImpl implements Guard {

    /** The id */
    private Object id;

    /** The result */
    private boolean result;

    /** The accesses relation */
    private final ExecutionHasAccesses accesses;

    /** The limits relation */
    private final GuardHasLimits limits;

    /**
     * Instantiates a new Guard. The Guard is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardImpl(GuardedElement container) throws TraceModelDuplicateRelation {
        this.accesses = new ExecutionHasAccessesImpl(this);
        this.limits = new GuardHasLimitsImpl(this);
        if (!container.guard().create(this)) {
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
    public boolean getResult() {
        return result;
    }
    
    
    @Override
    public void setResult(boolean value) {
        this.result = value;
    }   
     
    @Override
    public ExecutionHasAccesses accesses() {
        return accesses;
    }

    @Override
    public GuardHasLimits limits() {
        return limits;
    }

    @Override
    public AllInstancesAccess createAllInstancesAccess(ModelType type) throws EolIncrementalExecutionException {
        AllInstancesAccess allInstancesAccess = null;
        try {
            allInstancesAccess = new AllInstancesAccessImpl(type, this);
            
            this.accesses().create(allInstancesAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (allInstancesAccess != null) {
    	        return allInstancesAccess;
    	    }
            try {
                allInstancesAccess = this.accesses.get().stream()
                    .map(AllInstancesAccess.class::cast)
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return allInstancesAccess;
    }      
            
    @Override
    public PropertyAccess createPropertyAccess(Property property) throws EolIncrementalExecutionException {
        PropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccessImpl(property, this);
            
            this.accesses().create(propertyAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyAccess != null) {
    	        return propertyAccess;
    	    }
            try {
                propertyAccess = this.accesses.get().stream()
                    .map(PropertyAccess.class::cast)
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return propertyAccess;
    }      
            
                  
    @Override
    public boolean sameIdentityAs(final Guard other) {
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
        if (!(obj instanceof GuardImpl))
            return false;
        GuardImpl other = (GuardImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        if (limits.get() == null) {
            if (other.limits.get() != null)
                return false;
        } else if (!limits.get().equals(other.limits.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((limits.get() == null) ? 0 : limits.get().hashCode());
        return result;
    }

}
