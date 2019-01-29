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
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region SatisfiesAccessImports on begin **/
/** protected region SatisfiesAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ISatisfiesAccess. 
 */
public class SatisfiesAccess implements ISatisfiesAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The all.
	 */
    private Boolean all;

    /**
     * The from.
     */
    private final IAccessHasFrom from;

    /**
     * The modelElement.
     */
    private final ISatisfiesAccessHasModelElement modelElement;

    /**
     * The satisfiedInvariants.
     */
    private final ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants;

    /**
     * Instantiates a new SatisfiesAccess. The SatisfiesAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public SatisfiesAccess(IModelElementTrace modelElement,
                           IExecutionContext container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.from = new AccessHasFrom(this);
        this.modelElement = new SatisfiesAccessHasModelElement(this);
        if (!this.modelElement.create(modelElement)) {
            throw new TraceModelDuplicateElement();
        }
        this.satisfiedInvariants = new SatisfiesAccessHasSatisfiedInvariants(this);

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
    public Boolean getAll() {
        return all;
    }
    
    
    @Override
    public void setAll(boolean value) {
        this.all = value;
    }   
     
    @Override
    public IAccessHasFrom from() {
        return from;
    }

    @Override
    public ISatisfiesAccessHasModelElement modelElement() {
        return modelElement;
    }

    @Override
    public ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants() {
        return satisfiedInvariants;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final ISatisfiesAccess other) {
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
        if (!(obj instanceof SatisfiesAccess))
            return false;
        SatisfiesAccess other = (SatisfiesAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        if (from.get() == null) {
            if (other.from.get() != null)
                return false;
        }
        if (!from.get().equals(other.from.get())) {
            return false;
        }
        if (modelElement.get() == null) {
            if (other.modelElement.get() != null)
                return false;
        }
        if (!modelElement.get().equals(other.modelElement.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((from.get() == null) ? 0 : from.get().hashCode());
        result = prime * result + ((modelElement.get() == null) ? 0 : modelElement.get().hashCode());
        return result;
    }
}
