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
@SuppressWarnings("unused") 
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
     * The module.
     */
    private final IAccessHasModule module;

    /**
     * The from.
     */
    private final IAccessHasFrom from;

    /**
     * The in.
     */
    private final IAccessHasIn in;

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
    public SatisfiesAccess(IModuleElementTrace from,
                           IExecutionContext in,
                           IModelElementTrace modelElement,
                           IModuleExecutionTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.modelElement = new SatisfiesAccessHasModelElement(this);
        this.module = new AccessHasModule(this);
        if (!this.modelElement.create(modelElement)) {
            throw new TraceModelDuplicateElement();
        }
        this.from = new AccessHasFrom(this);
        if (!this.from.create(from)) {
            throw new TraceModelDuplicateElement();
        }
        this.in = new AccessHasIn(this);
        if (!this.in.create(in)) {
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
    public IAccessHasModule module() {
        return module;
    }

    @Override
    public IAccessHasFrom from() {
        return from;
    }

    @Override
    public IAccessHasIn in() {
        return in;
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
        if (modelElement.get() == null) {
            if (other.modelElement.get() != null)
                return false;
        }
        if (!modelElement.get().equals(other.modelElement.get())) {
            return false;
        }
        if (module.get() == null) {
            if (other.module.get() != null)
                return false;
        }
        if (!module.get().equals(other.module.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modelElement.get() == null) ? 0 : modelElement.get().hashCode());
        result = prime * result + ((module.get() == null) ? 0 : module.get().hashCode());
        return result;
    }
}
