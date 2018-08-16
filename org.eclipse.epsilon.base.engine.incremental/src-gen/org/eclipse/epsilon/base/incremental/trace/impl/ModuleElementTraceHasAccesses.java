 /*******************************************************************************
 * This file was automatically generated on: 2018-08-16.
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
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IModuleElementTraceHasAccesses reference. 
 */
public class ModuleElementTraceHasAccesses extends Feature implements IModuleElementTraceHasAccesses {
    
    /** The source(s) of the reference */
    protected IModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected Set<IAccess> target =  ConcurrentHashMap.newKeySet();
    
    /**
     * Instantiates a new IModuleElementTraceHasAccesses.
     *
     * @param source the source of the reference
     */
    public ModuleElementTraceHasAccesses (IModuleElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Iterator<IAccess> get() {
    	return target.iterator();
    }
    

    @Override
    public boolean create(IAccess target) {
        if (conflict(target)) {
            return false;
        }
        target.executionTrace().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IAccess target) {
        if (!related(target)) {
            return false;
        }
        target.executionTrace().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IAccess target) {
        boolean result = false;
        if (isUnique) {
            result |= this.target.contains(target);
        }
        result |= target.executionTrace().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IAccess target) {
    	if (target == null) {
			return false;
		}
		return this.target.contains(target) && source.equals(target.executionTrace().get());
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