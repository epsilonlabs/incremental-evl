 /*******************************************************************************
 * This file was automatically generated on: 2019-01-24.
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
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccessHasModelElement;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of ISatisfiesAccessHasModelElement reference. 
 */
public class SatisfiesAccessHasModelElement extends Feature implements ISatisfiesAccessHasModelElement {
    
    /** The source(s) of the reference */
    protected ISatisfiesAccess source;
    
    /** The target(s) of the reference */
    protected IModelElementTrace target;
    
    /**
     * Instantiates a new ISatisfiesAccessHasModelElement.
     *
     * @param source the source of the reference
     */
    public SatisfiesAccessHasModelElement (ISatisfiesAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelElementTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IModelElementTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelElementTrace exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        result |= this.target != null;
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        this.target = null;
    }

}