 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.eol.incremental.trace.Module;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleHasModules;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ModuleHasModules reference. 
 */
public class ModuleHasModulesImpl extends Feature implements ModuleHasModules {
    
    /** The source(s) of the reference */
    protected Module source;
    
    /** The target(s) of the reference */
    protected Queue<ModuleElement> target =  new ConcurrentLinkedQueue<ModuleElement>();
    
    /**
     * Instantiates a new ModuleHasModules.
     *
     * @param source the source of the reference
     */
    public ModuleHasModulesImpl (Module source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<ModuleElement> get() {
        return target;
    }
    
    @Override
    public boolean create(ModuleElement target) {
        if (conflict(target)) {
            return false;
        }
        target.module().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(ModuleElement target) {
        if (!related(target)) {
            return false;
        }
        target.module().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(ModuleElement target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.module().get() != null;
        return result;
    }
    
    @Override
    public boolean related(ModuleElement target) {
  
        return get().contains(target) & source.equals(target.module().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(ModuleElement target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(ModuleElement target) {
        this.target.remove(target);
    }

}