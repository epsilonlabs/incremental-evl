 /*******************************************************************************
 * This file was automatically generated on: 2018-08-16.
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
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasSatisfies;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantTraceHasSatisfies reference. 
 */
public class InvariantTraceHasSatisfies extends Feature implements IInvariantTraceHasSatisfies {
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** The target(s) of the reference */
    protected ISatisfiesTrace target;
    
    /**
     * Instantiates a new IInvariantTraceHasSatisfies.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasSatisfies (IInvariantTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public ISatisfiesTrace get() {
        return target;
    }
    

    @Override
    public boolean create(ISatisfiesTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.invariant().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ISatisfiesTrace target) {
        if (!related(target)) {
            return false;
        }
        target.invariant().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ISatisfiesTrace target) {
        boolean result = false;
        result |= this.target != null;
        result |= target.invariant().get() != null;
        return result;
    }
    
    @Override
    public boolean related(ISatisfiesTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target) && source.equals(target.invariant().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(ISatisfiesTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(ISatisfiesTrace target) {
        this.target = null;
    }

}