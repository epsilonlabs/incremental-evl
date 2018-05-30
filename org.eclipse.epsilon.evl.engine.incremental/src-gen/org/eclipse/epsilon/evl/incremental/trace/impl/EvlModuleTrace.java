 /*******************************************************************************
 * This file was automatically generated on: 2018-04-26.
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

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleTraceHasExecutionContexts;
import org.eclipse.epsilon.base.incremental.trace.IModuleTraceHasRuleTraces;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleTraceHasExecutionContexts;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleTraceHasRuleTraces;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTrace implements IEvlModuleTrace {

    /** The id */
    private Object id;

    /** The source */
    private String source;

    /** The ruleTraces relation */
    private final IModuleTraceHasRuleTraces ruleTraces;

    /** The executionContexts relation */
    private final IModuleTraceHasExecutionContexts executionContexts;

    /**
     * Instantiates a new EvlModuleTrace. The EvlModuleTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTrace(String source) throws TraceModelDuplicateRelation {
        this.source = source;
        this.ruleTraces = new ModuleTraceHasRuleTraces(this);
        this.executionContexts = new ModuleTraceHasExecutionContexts(this);
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
    public String getSource() {
        return source;
    }
    
    @Override
    public IModuleTraceHasRuleTraces ruleTraces() {
        return ruleTraces;
    }

    @Override
    public IModuleTraceHasExecutionContexts executionContexts() {
        return executionContexts;
    }


    @Override
    public IContextTrace createContextTrace(String kind, Integer index, IExecutionContext executionContext) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        try {
            contextTrace = new ContextTrace(kind, index, executionContext, this);
            
            this.ruleTraces().create(contextTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
            try {
                contextTrace = this.ruleTraces.get().stream()
                    .filter(t -> t instanceof IContextTrace)
                    .map(IContextTrace.class::cast)
                    .filter(item -> item.getKind().equals(kind))
                    .filter(item -> item.getIndex().equals(index))
                    .filter(item -> item.executionContext().get().equals(executionContext))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return contextTrace;
    }      
            
                  
    @Override
    public boolean sameIdentityAs(final IEvlModuleTrace other) {
        if (other == null) {
            return false;
        }
        String source = getSource();
        String otherSource = other.getSource();
        if (source == null) {
            if (otherSource != null)
                return false;
        } else if (!source.equals(otherSource)) {
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
        if (!(obj instanceof EvlModuleTrace))
            return false;
        EvlModuleTrace other = (EvlModuleTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }
}
