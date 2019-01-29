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
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region ContextTraceImports on begin **/
/** protected region ContextTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

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
    private Integer index;

    /**
     * The guard.
     */
    private final IGuardedElementTraceHasGuard guard;

    /**
     * * The execution context in which this module was executed. 
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
    public ContextTrace(String kind,
                        Integer index,
                        IModuleExecutionTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.kind = kind;
        this.index = index;
        if (!container.moduleElements().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.executionContext = new ContextModuleElementTraceHasExecutionContext(this);
        this.guard = new GuardedElementTraceHasGuard(this);
        this.constraints = new ContextTraceHasConstraints(this);

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
    public IContextModuleElementTraceHasExecutionContext executionContext() {
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
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (guardTrace == null) {
                guardTrace = this.guard.get();
            }
            if (guardTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guardTrace;
    }      
                  
    @Override
    public IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException {
        IExecutionContext executionContext = null;
        
        try {
            executionContext = new ExecutionContext(this);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (executionContext == null) {
            }
            if (executionContext == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ExecutionContext was "
                        + "duplicate but previous one was not found.");
            }
        }
        return executionContext;
    }      
                  
    @Override
    public IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException {
        IInvariantTrace invariantTrace = null;
        
        try {
            invariantTrace = new InvariantTrace(name, this);
            this.constraints().create(invariantTrace);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (invariantTrace == null) {
                Iterator<IInvariantTrace> it = this.constraints.get();
                while (it.hasNext()) {
                    IInvariantTrace item;
                    item = (IInvariantTrace) it.next();
        	        if (item.getName().equals(name)) {
        	            invariantTrace = item;
        	            break;
        	        }
                }
            }
            if (invariantTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested InvariantTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return invariantTrace;
    }      
                  
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("kind", getKind());
        result.put("index", getIndex());
        return result;
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
        if (!IncrementalUtils.equalIterators(executionContext.get(), other.executionContext.get())) {
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
        result = prime * result + ((executionContext.get() == null) ? 0 : IncrementalUtils.iteratorHashCode(executionContext.get()));
        return result;
    }
}
