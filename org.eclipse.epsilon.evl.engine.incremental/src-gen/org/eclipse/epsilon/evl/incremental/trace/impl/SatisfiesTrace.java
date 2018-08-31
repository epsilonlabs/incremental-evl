 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region SatisfiesTraceImports on begin **/
/** protected region SatisfiesTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ISatisfiesTrace. 
 */
public class SatisfiesTrace implements ISatisfiesTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The all.
	 */
    private Boolean all;

    /**
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private final IModuleElementTraceHasAccesses accesses;

    /**
     * The parentTrace.
     */
    private final IInContextModuleElementTraceHasParentTrace parentTrace;

    /**
     * The invariant.
     */
    private final ISatisfiesTraceHasInvariant invariant;

    /**
     * The satisfiedInvariants.
     */
    private final ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants;

    /**
     * Instantiates a new SatisfiesTrace. The SatisfiesTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesTrace(IInvariantTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.satisfies().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.invariant = new SatisfiesTraceHasInvariant(this);
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.parentTrace = new InContextModuleElementTraceHasParentTrace(this);
        this.satisfiedInvariants = new SatisfiesTraceHasSatisfiedInvariants(this);


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
    public Boolean getAll() {
        return all;
    }
    
    
    @Override
    public void setAll(boolean value) {
        this.all = value;
    }   
     
    @Override
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        return parentTrace;
    }

    @Override
    public ISatisfiesTraceHasInvariant invariant() {
        return invariant;
    }

    @Override
    public ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants() {
        return satisfiedInvariants;
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
        if (invariant.get() == null) {
            if (other.invariant.get() != null)
                return false;
        }
        if (!invariant.get().equals(other.invariant.get())) {
            return false;
        }
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
