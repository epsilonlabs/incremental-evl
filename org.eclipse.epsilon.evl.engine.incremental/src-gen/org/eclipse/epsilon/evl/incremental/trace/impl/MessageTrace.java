 /*******************************************************************************
 * This file was automatically generated on: 2017-11-23.
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

import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.ElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTraceHasInvariant;

/**
 * Implementation of IMessageTrace. 
 */
public class MessageTrace implements IMessageTrace {

    /** The id */
    private Object id;

    /** The accesses relation */
    private final IExecutionTraceHasAccesses accesses;

    /** The invariant relation */
    private final IMessageTraceHasInvariant invariant;

    /**
     * Instantiates a new MessageTrace. The MessageTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public MessageTrace(IInvariantTrace container) throws TraceModelDuplicateRelation {
        this.accesses = new ExecutionTraceHasAccesses(this);
        this.invariant = new MessageTraceHasInvariant(this);
        if (!container.message().create(this)) {
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
    public IExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IMessageTraceHasInvariant invariant() {
        return invariant;
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
    public IElementAccess createElementAccess(IModelElementTrace modelElement) throws EolIncrementalExecutionException {
        IElementAccess elementAccess = null;
        try {
            elementAccess = new ElementAccess(modelElement, this);
            
            this.accesses().create(elementAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (elementAccess != null) {
    	        return elementAccess;
    	    }
            try {
                elementAccess = this.accesses.get().stream()
                    .map(ElementAccess.class::cast)
                    .filter(item -> item.modelElement().get().equals(modelElement))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return elementAccess;
    }      
            
    @Override
    public IPropertyAccess createPropertyAccess(IModelElementTrace modelElement, IPropertyTrace property) throws EolIncrementalExecutionException {
        IPropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccess(modelElement, property, this);
            
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
                    .filter(item -> item.modelElement().get().equals(modelElement))
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
    public boolean sameIdentityAs(final IMessageTrace other) {
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
        if (!(obj instanceof MessageTrace))
            return false;
        MessageTrace other = (MessageTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (invariant.get() == null) {
            if (other.invariant.get() != null)
                return false;
        } else if (!invariant.get().equals(other.invariant.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invariant.get() == null) ? 0 : invariant.get().hashCode());
        return result;
    }

}
