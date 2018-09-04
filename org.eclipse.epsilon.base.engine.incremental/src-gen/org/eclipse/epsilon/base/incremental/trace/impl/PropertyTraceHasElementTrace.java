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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTraceHasElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IPropertyTraceHasElementTrace reference. 
 */
public class PropertyTraceHasElementTrace extends Feature implements IPropertyTraceHasElementTrace {
    
    /** The source(s) of the reference */
    protected IPropertyTrace source;
    
    /** The target(s) of the reference */
    protected IModelElementTrace target;
    
    /**
     * Instantiates a new IPropertyTraceHasElementTrace.
     *
     * @param source the source of the reference
     */
    public PropertyTraceHasElementTrace (IPropertyTrace source) {
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
        if (related(target)) {
            return false;
        }
        target.properties().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.properties().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        result |= this.target != null;
		Iterable<IPropertyTrace> iterable = () -> target.properties().get();
		Stream<IPropertyTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        result |= target.properties().isUnique() &&
        	targetStream.anyMatch(source::equals);
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
        
		Iterable<IPropertyTrace> iterable = () -> target.properties().get();
		Stream<IPropertyTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return target.equals(this.target) && targetStream.anyMatch(source::equals);
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