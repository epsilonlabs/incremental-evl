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

import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasInvariants;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTraceHasInvariants;

/**
 * Implementation of ISatisfiesTrace. 
 */
public class SatisfiesTrace implements ISatisfiesTrace {

    /** The all */
    private boolean all;

    /** The invariants relation */
    private final ISatisfiesTraceHasInvariants invariants;

    /**
     * Instantiates a new SatisfiesTrace. The SatisfiesTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesTrace(IInvariantTrace container) throws TraceModelDuplicateRelation {
        this.invariants = new SatisfiesTraceHasInvariants(this);
        if (!container.satisfies().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
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
    public ISatisfiesTraceHasInvariants invariants() {
        return invariants;
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
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return result;
    }

}
