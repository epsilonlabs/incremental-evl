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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.Message;
import org.eclipse.epsilon.evl.incremental.trace.InvariantHasMessage;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of InvariantHasMessage reference. 
 */
public class InvariantHasMessageImpl extends Feature implements InvariantHasMessage {
    
    /** The source(s) of the reference */
    protected Invariant source;
    
    /** The target(s) of the reference */
    protected Message target;
    
    /**
     * Instantiates a new InvariantHasMessage.
     *
     * @param source the source of the reference
     */
    public InvariantHasMessageImpl (Invariant source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Message get() {
        return target;
    }
    
    @Override
    public boolean create(Message target) {
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
    public boolean destroy(Message target) {
        if (!related(target)) {
            return false;
        }
        target.invariant().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Message target) {
        boolean result = false;
        result |= get() != null;
        result |= target.invariant().get() != null;
        return result;
    }
    
    @Override
    public boolean related(Message target) {
  
        return target.equals(this.target) & source.equals(target.invariant().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(Message target) {
        this.target = target;
    }
    
    @Override
    public void remove(Message target) {
        this.target = null;
    }

}