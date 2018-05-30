 /*******************************************************************************
 * This file was automatically generated on: 2018-04-13.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.IExecutionAccess;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionAccessHasAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionAccessHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionAccessHasAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionAccessHasExecutionContext;

/**
 * Implementation of IExecutionAccess. 
 */
public class ExecutionAccess implements IExecutionAccess {

    /** The access relation */
    private final IExecutionAccessHasAccess access;

    /** The executionContext relation */
    private final IExecutionAccessHasExecutionContext executionContext;

    /**
     * Instantiates a new ExecutionAccess. The ExecutionAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ExecutionAccess(IAccess access, IExecutionContext executionContext) throws TraceModelDuplicateRelation {
        this.access = new ExecutionAccessHasAccess(this);
        if (!this.access.create(access)) {
            throw new TraceModelDuplicateRelation();
        }
        this.executionContext = new ExecutionAccessHasExecutionContext(this);
        if (!this.executionContext.create(executionContext)) {
            throw new TraceModelDuplicateRelation();
        }
    }
    
    @Override
    public IExecutionAccessHasAccess access() {
        return access;
    }

    @Override
    public IExecutionAccessHasExecutionContext executionContext() {
        return executionContext;
    }

    @Override
    public boolean sameIdentityAs(final IExecutionAccess other) {
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
        if (!(obj instanceof ExecutionAccess))
            return false;
        ExecutionAccess other = (ExecutionAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        // Will use access for equals
        if (access.get() == null) {
            if (other.access.get() != null)
                return false;
        }
        else if (!access.get().equals(other.access.get())) {
            return false;
        }
        // Will use executionContext for equals
        if (executionContext.get() == null) {
            if (other.executionContext.get() != null)
                return false;
        }
        else if (!executionContext.get().equals(other.executionContext.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((access.get() == null) ? 0 : access.get().hashCode());
        result = prime * result + ((executionContext.get() == null) ? 0 : executionContext.get().hashCode());
        return result;
    }
}
