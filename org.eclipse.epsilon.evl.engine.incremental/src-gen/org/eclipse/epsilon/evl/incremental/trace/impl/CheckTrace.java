 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
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

import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region CheckTraceImports on begin **/
/** protected region CheckTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IInContextModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.InContextModuleElementTraceHasParentTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.CheckTraceHasInvariant;

/**
 * Implementation of ICheckTrace. 
 */
public class CheckTrace implements ICheckTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private final IModuleElementTraceHasAccesses accesses;

    /**
     * The invariant.
     */
    private final ICheckTraceHasInvariant invariant;

    /**
     * Instantiates a new CheckTrace. The CheckTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckTrace(IInvariantTrace container) throws TraceModelDuplicateRelation {
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@616af88b (name: invariant) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.invariant = new CheckTraceHasInvariant(this);
        this.accesses = new ModuleElementTraceHasAccesses(this);

        if (!container.check().create(this)) {
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
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public ICheckTraceHasInvariant invariant() {
        return invariant;
    }

    @Override
    public IInContextModuleElementTraceHasParentTrace parentTrace() {
        /** protected region parentTrace on begin **/
        return null;
        /** protected region parentTrace end **/
    }


    @Override
    public boolean sameIdentityAs(final ICheckTrace other) {
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
        if (!(obj instanceof CheckTrace))
            return false;
        CheckTrace other = (CheckTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (invariant.get() == null) {
            if (other.invariant.get() != null)
                return false;
        }
        if (!invariant.get().equals(other.invariant.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invariant.get() == null) ? 0 : invariant.get().hashCode());
        return result;
    }
}
