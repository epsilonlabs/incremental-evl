 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.eol.incremental.trace.Execution;
import org.eclipse.epsilon.eol.incremental.trace.Access;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ExecutionHasAccesses reference. 
 */
public class ExecutionHasAccessesImpl extends Feature implements ExecutionHasAccesses {
    
    /** The source(s) of the reference */
    protected Execution source;
    
    /** The target(s) of the reference */
    protected Queue<Access> target =  new ConcurrentLinkedQueue<Access>();
    
    /**
     * Instantiates a new ExecutionHasAccesses.
     *
     * @param source the source of the reference
     */
    public ExecutionHasAccessesImpl (Execution source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<Access> get() {
        return target;
    }
    
    @Override
    public boolean create(Access target) {
        if (conflict(target)) {
            return false;
        }
        target.execution().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Access target) {
        if (!related(target)) {
            return false;
        }
        target.execution().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Access target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.execution().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Access target) {
  
        return get().contains(target) & source.equals(target.execution().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(Access target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(Access target) {
        this.target.remove(target);
    }

}