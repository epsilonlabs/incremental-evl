 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region CheckTraceImports on begin **/
/** protected region CheckTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of ICheckTrace. 
 */
public class CheckTrace implements ICheckTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
     * The contextModuleElement.
     */
    private final IInContextModuleElementTraceHasContextModuleElement contextModuleElement;

    /**
     * The invariant.
     */
    private final ICheckTraceHasInvariant invariant;

    /**
     * The result.
     */
    private final ICheckTraceHasResult result;

    /**
     * Instantiates a new CheckTrace. The CheckTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public CheckTrace(IInvariantTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.check().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.invariant = new CheckTraceHasInvariant(this);
        this.contextModuleElement = new InContextModuleElementTraceHasContextModuleElement(this);
        this.result = new CheckTraceHasResult(this);


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
    public IInContextModuleElementTraceHasContextModuleElement contextModuleElement() {
        return contextModuleElement;
    }

    @Override
    public ICheckTraceHasInvariant invariant() {
        return invariant;
    }

    @Override
    public ICheckTraceHasResult result() {
        return result;
    }

    @Override
    public ICheckResult getOrCreateCheckResult(IExecutionContext context) throws EolIncrementalExecutionException {
        ICheckResult checkResult = null;
        try {
            checkResult = new CheckResult(context, this);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
    	    if (checkResult != null) {
    	        return checkResult;
    	    }
            Iterator<ICheckResult> it = this.result.get();
            while (it.hasNext()) {
            	ICheckResult item;
                item = (ICheckResult) it.next();
    			if (item.context().get().equals(context)) {
    				checkResult = item;
    				break;
    			}
    		}
    		if (checkResult == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested CheckResult was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return checkResult;
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
