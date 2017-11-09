 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Message;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.MessageHasInvariant;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of MessageHasInvariant reference. 
 */
public class MessageHasInvariantImpl extends Feature implements MessageHasInvariant {
    
    /** The source(s) of the reference */
    protected Message source;
    
    /** The target(s) of the reference */
    protected Invariant target;
    
    /**
     * Instantiates a new MessageHasInvariant.
     *
     * @param source the source of the reference
     */
    public MessageHasInvariantImpl (Message source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Invariant get() {
        return target;
    }
    
    @Override
    public boolean create(Invariant target) {
        if (conflict(target)) {
            return false;
        }
        target.message().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Invariant target) {
        if (!related(target)) {
            return false;
        }
        target.message().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Invariant target) {
        boolean result = false;
        result |= get() != null;
        result |= target.message().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Invariant target) {
  
        return target.equals(this.target) & source.equals(target.message().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(Invariant target) {
        this.target = target;
    }
    
    @Override
    public void remove(Invariant target) {
        this.target = null;
    }

}