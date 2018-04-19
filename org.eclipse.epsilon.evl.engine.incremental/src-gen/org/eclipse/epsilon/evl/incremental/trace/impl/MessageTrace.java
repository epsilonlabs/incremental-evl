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

import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.INestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.NestedModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTraceHasInvariant;

/**
 * Implementation of IMessageTrace. 
 */
public class MessageTrace implements IMessageTrace {

    /** The id */
    private Object id;

    /** The accesses relation */
    private final IModuleElementTraceHasAccesses accesses;

    /** The invariant relation */
    private final IMessageTraceHasInvariant invariant;

    /**
     * Instantiates a new MessageTrace. The MessageTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public MessageTrace(IInvariantTrace container) throws TraceModelDuplicateRelation {
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.invariant = new MessageTraceHasInvariant(this);
        if (!container.message().create(this)) {
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
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IMessageTraceHasInvariant invariant() {
        return invariant;
    }

    @Override
    public INestedModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
        return null;
        /** protected region parentTrace end **/
    }


    @Override
    public boolean sameIdentityAs(final IMessageTrace other) {
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
        if (!(obj instanceof MessageTrace))
            return false;
        MessageTrace other = (MessageTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (invariant.get() == null) {
            if (other.invariant.get() != null)
                return false;
        }
        else if (!invariant.get().equals(other.invariant.get())) {
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
