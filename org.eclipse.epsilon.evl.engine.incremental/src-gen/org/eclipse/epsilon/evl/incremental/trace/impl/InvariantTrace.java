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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import java.util.Arrays;
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
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasSatisfies;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasSatisfies;

/**
 * Implementation of IInvariantTrace. 
 */
public class InvariantTrace implements IInvariantTrace {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The result */
    private boolean result;

    /** The accesses relation */
    private final IExecutionTraceHasAccesses accesses;

    /** The guard relation */
    private final IGuardedElementTraceHasGuard guard;

    /** The check relation */
    private final IInvariantTraceHasCheck check;

    /** The message relation */
    private final IInvariantTraceHasMessage message;

    /** The satisfies relation */
    private final IInvariantTraceHasSatisfies satisfies;

    /** The invariantContext relation */
    private final IInvariantTraceHasInvariantContext invariantContext;

    /**
     * Instantiates a new InvariantTrace. The InvariantTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public InvariantTrace(String name, IModuleExecution container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.accesses = new ExecutionTraceHasAccesses(this);
        this.guard = new GuardedElementTraceHasGuard(this);
        this.check = new InvariantTraceHasCheck(this);
        this.message = new InvariantTraceHasMessage(this);
        this.satisfies = new InvariantTraceHasSatisfies(this);
        this.invariantContext = new InvariantTraceHasInvariantContext(this);
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
    public String getName() {
        return name;
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
    public IExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IGuardedElementTraceHasGuard guard() {
        return guard;
    }

    @Override
    public IInvariantTraceHasCheck check() {
        return check;
    }

    @Override
    public IInvariantTraceHasMessage message() {
        return message;
    }

    @Override
    public IInvariantTraceHasSatisfies satisfies() {
        return satisfies;
    }

    @Override
    public IInvariantTraceHasInvariantContext invariantContext() {
        return invariantContext;
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
                    .filter(t -> t instanceof IAllInstancesAccess)
                    .map(IAllInstancesAccess.class::cast)
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
                    .filter(t -> t instanceof IPropertyAccess)
                    .map(IPropertyAccess.class::cast)
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
    public boolean sameIdentityAs(final IInvariantTrace other) {
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
        if (!(obj instanceof InvariantTrace))
            return false;
        InvariantTrace other = (InvariantTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        // Will use invariantContext for equals
        if (invariantContext.get() == null) {
            if (other.invariantContext.get() != null)
                return false;
        }
        else if (!invariantContext.get().equals(other.invariantContext.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((invariantContext.get() == null) ? 0 : invariantContext.get().hashCode());
        return result;
    }

}
