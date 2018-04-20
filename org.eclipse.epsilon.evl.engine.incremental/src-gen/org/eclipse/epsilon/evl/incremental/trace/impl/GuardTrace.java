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

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.INestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.NestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTraceHasLimits;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTraceHasLimits;

/**
 * Implementation of IGuardTrace. 
 */
public class GuardTrace implements IGuardTrace {

    /** The id */
    private Object id;

    /** The result */
    private boolean result;

    /** The accesses relation */
    private final IModuleElementTraceHasAccesses accesses;

    /** The limits relation */
    private final IGuardTraceHasLimits limits;

    /**
     * Instantiates a new GuardTrace. The GuardTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardTrace(IGuardedElementTrace container) throws TraceModelDuplicateRelation {
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.limits = new GuardTraceHasLimits(this);
        if (!container.guard().create(this)) {
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
    public boolean getResult() {
        return result;
    }
    
    
    @Override
    public void setResult(boolean value) {
        this.result = value;
    }   
     
    @Override
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IGuardTraceHasLimits limits() {
        return limits;
    }

    @Override
    public INestedModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
    		if (limits.get() instanceof IContextTrace) {
    			return null;
        }
        else {
        		return ((IInvariantTrace)limits.get()).parentTrace();
        }
        /** protected region parentTrace end **/
    }


    @Override
    public boolean sameIdentityAs(final IGuardTrace other) {
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
        if (!(obj instanceof GuardTrace))
            return false;
        GuardTrace other = (GuardTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (limits.get() == null) {
            if (other.limits.get() != null)
                return false;
        }
        else if (!limits.get().equals(other.limits.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((limits.get() == null) ? 0 : limits.get().hashCode());
        return result;
    }
}
