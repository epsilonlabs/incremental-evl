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
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IContextModuleElementTraceHasExecutionContext reference. 
 */
@SuppressWarnings("unused") 
public class ContextModuleElementTraceHasExecutionContext extends Feature implements IContextModuleElementTraceHasExecutionContext {
    
    /** The source(s) of the reference */
    protected IContextModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IExecutionContext> target =  new ConcurrentLinkedQueue<IExecutionContext>();
    
    /**
     * Instantiates a new IContextModuleElementTraceHasExecutionContext.
     *
     * @param source the source of the reference
     */
    public ContextModuleElementTraceHasExecutionContext (IContextModuleElementTrace source) {
        super(false);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    
    public Iterator<IExecutionContext> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IExecutionContext target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IExecutionContext exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionContext target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionContext target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IExecutionContext target) {
        this.target.remove(target);
    }

}