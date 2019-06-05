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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTraceHasResult;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of ICheckTraceHasResult reference. 
 */
@SuppressWarnings("unused") 
public class CheckTraceHasResult extends Feature implements ICheckTraceHasResult {
    
    /** The source(s) of the reference */
    protected ICheckTrace source;
    
    /** The target(s) of the reference */
    protected Queue<ICheckResult> target =  new ConcurrentLinkedQueue<ICheckResult>();
    
    /**
     * Instantiates a new ICheckTraceHasResult.
     *
     * @param source the source of the reference
     */
    public CheckTraceHasResult (ICheckTrace source) {
        super(false);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<ICheckResult> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(ICheckResult target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous ICheckResult exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ICheckResult target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ICheckResult target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(ICheckResult target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(ICheckResult target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ICheckResult target) {
        this.target.remove(target);
    }

}