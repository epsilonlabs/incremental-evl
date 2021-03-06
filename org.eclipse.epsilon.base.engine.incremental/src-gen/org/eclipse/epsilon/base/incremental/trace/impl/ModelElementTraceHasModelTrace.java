 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModelElementTraceHasModelTrace reference. 
 */
public class ModelElementTraceHasModelTrace extends Feature implements IModelElementTraceHasModelTrace {
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
    /** The target(s) of the reference */
    protected IModelTrace target;
    
    /**
     * Instantiates a new IModelElementTraceHasModelTrace.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasModelTrace (IModelElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IModelTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.elements().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTrace target) {
        if (!related(target)) {
            return false;
        }
        target.elements().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTrace target) {
        boolean result = false;
        result |= this.target != null;
		Iterable<IModelElementTrace> iterable = () -> target.elements().get();
		Stream<IModelElementTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        result |= target.elements().isUnique() &&
        	targetStream.anyMatch(source::equals);
        return result;
    }
    
    @Override
    public boolean related(IModelTrace target) {
    	if (target == null) {
			return false;
		}
        
		Iterable<IModelElementTrace> iterable = () -> target.elements().get();
		Stream<IModelElementTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return target.equals(this.target) && targetStream.anyMatch(source::equals);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModelTrace target) {
        this.target = null;
    }

}