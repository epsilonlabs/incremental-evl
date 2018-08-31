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
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region GuardTraceImports on begin **/
/** protected region GuardTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IGuardTrace. 
 */
public class GuardTrace implements IGuardTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The result.
	 */
    private Boolean result;

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
     * The limits.
     */
    private final IGuardTraceHasLimits limits;

    /**
     * Instantiates a new GuardTrace. The GuardTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardTrace(IGuardedElementTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.guard().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.limits = new GuardTraceHasLimits(this);
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.parentTrace = new InContextModuleElementTraceHasParentTrace(this);


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
    public Boolean getResult() {
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
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        return parentTrace;
    }

    @Override
    public IGuardTraceHasLimits limits() {
        return limits;
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
        if (!limits.get().equals(other.limits.get())) {
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
