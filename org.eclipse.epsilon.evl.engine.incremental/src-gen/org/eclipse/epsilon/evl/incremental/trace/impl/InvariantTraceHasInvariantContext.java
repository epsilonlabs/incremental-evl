 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantTraceHasInvariantContext reference. 
 */
@SuppressWarnings("unused") 
public class InvariantTraceHasInvariantContext extends Feature implements IInvariantTraceHasInvariantContext {
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** The target(s) of the reference */
    protected IContextTrace target;
    
    /**
     * Instantiates a new IInvariantTraceHasInvariantContext.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasInvariantContext (IInvariantTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IContextTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IContextTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IContextTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.constraints().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IContextTrace target) {
        if (!related(target)) {
            return false;
        }
        target.constraints().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IContextTrace target) {
        boolean result = false;
        result |= this.target != null;
		Iterable<IInvariantTrace> iterable = () -> target.constraints().get();
		Stream<IInvariantTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        result |= target.constraints().isUnique() &&
        	targetStream.anyMatch(source::equals);
        return result;
    }
    
    @Override
    public boolean related(IContextTrace target) {
    	if (target == null) {
			return false;
		}
        
		Iterable<IInvariantTrace> iterable = () -> target.constraints().get();
		Stream<IInvariantTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return target.equals(this.target) && targetStream.anyMatch(source::equals);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IContextTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IContextTrace target) {
        this.target = null;
    }

}