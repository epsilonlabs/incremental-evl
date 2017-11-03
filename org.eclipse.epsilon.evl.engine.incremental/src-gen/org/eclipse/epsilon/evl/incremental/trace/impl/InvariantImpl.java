 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
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
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasCheck;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasMessage;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasSatisfies;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElementHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementHasGuardImpl;

/**
 * Implementation of Invariant. 
 */
public class InvariantImpl implements Invariant {

    /** The id */
    private Object id;

    /** The result */
    private boolean result;

    /** The accesses relation */
    private final ExecutionHasAccesses accesses;

    /** The guard relation */
    private final GuardedElementHasGuard guard;

    /** The check relation */
    private final InvariantHasCheck check;

    /** The message relation */
    private final InvariantHasMessage message;

    /** The satisfies relation */
    private final InvariantHasSatisfies satisfies;

    /**
     * Instantiates a new Invariant.
     */
    public InvariantImpl() {
        accesses = new ExecutionHasAccessesImpl(this);
        guard = new GuardedElementHasGuardImpl(this);
        check = new InvariantHasCheckImpl(this);
        message = new InvariantHasMessageImpl(this);
        satisfies = new InvariantHasSatisfiesImpl(this);
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
 

}