 /*******************************************************************************
 * This file was automatically generated on: 2018-04-19.
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

import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.INestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.NestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasSatisfies;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.CheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasSatisfies;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTrace;

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

    /** The guard relation */
    private final IGuardedElementTraceHasGuard guard;

    /** The accesses relation */
    private final IModuleElementTraceHasAccesses accesses;

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
    public InvariantTrace(String name, IContextTrace container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.guard = new GuardedElementTraceHasGuard(this);
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.check = new InvariantTraceHasCheck(this);
        this.message = new InvariantTraceHasMessage(this);
        this.satisfies = new InvariantTraceHasSatisfies(this);
        this.invariantContext = new InvariantTraceHasInvariantContext(this);
        if (!container.constraints().create(this)) {
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
    public IGuardedElementTraceHasGuard guard() {
        return guard;
    }

    @Override
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
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
    public INestedModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
        return null;
        /** protected region parentTrace end **/
    }


    @Override
    public IGuardTrace createGuardTrace() throws EolIncrementalExecutionException {
        IGuardTrace guardTrace = null;
        try {
            guardTrace = new GuardTrace(this);
            
            this.guard().create(guardTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (guardTrace != null) {
    	        return guardTrace;
    	    }
            guardTrace = this.guard.get();
            if (guardTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guardTrace;
    }      
                  
    @Override
    public ICheckTrace createCheckTrace() throws EolIncrementalExecutionException {
        ICheckTrace checkTrace = null;
        try {
            checkTrace = new CheckTrace(this);
            
            this.check().create(checkTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (checkTrace != null) {
    	        return checkTrace;
    	    }
            checkTrace = this.check.get();
            if (checkTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested CheckTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return checkTrace;
    }      
                  
    @Override
    public IMessageTrace createMessageTrace() throws EolIncrementalExecutionException {
        IMessageTrace messageTrace = null;
        try {
            messageTrace = new MessageTrace(this);
            
            this.message().create(messageTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (messageTrace != null) {
    	        return messageTrace;
    	    }
            messageTrace = this.message.get();
            if (messageTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested MessageTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return messageTrace;
    }      
                  
    @Override
    public ISatisfiesTrace createSatisfiesTrace() throws EolIncrementalExecutionException {
        ISatisfiesTrace satisfiesTrace = null;
        try {
            satisfiesTrace = new SatisfiesTrace(this);
            
            this.satisfies().create(satisfiesTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (satisfiesTrace != null) {
    	        return satisfiesTrace;
    	    }
            satisfiesTrace = this.satisfies.get();
            if (satisfiesTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested SatisfiesTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return satisfiesTrace;
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
