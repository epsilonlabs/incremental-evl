 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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

import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.SatisfiesHasInvariants;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesHasInvariantsImpl;

/**
 * Implementation of Satisfies. 
 */
public class SatisfiesImpl implements Satisfies {

    /** The all */
    private boolean all;

    /** The invariants relation */
    private final SatisfiesHasInvariants invariants;

    /**
     * Instantiates a new Satisfies. The Satisfies is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesImpl(Invariant container) throws TraceModelDuplicateRelation {
        this.invariants = new SatisfiesHasInvariantsImpl(this);
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
    public SatisfiesHasInvariants invariants() {
        return invariants;
    }

    @Override
    public boolean sameIdentityAs(final Satisfies other) {
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
        if (!(obj instanceof SatisfiesImpl))
            return false;
        SatisfiesImpl other = (SatisfiesImpl) obj;
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
