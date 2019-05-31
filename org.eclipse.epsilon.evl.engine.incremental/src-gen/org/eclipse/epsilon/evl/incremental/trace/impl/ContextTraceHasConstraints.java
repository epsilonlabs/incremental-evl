 /*******************************************************************************
 * This file was automatically generated on: 2019-05-31.
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
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IContextTraceHasConstraints reference. 
 */
@SuppressWarnings("unused") 
public class ContextTraceHasConstraints extends Feature implements IContextTraceHasConstraints {
    
    /** The source(s) of the reference */
    protected IContextTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IInvariantTrace> target =  new ConcurrentSetQueue<IInvariantTrace>();
    
    /**
     * Instantiates a new IContextTraceHasConstraints.
     *
     * @param source the source of the reference
     */
    public ContextTraceHasConstraints (IContextTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IInvariantTrace> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IInvariantTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IInvariantTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.invariantContext().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IInvariantTrace target) {
        if (!related(target)) {
            return false;
        }
        target.invariantContext().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IInvariantTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        result |= target.invariantContext().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IInvariantTrace target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target) && source.equals(target.invariantContext().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IInvariantTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IInvariantTrace target) {
        this.target.remove(target);
    }

}