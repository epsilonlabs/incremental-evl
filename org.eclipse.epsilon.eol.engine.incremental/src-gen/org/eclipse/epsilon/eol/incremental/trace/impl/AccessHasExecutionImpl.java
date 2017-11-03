 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
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

import org.eclipse.epsilon.eol.incremental.trace.Access;
import org.eclipse.epsilon.eol.incremental.trace.Execution;
import org.eclipse.epsilon.eol.incremental.trace.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of execution reference. 
 */
public class AccessHasExecutionImpl extends Feature implements AccessHasExecution {
    
    protected Access source;
    protected Execution target;
    
    /**
     * Instantiates a new AccessHasExecution.
     *
     * @param source the source of the reference
     */
    public AccessHasExecutionImpl (Access source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Execution get() {
        return target;
    }
    
    @Override
    public void set(Execution target) {
        this.target = target;
    }
    
    @Override
    public void remove(Execution target) {
        this.target = null;
    }
    
    @Override
    public boolean conflict(Execution  target) {
        boolean result = false;
        result |= get() != null;
        result |= target.accesses().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(Execution target) {
  
        return target.equals(this.target) & target.accesses().get().contains(source);
    }
    
    @Override
    public boolean create(Execution target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.accesses().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Execution target) {
        if (!related(target)) {
            return false;
        }
        target.accesses().remove(source);
        remove(target);
        return true;
    }

}