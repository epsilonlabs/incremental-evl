 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
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

import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region AllInstancesAccessImports on begin **/
/** protected region AllInstancesAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccessHasExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccessHasType;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.AccessHasExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.AllInstancesAccessHasType;

/**
 * Implementation of IAllInstancesAccess. 
 */
public class AllInstancesAccess implements IAllInstancesAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The ofKind.
	 */
    private boolean ofKind;

    /**
     * The executionTrace.
     */
    private final IAccessHasExecutionTrace executionTrace;

    /**
     * The type.
     */
    private final IAllInstancesAccessHasType type;

    /**
     * Instantiates a new AllInstancesAccess. The AllInstancesAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public AllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        this.ofKind = ofKind;
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@3806dcd0 (name: type) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.type = new AllInstancesAccessHasType(this);
        if (!this.type.create(type)) {
            throw new TraceModelDuplicateRelation();
        }
        this.executionTrace = new AccessHasExecutionTrace(this);
        if (!this.executionTrace.create(executionTrace)) {
            throw new TraceModelDuplicateRelation();
        }

        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(Object value) {
        this.id = value;
    }   
     
    @Override
    public boolean getOfKind() {
        return ofKind;
    }
    
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        return executionTrace;
    }

    @Override
    public IAllInstancesAccessHasType type() {
        return type;
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
        result = prime * result + ((type.get() == null) ? 0 : type.get().hashCode());
        return result;
    }
}
