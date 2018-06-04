 /*******************************************************************************
 * This file was automatically generated on: 2018-05-31.
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

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ExecutionContextImports on begin **/

/** protected region ExecutionContextImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementVariable;

/**
 * Implementation of IExecutionContext. 
 */
public class ExecutionContext implements IExecutionContext {

    /**
	 * The id.
	 */
    private Object id;

    /**
     * The variables that make up the context.
     */
    private final IExecutionContextHasContextVariables contextVariables;

    /**
     * Instantiates a new ExecutionContext. The ExecutionContext is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ExecutionContext(IContextModuleElementTrace container) throws TraceModelDuplicateRelation {
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@75484128 (name: contextVariables) (ordered: false, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
        this.contextVariables = new ExecutionContextHasContextVariables(this);

        if (!container.executionContext().create(this)) {
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
    public IExecutionContextHasContextVariables contextVariables() {
        return contextVariables;
    }


    @Override
    public IModelElementVariable createModelElementVariable(String name, IModelElementTrace value) throws EolIncrementalExecutionException {
        IModelElementVariable modelElementVariable = null;
        try {
            modelElementVariable = new ModelElementVariable(name, value, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelElementVariable != null) {
    	        return modelElementVariable;
    	    }
            try {
                modelElementVariable = this.contextVariables.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .filter(item -> item.value().get().equals(value))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElementVariable was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelElementVariable;
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
        if (!contextVariables.get().equals(other.contextVariables.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contextVariables.get() == null) ? 0 : contextVariables.get().hashCode());
        return result;
    }
}
