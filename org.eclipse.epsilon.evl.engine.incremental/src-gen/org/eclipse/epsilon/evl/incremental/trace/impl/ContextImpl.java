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

import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.evl.incremental.trace.ContextHasConstraints;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElementHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementHasGuardImpl;

/**
 * Implementation of Context. 
 */
public class ContextImpl implements Context {

    /** The id */
    private Object id;

    /** The accesses relation */
    private final ExecutionHasAccesses accesses;

    /** The guard relation */
    private final GuardedElementHasGuard guard;

    /** The constraints relation */
    private final ContextHasConstraints constraints;

    /**
     * Instantiates a new Context.
     */
    public ContextImpl() {
        accesses = new ExecutionHasAccessesImpl(this);
        guard = new GuardedElementHasGuardImpl(this);
        constraints = new ContextHasConstraintsImpl(this);
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
    public ExecutionHasAccesses accesses() {
        return accesses;
    }
    @Override
    public GuardedElementHasGuard guard() {
        return guard;
    }
    @Override
    public ContextHasConstraints constraints() {
        return constraints;
    }
 

}