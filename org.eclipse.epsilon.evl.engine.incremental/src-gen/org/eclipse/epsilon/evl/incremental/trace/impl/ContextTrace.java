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

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
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

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The kind.
	 */
    private String kind;

    /**
	 * The index.
	 */
    private int index;

    /**
     * The guard.
     */
    private final IGuardedElementTraceHasGuard guard;

    /**
     * * The different accesses that where recorded during execution for this particular 
       * module element.
     */
    private final IModuleElementTraceHasAccesses accesses;

    /**
     * * The execution context in which this module was executed. This is constitued
       * by the variables that define the context of the module element. In EVL this would
       * be ‘self’ (model element of the Context type) in ETL this would be the input (and 
       * output variables). 
     */
    private final IContextModuleElementTraceHasExecutionContext executionContext;

    /**
     * The constraints.
     */
    private final IContextTraceHasConstraints constraints;

    /**
     * Instantiates a new ContextTrace. The ContextTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTrace(String kind, int index, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        this.kind = kind;
        this.index = index;
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@2fbb629d (name: executionContext) (ordered: true, unique: true, lowerBound: 0, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
        this.executionContext = new ContextModuleElementTraceHasExecutionContext(this);
        this.guard = new GuardedElementTraceHasGuard(this);
        this.accesses = new ModuleElementTraceHasAccesses(this);
        this.constraints = new ContextTraceHasConstraints(this);

        if (!container.moduleElements().create(this)) {
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
    public String getKind() {
        return kind;
    }
    
    @Override
    public int getIndex() {
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
    public IContextModuleElementTraceHasExecutionContext executionContext() {
        return executionContext;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        return constraints;
    }

    @Override
    public IGuardTrace createGuardTrace() throws EolIncrementalExecutionException {
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
    public IExecutionContext createExecutionContext() throws EolIncrementalExecutionException {
        IExecutionContext executionContext = null;
        try {
            executionContext = new ExecutionContext(this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (executionContext != null) {
    	        return executionContext;
    	    }
            executionContext = this.executionContext.get();
            if (executionContext  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ExecutionContext was "
                        + "duplicate but previous one was not found.");
            }
        }
        return executionContext;
    }      
                  
    @Override
    public IInvariantTrace createInvariantTrace(String name) throws EolIncrementalExecutionException {
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
                    .filter(item -> item.getName() == name)
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
        Integer index = Integer.valueOf(getIndex());
        Integer otherIndex = Integer.valueOf(other.getIndex());
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
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kind == null) ? 0 : kind.hashCode());
        Integer index = Integer.valueOf(getIndex());
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        result = prime * result + ((executionContext.get() == null) ? 0 : executionContext.get().hashCode());
        return result;
    }
}
