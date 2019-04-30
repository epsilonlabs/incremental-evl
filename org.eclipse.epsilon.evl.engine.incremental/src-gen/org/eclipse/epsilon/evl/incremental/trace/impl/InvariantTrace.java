 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region InvariantTraceImports on begin **/
/** protected region InvariantTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IInvariantTrace. 
 */
@SuppressWarnings("unused") 
public class InvariantTrace implements IInvariantTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The name.
	 */
    private String name;

    /**
     * The guard.
     */
    private final IGuardedElementTraceHasGuard guard;

    /**
     * The contextModuleElement.
     */
    private final IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The check.
     */
    private final IInvariantTraceHasCheck check;

    /**
     * The message.
     */
    private final IInvariantTraceHasMessage message;

    /**
     * The invariantContext.
     */
    private final IInvariantTraceHasInvariantContext invariantContext;

    /**
     * Instantiates a new InvariantTrace. The InvariantTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public InvariantTrace(String name,
                          IContextTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.name = name;
        if (!container.constraints().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.invariantContext = new InvariantTraceHasInvariantContext(this);
        this.guard = new GuardedElementTraceHasGuard(this);
        this.contextModuleElement = new InContextModuleElementTraceHasContextModuleElement(this);
        this.check = new InvariantTraceHasCheck(this);
        this.message = new InvariantTraceHasMessage(this);

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
    public IGuardedElementTraceHasGuard guard() {
        return guard;
    }

    @Override
    public IInContextModuleElementTraceHasContextModuleElement contextModuleElement() {
        return contextModuleElement;
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
    public IInvariantTraceHasInvariantContext invariantContext() {
        return invariantContext;
    }

    @Override
    public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {
        IGuardTrace guardTrace = null;
        
        try {
            guardTrace = new GuardTrace(this);
            this.guard().create(guardTrace);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (guardTrace == null) {
                guardTrace = this.guard.get();
            }
            if (guardTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guardTrace;
    }      
                  
    @Override
    public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {
        ICheckTrace checkTrace = null;
        
        try {
            checkTrace = new CheckTrace(this);
            this.check().create(checkTrace);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (checkTrace == null) {
                checkTrace = this.check.get();
            }
            if (checkTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested CheckTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return checkTrace;
    }      
                  
    @Override
    public IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException {
        IMessageTrace messageTrace = null;
        
        try {
            messageTrace = new MessageTrace(this);
            this.message().create(messageTrace);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (messageTrace == null) {
                messageTrace = this.message.get();
            }
            if (messageTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested MessageTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return messageTrace;
    }      
                  
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", getName());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IInvariantTrace other) {
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
        if (!(obj instanceof InvariantTrace))
            return false;
        InvariantTrace other = (InvariantTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (invariantContext.get() == null) {
            if (other.invariantContext.get() != null)
                return false;
        }
        if (!invariantContext.get().equals(other.invariantContext.get())) {
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
