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

import org.eclipse.epsilon.evl.incremental.trace.Check;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.CheckHasInvariant;

import org.eclipse.epsilon.eol.incremental.trace.Access;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;

/**
 * Implementation of Check. 
 */
public class CheckImpl implements Check {

    /** The id */
    private Object id;

    /** The accesses relation */
    private final ExecutionHasAccesses accesses;

    /** The invariant relation */
    private final CheckHasInvariant invariant;

    /**
     * Instantiates a new Check. The Check is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckImpl(Invariant container) {
        accesses = new ExecutionHasAccessesImpl(this);
        invariant = new CheckHasInvariantImpl(this);
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
    public CheckHasInvariant invariant() {
        return invariant;
    }
 

}