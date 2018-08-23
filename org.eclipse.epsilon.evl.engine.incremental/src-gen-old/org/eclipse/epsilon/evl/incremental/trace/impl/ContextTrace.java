 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IRuleTraceHasModule;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.RuleTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.RuleTraceHasModule;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTrace;

/**
 * Implementation of IContextTrace. 
 */
public class ContextTrace implements IContextTrace {

    /** The id */
    private Object id;

    /** The kind */
    private String kind;

    /** The index */
    private Integer index;

    /** The guard relation */
    private final IGuardedElementTraceHasGuard guard;

    /** The accesses relation */
    private final IModuleElementTraceHasAccesses accesses;

    /** The module relation */
    private final IRuleTraceHasModule module;

    /** The executionContext relation */
    private final IRuleTraceHasExecutionContext executionContext;

    /** The constraints relation */
    private final IContextTraceHasConstraints constraints;

    /**
     * Instantiates a new ContextTrace. The ContextTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTrace(String kind, Integer index, IModuleTrace module, IExecutionContext executionContext) throws TraceModelDuplicateRelation {
        this.kind = kind;
        this.index = index;
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@18766d70 (name: executionContext) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.executionContext = new RuleTraceHasExecutionContext(this);
        if (!this.executionContext.create(executionContext)) {
            throw new TraceModelDuplicateRelation();
        }
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@227793be (name: module) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.module = new RuleTraceHasModule(this);
        if (!this.module.create(module)) {
            throw new TraceModelDuplicateRelation();
        }
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@2b8d49f5 (name: guard) (ordered: true, unique: true, lowerBound: 0, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
        this.guard = new GuardedElementTraceHasGuard(this);
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@4044e6e1 (name: accesses) (ordered: false, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: false)
        this.accesses = new ModuleElementTraceHasAccesses(this);
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@58414a22 (name: constraints) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: false)
        this.constraints = new ContextTraceHasConstraints(this);

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
    public String getKind() {
        return kind;
    }
    
    @Override
    public Integer getIndex() {
        return index;
    }
    
    @Override
    public IGuardedElementTraceHasGuard guard() {
        return guard;
    }

    @Override
    public IModuleElementTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IRuleTraceHasModule module() {
        return module;
    }

    @Override
    public IRuleTraceHasExecutionContext executionContext() {
        return executionContext;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        return constraints;
    }


    @Override
    public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {
        IGuardTrace guardTrace = null;
        try {
            guardTrace = new GuardTrace(this);
            
            this.guard().create(guardTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (guardTrace != null) {
    	        return guardTrace;
    	    }
            guardTrace = this.guard.get();
            if (guardTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guardTrace;
    }      
                  
    @Override
    public IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException {
        IInvariantTrace invariantTrace = null;
        try {
            invariantTrace = new InvariantTrace(name, this);
            
            this.constraints().create(invariantTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (invariantTrace != null) {
    	        return invariantTrace;
    	    }
            try {
                invariantTrace = this.constraints.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested InvariantTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return invariantTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IContextTrace other) {
        if (other == null) {
            return false;
        }
        String kind = getKind();
        String otherKind = other.getKind();
        if (kind == null) {
            if (otherKind != null)
                return false;
        } else if (!kind.equals(otherKind)) {
            return false;
        }
        Integer index = getIndex();
        Integer otherIndex = other.getIndex();
        if (index == null) {
            if (otherIndex != null)
                return false;
        } else if (!index.equals(otherIndex)) {
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
        if (!(obj instanceof ContextTrace))
            return false;
        ContextTrace other = (ContextTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (executionContext.get() == null) {
            if (other.executionContext.get() != null)
                return false;
        }
        if (!executionContext.get().equals(other.executionContext.get())) {
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
        result = prime * result + ((kind == null) ? 0 : kind.hashCode());
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        result = prime * result + ((executionContext.get() == null) ? 0 : executionContext.get().hashCode());
        result = prime * result + ((module.get() == null) ? 0 : module.get().hashCode());
        return result;
    }
}
