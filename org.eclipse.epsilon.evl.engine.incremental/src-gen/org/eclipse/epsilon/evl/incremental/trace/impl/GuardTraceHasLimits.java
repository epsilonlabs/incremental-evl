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
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTraceHasLimits;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IGuardTraceHasLimits reference. 
 */
public class GuardTraceHasLimits extends Feature implements IGuardTraceHasLimits {
    
    /** The source(s) of the reference */
    protected IGuardTrace source;
    
    /** The target(s) of the reference */
    protected IGuardedElementTrace target;
    
    /**
     * Instantiates a new IGuardTraceHasLimits.
     *
     * @param source the source of the reference
     */
    public GuardTraceHasLimits (IGuardTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IGuardedElementTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IGuardedElementTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IGuardedElementTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.guard().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardedElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.guard().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardedElementTrace target) {
        boolean result = false;
        result |= this.target != null;
        result |= target.guard().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IGuardedElementTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target) && source.equals(target.guard().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IGuardedElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IGuardedElementTrace target) {
        this.target = null;
    }

}