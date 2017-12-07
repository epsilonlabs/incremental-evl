 /*******************************************************************************
 * This file was automatically generated on: 2017-12-07.
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

import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccessHasModelElement;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IElementAccessHasModelElement reference. 
 */
public class ElementAccessHasModelElement extends Feature implements IElementAccessHasModelElement {
    
    /** The source(s) of the reference */
    protected IElementAccess source;
    
    /** The target(s) of the reference */
    protected IModelElementTrace target;
    
    /**
     * Instantiates a new IElementAccessHasModelElement.
     *
     * @param source the source of the reference
     */
    public ElementAccessHasModelElement (IElementAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IModelElementTrace get() {
        return target;
    }
    
    @Override
    public boolean create(IModelElementTrace target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelElementTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelElementTrace target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModelElementTrace target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModelElementTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IModelElementTrace target) {
        this.target = null;
    }

}