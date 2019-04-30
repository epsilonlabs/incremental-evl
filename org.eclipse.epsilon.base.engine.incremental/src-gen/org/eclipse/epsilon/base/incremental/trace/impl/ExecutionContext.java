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
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region ExecutionContextImports on begin **/
/** protected region ExecutionContextImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IExecutionContext. 
 */
@SuppressWarnings("unused") 
public class ExecutionContext implements IExecutionContext {

    /**
	 * The id.
	 */
    private Object id;

    /**
     * * The variables that make up the context.
     */
    private final IExecutionContextHasContextVariables contextVariables;

    /**
     * Instantiates a new ExecutionContext. The ExecutionContext is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ExecutionContext(IContextModuleElementTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.executionContext().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.contextVariables = new ExecutionContextHasContextVariables(this);

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
    public IExecutionContextHasContextVariables contextVariables() {
        return contextVariables;
    }

    @Override
    public IModelElementVariable getOrCreateModelElementVariable(String name, IModelElementTrace value) throws EolIncrementalExecutionException {
        IModelElementVariable modelElementVariable = null;
        
        try {
            modelElementVariable = new ModelElementVariable(name, value, this);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (modelElementVariable == null) {
                // Find the matching element
                Iterator<IModelElementVariable> it = this.contextVariables.get();
                while (it.hasNext()) {
                    IModelElementVariable item;
                    item = (IModelElementVariable) it.next();
        	        if (item.getName().equals(name) &&
        	            item.value().get().equals(value)) {
        	            modelElementVariable = item;
        	            break;
        	        }
                }
            }
            if (modelElementVariable == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElementVariable was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelElementVariable;
    }      
                  
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public boolean sameIdentityAs(final IExecutionContext other) {
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
        if (!(obj instanceof ExecutionContext))
            return false;
        ExecutionContext other = (ExecutionContext) obj;
        if (!sameIdentityAs(other))
            return false;
        if (contextVariables.get() == null) {
            if (other.contextVariables.get() != null)
                return false;
        }
        if (!IncrementalUtils.equalUniqueIterators(contextVariables.get(), other.contextVariables.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contextVariables.get() == null) ? 0 : IncrementalUtils.iteratorHashCode(contextVariables.get()));
        return result;
    }
}
