 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTraceHasInvariant;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IMessageTraceHasInvariant reference. 
 */
@SuppressWarnings("unused") 
public class MessageTraceHasInvariant extends Feature implements IMessageTraceHasInvariant {
    
    /** The source(s) of the reference */
    protected IMessageTrace source;
    
    /** The target(s) of the reference */
    protected IInvariantTrace target;
    
    /**
     * Instantiates a new IMessageTraceHasInvariant.
     *
     * @param source the source of the reference
     */
    public MessageTraceHasInvariant (IMessageTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IInvariantTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IInvariantTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IInvariantTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.message().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IInvariantTrace target) {
        if (!related(target)) {
            return false;
        }
        target.message().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IInvariantTrace target) {
        boolean result = false;
        result |= this.target != null;
        result |= target.message().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IInvariantTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target) && source.equals(target.message().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IInvariantTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IInvariantTrace target) {
        this.target = null;
    }

}