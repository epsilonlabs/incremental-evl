 /*******************************************************************************
 * This file was automatically generated on: 2017-12-08.
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

import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasSatisfiedInvariants;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTraceHasSatisfiedInvariants;

/**
 * Implementation of ISatisfiesTrace. 
 */
public class SatisfiesTrace implements ISatisfiesTrace {

    /** The id */
    private Object id;

    /** The all */
    private boolean all;

    /** The accesses relation */
    private final IExecutionTraceHasAccesses accesses;

    /** The invariant relation */
    private final ISatisfiesTraceHasInvariant invariant;

    /** The satisfiedInvariants relation */
    private final ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants;

    /**
     * Instantiates a new SatisfiesTrace. The SatisfiesTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesTrace(IInvariantTrace invariant, IModuleExecution container) throws TraceModelDuplicateRelation {
        this.accesses = new ExecutionTraceHasAccesses(this);
        this.invariant = new SatisfiesTraceHasInvariant(this);
        this.invariant.create(invariant);
        this.satisfiedInvariants = new SatisfiesTraceHasSatisfiedInvariants(this);
        if (!container.executions().create(this)) {
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
    public boolean getAll() {
        return all;
    }
    
    
    @Override
    public void setAll(boolean value) {
        this.all = value;
    }   
     
    @Override
    public IExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public ISatisfiesTraceHasInvariant invariant() {
        return invariant;
    }

    @Override
    public ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants() {
        return satisfiedInvariants;
    }

    @Override
    public IAllInstancesAccess createAllInstancesAccess(IModelTypeTrace type) throws EolIncrementalExecutionException {
        IAllInstancesAccess allInstancesAccess = null;
        try {
            allInstancesAccess = new AllInstancesAccess(type, this);
            
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
                    .filter(item -> item.type().get().equals(type))
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
    public IPropertyAccess createPropertyAccess(IPropertyTrace property) throws EolIncrementalExecutionException {
        IPropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccess(property, this);
            
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
                    .filter(item -> item.property().get().equals(property))
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
    public boolean sameIdentityAs(final ISatisfiesTrace other) {
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
        if (!(obj instanceof SatisfiesTrace))
            return false;
        SatisfiesTrace other = (SatisfiesTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        // Will use invariant for equals
        if (invariant.get() == null) {
            if (other.invariant.get() != null)
                return false;
        }        else if (!invariant.get().equals(other.invariant.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invariant == null) ? 0 : invariant.hashCode());
        return result;
    }

}
