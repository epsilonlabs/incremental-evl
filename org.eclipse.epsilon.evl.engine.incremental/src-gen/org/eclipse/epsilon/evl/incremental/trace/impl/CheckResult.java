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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region CheckResultImports on begin **/
/** protected region CheckResultImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ICheckResult. 
 */
@SuppressWarnings("unused") 
public class CheckResult implements ICheckResult {

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
    private final ICheckResultHasContext context;

    /**
     * Instantiates a new CheckResult. The CheckResult is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckResult(IExecutionContext context,
                       ICheckTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.result().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.context = new CheckResultHasContext(this);
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
    public ICheckResultHasContext context() {
        return context;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ICheckResult other) {
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
        if (!(obj instanceof CheckResult))
            return false;
        CheckResult other = (CheckResult) obj;
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
