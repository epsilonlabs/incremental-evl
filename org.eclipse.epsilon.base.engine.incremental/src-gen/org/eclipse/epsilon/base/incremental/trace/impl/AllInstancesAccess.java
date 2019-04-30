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

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IAllInstancesAccess. 
 */
@SuppressWarnings("unused") 
public class AllInstancesAccess implements IAllInstancesAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The ofKind.
	 */
    private Boolean ofKind;

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
     * The type.
     */
    private final IAllInstancesAccessHasType type;

    /**
     * Instantiates a new AllInstancesAccess. The AllInstancesAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccess(Boolean ofKind,
                              IModuleElementTrace from,
                              IExecutionContext in,
                              IModelTypeTrace type,
                              IModuleExecutionTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.ofKind = ofKind;
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.module = new AccessHasModule(this);
        this.type = new AllInstancesAccessHasType(this);
        if (!this.type.create(type)) {
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
    public Boolean getOfKind() {
        return ofKind;
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
    public IAllInstancesAccessHasType type() {
        return type;
    }

    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("ofKind", getOfKind());
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IAllInstancesAccess other) {
        if (other == null) {
            return false;
        }
        Boolean ofKind = Boolean.valueOf(getOfKind());
        Boolean otherOfKind = Boolean.valueOf(other.getOfKind());
        if (ofKind == null) {
            if (otherOfKind != null)
                return false;
        } else if (!ofKind.equals(otherOfKind)) {
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
        if (!(obj instanceof AllInstancesAccess))
            return false;
        AllInstancesAccess other = (AllInstancesAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        if (module.get() == null) {
            if (other.module.get() != null)
                return false;
        }
        if (!module.get().equals(other.module.get())) {
            return false;
        }
        if (type.get() == null) {
            if (other.type.get() != null)
                return false;
        }
        if (!type.get().equals(other.type.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Boolean ofKind = Boolean.valueOf(getOfKind());
        result = prime * result + ((ofKind == null) ? 0 : ofKind.hashCode());
        result = prime * result + ((module.get() == null) ? 0 : module.get().hashCode());
        result = prime * result + ((type.get() == null) ? 0 : type.get().hashCode());
        return result;
    }
}
