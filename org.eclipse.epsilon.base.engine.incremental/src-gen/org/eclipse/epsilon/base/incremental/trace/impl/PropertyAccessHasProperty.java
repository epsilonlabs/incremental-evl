 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccessHasProperty;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IPropertyAccessHasProperty reference. 
 */
@SuppressWarnings("unused") 
public class PropertyAccessHasProperty extends Feature implements IPropertyAccessHasProperty {
    
    /** The source(s) of the reference */
    protected IPropertyAccess source;
    
    /** The target(s) of the reference */
    protected IPropertyTrace target;
    
    /**
     * Instantiates a new IPropertyAccessHasProperty.
     *
     * @param source the source of the reference
     */
    public PropertyAccessHasProperty (IPropertyAccess source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IPropertyTrace get() {
        return target;
    }
    

    @Override
    public boolean create(IPropertyTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IPropertyTrace exists");
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IPropertyTrace target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IPropertyTrace target) {
        boolean result = false;
        result |= this.target != null;
        return result;
    }
    
    @Override
    public boolean related(IPropertyTrace target) {
    	if (target == null) {
			return false;
		}
		return target.equals(this.target);
	}
        
    
    // PRIVATE API
    
    @Override
    public void set(IPropertyTrace target) {
        this.target = target;
    }
    
    @Override
    public void remove(IPropertyTrace target) {
        this.target = null;
    }

}