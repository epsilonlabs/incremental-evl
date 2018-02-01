 /*******************************************************************************
 * This file was automatically generated on: 2018-02-01.
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

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceHasContexts;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceHasContexts;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTrace implements IEvlModuleTrace {

    /** The id */
    private Object id;

    /** The source */
    private String source;

    /** The contexts relation */
    private final IEvlModuleTraceHasContexts contexts;

    /**
     * Instantiates a new EvlModuleTrace. The EvlModuleTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTrace(String source) throws TraceModelDuplicateRelation {
        this.source = source;
        this.contexts = new EvlModuleTraceHasContexts(this);
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
    public IEvlModuleTraceHasContexts contexts() {
        return contexts;
    }

    @Override
    public IContextTrace createContextTrace(String kind, Integer index, IModelElementTrace context) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        try {
            contextTrace = new ContextTrace(kind, index, context, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
            try {
                contextTrace = this.contexts.get().stream()
                    .filter(item -> item.getKind().equals(kind))
                    .filter(item -> item.getIndex().equals(index))
                    .filter(item -> item.context().get().equals(context))
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
        if (getSource() == null) {
            if (other.getSource() != null)
                return false;
        } else if (!getSource().equals(other.getSource()))
            return false;
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
