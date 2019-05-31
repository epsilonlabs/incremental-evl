 /*******************************************************************************
 * This file was automatically generated on: 2019-05-31.
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
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleExecutionTraceHasAccesses reference. 
 */
@SuppressWarnings("unused") 
public class ModuleExecutionTraceHasAccesses extends Feature implements IModuleExecutionTraceHasAccesses {
    
    /** The source(s) of the reference */
    protected IModuleExecutionTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IAccess> target =  new ConcurrentLinkedQueue<IAccess>();
    
    /**
     * Instantiates a new IModuleExecutionTraceHasAccesses.
     *
     * @param source the source of the reference
     */
    public ModuleExecutionTraceHasAccesses (IModuleExecutionTrace source) {
        super(false);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IAccess> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IAccess target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IAccess exists");
        }
        if (related(target)) {
            return false;
        }
        target.module().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IAccess target) {
        if (!related(target)) {
            return false;
        }
        target.module().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IAccess target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        result |= target.module().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target) && source.equals(target.module().get());
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IAccess target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IAccess target) {
        this.target.remove(target);
    }

}