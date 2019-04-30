 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModelElementTraceHasProperties reference. 
 */
@SuppressWarnings("unused") 
public class ModelElementTraceHasProperties extends Feature implements IModelElementTraceHasProperties {
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IPropertyTrace> target =  new ConcurrentSetQueue<IPropertyTrace>();
    
    /**
     * Instantiates a new IModelElementTraceHasProperties.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasProperties (IModelElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IPropertyTrace> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IPropertyTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IPropertyTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.elementTrace().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IPropertyTrace target) {
        if (!related(target)) {
            return false;
        }
        target.elementTrace().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IPropertyTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        result |= target.elementTrace().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IPropertyTrace target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target) && source.equals(target.elementTrace().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IPropertyTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IPropertyTrace target) {
        this.target.remove(target);
    }

}