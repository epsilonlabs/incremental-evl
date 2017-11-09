 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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

import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElementHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of ModelElementHasModel reference. 
 */
public class ModelElementHasModelImpl extends Feature implements ModelElementHasModel {
    
    /** The source(s) of the reference */
    protected ModelElement source;
    
    /** The target(s) of the reference */
    protected Model target;
    
    /**
     * Instantiates a new ModelElementHasModel.
     *
     * @param source the source of the reference
     */
    public ModelElementHasModelImpl (ModelElement source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Model get() {
        return target;
    }
    
    @Override
    public boolean create(Model target) {
        if (conflict(target)) {
            return false;
        }
        target.elements().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Model target) {
        if (!related(target)) {
            return false;
        }
        target.elements().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(Model target) {
        boolean result = false;
        result |= get() != null;
        result |= target.elements().isUnique() && target.elements().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(Model target) {
  
        return target.equals(this.target) & target.elements().get().contains(source);
    }
    
    // PRIVATE API
    
    @Override
    public void set(Model target) {
        this.target = target;
    }
    
    @Override
    public void remove(Model target) {
        this.target = null;
    }

}