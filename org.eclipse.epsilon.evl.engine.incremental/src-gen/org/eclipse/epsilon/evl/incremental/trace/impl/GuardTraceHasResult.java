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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTraceHasResult;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IGuardTraceHasResult reference. 
 */
public class GuardTraceHasResult extends Feature implements IGuardTraceHasResult {
    
    /** The source(s) of the reference */
    protected IGuardTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IGuardResult> target =  new ConcurrentLinkedQueue<IGuardResult>();
    
    /**
     * Instantiates a new IGuardTraceHasResult.
     *
     * @param source the source of the reference
     */
    public GuardTraceHasResult (IGuardTrace source) {
        super(false);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IGuardResult> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IGuardResult target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IGuardResult exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IGuardResult target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IGuardResult target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IGuardResult target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IGuardResult target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IGuardResult target) {
        this.target.remove(target);
    }

}