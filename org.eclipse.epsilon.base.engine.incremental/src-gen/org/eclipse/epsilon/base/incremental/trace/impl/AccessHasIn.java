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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IAccessHasIn;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IAccessHasIn reference. 
 */
@SuppressWarnings("unused") 
public class AccessHasIn extends Feature implements IAccessHasIn {
    
    /** The source(s) of the reference */
    protected IAccess source;
    
    /** The target(s) of the reference */
    protected IExecutionContext target;
    
    /**
     * Instantiates a new IAccessHasIn.
     *
     * @param source the source of the reference
     */
    public AccessHasIn (IAccess source) {
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