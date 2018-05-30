 /*******************************************************************************
 * This file was automatically generated on: 2018-05-04.
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

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContextHasRules;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasRules;

/**
 * Implementation of IExecutionContext. 
 */
public class ExecutionContext implements IExecutionContext {

    /** The id */
    private Object id;

    /** The contextVariables relation */
    private final IExecutionContextHasContextVariables contextVariables;

    /** The rules relation */
    private final IExecutionContextHasRules rules;

    /**
     * Instantiates a new ExecutionContext. The ExecutionContext is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ExecutionContext() throws TraceModelDuplicateRelation {
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@19b5769d (name: contextVariables) (ordered: false, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.contextVariables = new ExecutionContextHasContextVariables(this);
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@1f3e3dc0 (name: rules) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.rules = new ExecutionContextHasRules(this);

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
    public IExecutionContextHasRules rules() {
        return rules;
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
