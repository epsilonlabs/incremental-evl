 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
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

import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelTypeHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of model reference. 
 */
public class ModelTypeHasModelImpl extends Feature implements ModelTypeHasModel {
    
    protected ModelType source;
    protected Model target;
    
    /**
     * Instantiates a new ModelTypeHasModel.
     *
     * @param source the source of the reference
     */
    public ModelTypeHasModelImpl (ModelType source) {
        super(true);
        this.source = source;
    }
    
    @Override
    public Model get() {
        return target;
    }
    
    @Override
    public void set(Model target) {
        this.target = target;
    }
    
    @Override
    public void remove(Model target) {
        this.target = null;
    }
    
    @Override
    public boolean conflict(Model  target) {
        boolean result = false;
        result |= get() != null;
        result |= target.types().get().contains(source);
        return result;
    }
    
    @Override
    public boolean related(Model target) {
  
        return target.equals(this.target) & target.types().get().contains(source);
    }
    
    @Override
    public boolean create(Model target) {
        if (conflict(target)) {
            return false;
        }
        if (related(target)) {
            return true;
        }
        target.types().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(Model target) {
        if (!related(target)) {
            return false;
        }
        target.types().remove(source);
        remove(target);
        return true;
    }

}