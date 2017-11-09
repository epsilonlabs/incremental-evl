 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Context;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Module;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElementHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementHasModuleImpl;
import org.eclipse.epsilon.evl.incremental.trace.ContextHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.ContextHasContext;
import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElementHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextHasConstraintsImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextHasContextImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementHasGuardImpl;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantImpl;

/**
 * Implementation of Context. 
 */
public class ContextImpl implements Context {

    /** The id */
    private Object id;

    /** The guard relation */
    private final GuardedElementHasGuard guard;

    /** The module relation */
    private final ModuleElementHasModule module;

    /** The constraints relation */
    private final ContextHasConstraints constraints;

    /** The context relation */
    private final ContextHasContext context;

    /**
     * Instantiates a new Context. The Context is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextImpl(Module container) throws TraceModelDuplicateRelation {
        this.guard = new GuardedElementHasGuardImpl(this);
        this.module = new ModuleElementHasModuleImpl(this);
        this.constraints = new ContextHasConstraintsImpl(this);
        this.context = new ContextHasContextImpl(this);
        if (!container.modules().create(this)) {
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
    public GuardedElementHasGuard guard() {
        return guard;
    }

    @Override
    public ModuleElementHasModule module() {
        return module;
    }

    @Override
    public ContextHasConstraints constraints() {
        return constraints;
    }

    @Override
    public ContextHasContext context() {
        return context;
    }

    @Override
    public Guard createGuard() throws EolIncrementalExecutionException {
            try {
                return new GuardImpl(this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            Guard guard = null;
            guard = this.guard.get();
            if (guard == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Guard was "
                        + "duplicate but previous one was not found.");
            }
            return guard;
    }      
                  
    @Override
    public Invariant createInvariant() throws EolIncrementalExecutionException {
            try {
                return new InvariantImpl(this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            Invariant invariant = null;
            
            try {
                invariant = this.constraints.get().stream()
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Invariant was "
                        + "duplicate but previous one was not found.");
            }
            return invariant;
    }      
                  
    @Override
    public boolean sameIdentityAs(final Context other) {
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
        if (!(obj instanceof ContextImpl))
            return false;
        ContextImpl other = (ContextImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        if (module.get() == null) {
            if (other.module.get() != null)
                return false;
        } else if (!module.get().equals(other.module.get()))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((module.get() == null) ? 0 : module.get().hashCode());
        return result;
    }

}
