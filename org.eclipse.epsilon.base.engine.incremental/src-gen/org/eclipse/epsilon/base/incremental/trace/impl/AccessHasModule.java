 /*******************************************************************************
 * This file was automatically generated on: 2019-06-05.
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
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccessHasModule;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IAccessHasModule reference. 
 */
@SuppressWarnings("unused") 
public class AccessHasModule extends Feature implements IAccessHasModule {
    
    /** The source(s) of the reference */
    protected IAccess source;
    
    /** The target(s) of the reference */
    protected IModuleExecutionTrace target;
    
    /**
     * Instantiates a new IAccessHasModule.
     *
     * @param source the source of the reference
     */
    public AccessHasModule (IAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModuleExecutionTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IModuleExecutionTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModuleExecutionTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.accesses().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModuleExecutionTrace target) {
        if (!related(target)) {
            return false;
        }
        target.accesses().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModuleExecutionTrace target) {
        boolean result = false;
        result |= this.target != null;
		Iterable<IAccess> iterable = () -> target.accesses().get();
		Stream<IAccess> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        result |= target.accesses().isUnique() &&
        	targetStream.anyMatch(source::equals);
        return result;
    }
    
    @Override
    public boolean related(IModuleExecutionTrace target) {
    	if (target == null) {
			return false;
		}
        
		Iterable<IAccess> iterable = () -> target.accesses().get();
		Stream<IAccess> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return target.equals(this.target) && targetStream.anyMatch(source::equals);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModuleExecutionTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModuleExecutionTrace target) {
        this.target = null;
    }

}