 /*******************************************************************************
 * This file was automatically generated on: 2019-01-24.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region GuardResultImports on begin **/
/** protected region GuardResultImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IGuardResult. 
 */
public class GuardResult implements IGuardResult {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The value.
	 */
    private Boolean value;

    /**
     * The context.
     */
    private final IGuardResultHasContext context;

    /**
     * Instantiates a new GuardResult. The GuardResult is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardResult(IExecutionContext context,
                       IGuardTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.result().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.context = new GuardResultHasContext(this);
        if (!this.context.create(context)) {
            throw new TraceModelDuplicateElement();
        }

    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
        this.id = value;
    }   
     
    @Override
    public Boolean getValue() {
        return value;
    }
    
    
    @Override
    public void setValue(boolean value) {
        this.value = value;
    }   
     
    @Override
    public IGuardResultHasContext context() {
        return context;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IGuardResult other) {
        if (other == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GuardResult))
            return false;
        GuardResult other = (GuardResult) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return result;
    }
}
