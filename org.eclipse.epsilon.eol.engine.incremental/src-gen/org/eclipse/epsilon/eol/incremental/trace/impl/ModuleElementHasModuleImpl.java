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

import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Module;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElementHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ModuleElementHasModule reference. 
 */
public class ModuleElementHasModuleImpl extends Feature implements ModuleElementHasModule {
    
    /** The source(s) of the reference */
    protected ModuleElement source;
    
    /** The target(s) of the reference */
    protected Module target;
    
    /**
     * Instantiates a new ModuleElementHasModule.
     *
     * @param source the source of the reference
     */
    public ModuleElementHasModuleImpl (ModuleElement source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Module get() {
        return target;
    }
    
    @Override
    public boolean create(Module target) {
        if (conflict(target)) {
            return false;
        }
        target.moduleElements().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Module target) {
        if (!related(target)) {
            return false;
        }
        target.moduleElements().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Module target) {
        boolean result = false;
        result |= get() != null;
        result |= target.moduleElements().isUnique() && target.moduleElements().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(Module target) {
  
        return target.equals(this.target) & target.moduleElements().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(Module target) {
        this.target = target;
    }
    
    @Override
    public void remove(Module target) {
        this.target = null;
    }

}