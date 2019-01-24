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
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IExecutionContextHasContextVariables reference. 
 */
public class ExecutionContextHasContextVariables extends Feature implements IExecutionContextHasContextVariables {
    
    /** The source(s) of the reference */
    protected IExecutionContext source;
    
    /** The target(s) of the reference */
    protected Set<IModelElementVariable> target =  ConcurrentHashMap.newKeySet();
    
    /**
     * Instantiates a new IExecutionContextHasContextVariables.
     *
     * @param source the source of the reference
     */
    public ExecutionContextHasContextVariables (IExecutionContext source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IModelElementVariable> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IModelElementVariable target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelElementVariable exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementVariable target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementVariable target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelElementVariable target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelElementVariable target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModelElementVariable target) {
        this.target.remove(target);
    }

}