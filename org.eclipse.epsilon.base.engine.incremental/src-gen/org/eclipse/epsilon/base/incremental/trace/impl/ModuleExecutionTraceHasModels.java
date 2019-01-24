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
import org.eclipse.epsilon.base.incremental.trace.util.ConcurrentSetQueue;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionTraceHasModels reference. 
 */
public class ModuleExecutionTraceHasModels extends Feature implements IModuleExecutionTraceHasModels {
    
    /** The source(s) of the reference */
    protected IModuleExecutionTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IModelAccess> target =  new ConcurrentSetQueue<IModelAccess>();
    
    /**
     * Instantiates a new IModuleExecutionTraceHasModels.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionTraceHasModels (IModuleExecutionTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IModelAccess> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IModelAccess target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IModelAccess exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelAccess target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelAccess target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IModelAccess target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IModelAccess target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModelAccess target) {
        this.target.remove(target);
    }

}