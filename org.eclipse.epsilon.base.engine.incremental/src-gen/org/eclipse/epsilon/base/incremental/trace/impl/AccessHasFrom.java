 /*******************************************************************************
 * This file was automatically generated on: 2019-01-23.
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
import org.eclipse.epsilon.base.incremental.trace.IAccessHasFrom;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IAccessHasFrom reference. 
 */
public class AccessHasFrom extends Feature implements IAccessHasFrom {
    
    /** The source(s) of the reference */
    protected IAccess source;
    
    /** The target(s) of the reference */
    protected IExecutionContext target;
    
    /**
     * Instantiates a new IAccessHasFrom.
     *
     * @param source the source of the reference
     */
    public AccessHasFrom (IAccess source) {
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
        if (related(target)) {
            return false;
        }
        target.accesses().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionContext target) {
        if (!related(target)) {
            return false;
        }
        target.accesses().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        result |= this.target != null;
		Iterable<IAccess> iterable = () -> target.accesses().get();
		Stream<IAccess> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        result |= target.accesses().isUnique() &&
        	targetStream.anyMatch(source::equals);
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
    	if (target == null) {
			return false;
		}
        
		Iterable<IAccess> iterable = () -> target.accesses().get();
		Stream<IAccess> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return target.equals(this.target) && targetStream.anyMatch(source::equals);
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