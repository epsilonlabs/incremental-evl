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
package org.eclipse.epsilon.base.incremental.trace;

import java.util.Map;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * An ExecutionContext is represented by the set of ModelElementVariables and
 * Accesses that occured during execution.
 * 
 * For example, in EVL, the ModelElementVariables would contain the ‘self’ (model
 * element of the Context type); in in ETL this would be the input (and output
 * variables). 
 */
public interface IExecutionContext extends IIdElement {

    /**
     * Returns the value of the '<em><b>Context Variables</b></em>' reference.
     * The variables that make up the context.
     * @return the value of the '<em>Context Variables</em>' reference.
     */
    IExecutionContextHasContextVariables contextVariables();
                
    /**
     * Returns the value of the '<em><b>accesses</b></em>' reference.
     * The different accesses that where recorded during execution.
     * @return the value of the '<em>accesses</em>' reference.
     */
    IExecutionContextHasAccesses accesses();
                

 
    /**
     * ExecutionContext has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IExecutionContext other);
    
    /** The ModelElementVariable Factory. */
    IModelElementVariable getOrCreateModelElementVariable(String name, IModelElementTrace value) throws EolIncrementalExecutionException;       
            
    /** The Access Factory. */        
    public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException;
          

}
