 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantResultHasContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IInvariantResultHasContext reference. 
 */
public class InvariantResultHasContext extends Feature implements IInvariantResultHasContext {
    
    /** The source(s) of the reference */
    protected IGuardResult source;
    
    /** The target(s) of the reference */
    protected IExecutionContext target;
    
    /**
     * Instantiates a new IInvariantResultHasContext.
     *
     * @param source the source of the reference
     */
    public InvariantResultHasContext (IGuardResult source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IExecutionContext get() {
        return target;
    }
    

    @Override
    public boolean create(IExecutionContext target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IExecutionContext exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionContext target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        result |= this.target != null;
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionContext target) {
        this.target = target;
    }
    
    @Override
    public void remove(IExecutionContext target) {
        this.target = null;
    }

}