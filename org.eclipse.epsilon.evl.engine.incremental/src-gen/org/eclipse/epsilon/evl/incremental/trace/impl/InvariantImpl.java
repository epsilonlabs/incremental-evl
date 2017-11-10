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

import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.Check;
import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElementHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasSatisfies;
import org.eclipse.epsilon.evl.incremental.trace.Message;
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import org.eclipse.epsilon.evl.incremental.trace.impl.CheckImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementHasGuardImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantHasCheckImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantHasMessageImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantHasSatisfiesImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesImpl;

/**
 * Implementation of Invariant. 
 */
public class InvariantImpl implements Invariant {

    /** The name */
    private String name;

    /** The result */
    private boolean result;

    /** The guard relation */
    private final GuardedElementHasGuard guard;

    /** The check relation */
    private final InvariantHasCheck check;

    /** The message relation */
    private final InvariantHasMessage message;

    /** The satisfies relation */
    private final InvariantHasSatisfies satisfies;

    /**
     * Instantiates a new Invariant. The Invariant is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public InvariantImpl(String name, Context container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.guard = new GuardedElementHasGuardImpl(this);
        this.check = new InvariantHasCheckImpl(this);
        this.message = new InvariantHasMessageImpl(this);
        this.satisfies = new InvariantHasSatisfiesImpl(this);
        if (!container.constraints().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    
    @Override
    public void setName(String value) {
        this.name = value;
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
    public GuardedElementHasGuard guard() {
        return guard;
    }

    @Override
    public InvariantHasCheck check() {
        return check;
    }

    @Override
    public InvariantHasMessage message() {
        return message;
    }

    @Override
    public InvariantHasSatisfies satisfies() {
        return satisfies;
    }

    @Override
    public Guard createGuard() throws EolIncrementalExecutionException {
        Guard guard = null;
        try {
            guard = new GuardImpl(this);
            
            this.guard().create(guard);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (guard != null) {
    	        return guard;
    	    }
            guard  = this.guard.get();
            if (guard  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Guard was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guard;
    }      
                  
    @Override
    public Check createCheck() throws EolIncrementalExecutionException {
        Check check = null;
        try {
            check = new CheckImpl(this);
            
            this.check().create(check);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (check != null) {
    	        return check;
    	    }
            check  = this.check.get();
            if (check  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Check was "
                        + "duplicate but previous one was not found.");
            }
        }
        return check;
    }      
                  
    @Override
    public Message createMessage() throws EolIncrementalExecutionException {
        Message message = null;
        try {
            message = new MessageImpl(this);
            
            this.message().create(message);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (message != null) {
    	        return message;
    	    }
            message  = this.message.get();
            if (message  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Message was "
                        + "duplicate but previous one was not found.");
            }
        }
        return message;
    }      
                  
    @Override
    public Satisfies createSatisfies() throws EolIncrementalExecutionException {
        Satisfies satisfies = null;
        try {
            satisfies = new SatisfiesImpl(this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (satisfies != null) {
    	        return satisfies;
    	    }
            satisfies  = this.satisfies.get();
            if (satisfies  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Satisfies was "
                        + "duplicate but previous one was not found.");
            }
        }
        return satisfies;
    }      
                  
    @Override
    public boolean sameIdentityAs(final Invariant other) {
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
        if (!(obj instanceof InvariantImpl))
            return false;
        InvariantImpl other = (InvariantImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
