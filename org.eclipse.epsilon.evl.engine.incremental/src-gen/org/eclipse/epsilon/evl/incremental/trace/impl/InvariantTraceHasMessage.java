 /*******************************************************************************
 * This file was automatically generated on: 2018-09-05.
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
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasMessage;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantTraceHasMessage reference. 
 */
public class InvariantTraceHasMessage extends Feature implements IInvariantTraceHasMessage {
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** The target(s) of the reference */
    protected IMessageTrace target;
    
    /**
     * Instantiates a new IInvariantTraceHasMessage.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasMessage (IInvariantTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IMessageTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IMessageTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IMessageTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.invariant().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IMessageTrace target) {
        if (!related(target)) {
            return false;
        }
        target.invariant().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IMessageTrace target) {
        boolean result = false;
        result |= this.target != null;
        result |= target.invariant().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IMessageTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target) && source.equals(target.invariant().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IMessageTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IMessageTrace target) {
        this.target = null;
    }

}