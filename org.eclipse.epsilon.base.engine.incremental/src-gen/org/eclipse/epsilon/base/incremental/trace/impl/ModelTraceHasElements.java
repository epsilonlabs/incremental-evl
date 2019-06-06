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
import java.util.Queue;
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceHasElements;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModelTraceHasElements reference. 
 */
@SuppressWarnings("unused") 
public class ModelTraceHasElements extends Feature implements IModelTraceHasElements {
    
    /** The source(s) of the reference */
    protected IModelTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IModelElementTrace> target =  new ConcurrentSetQueue<IModelElementTrace>();
    
    /**
     * Instantiates a new IModelTraceHasElements.
     *
     * @param source the source of the reference
     */
    public ModelTraceHasElements (IModelTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IModelElementTrace> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IModelElementTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelElementTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.modelTrace().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        target.modelTrace().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        result |= target.modelTrace().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target) && source.equals(target.modelTrace().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelElementTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        this.target.remove(target);
    }

}