 /*******************************************************************************
 * This file was automatically generated on: 2019-06-04.
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
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IGuardedElementTraceHasGuard reference. 
 */
@SuppressWarnings("unused") 
public class GuardedElementTraceHasGuard extends Feature implements IGuardedElementTraceHasGuard {
    
    /** The source(s) of the reference */
    protected IGuardedElementTrace source;
    
    /** The target(s) of the reference */
    protected IGuardTrace target;
    
    /**
     * Instantiates a new IGuardedElementTraceHasGuard.
     *
     * @param source the source of the reference
     */
    public GuardedElementTraceHasGuard (IGuardedElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IGuardTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IGuardTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IGuardTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.limits().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardTrace target) {
        if (!related(target)) {
            return false;
        }
        target.limits().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardTrace target) {
        boolean result = false;
        result |= this.target != null;
        result |= target.limits().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IGuardTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target) && source.equals(target.limits().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IGuardTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IGuardTrace target) {
        this.target = null;
    }

}