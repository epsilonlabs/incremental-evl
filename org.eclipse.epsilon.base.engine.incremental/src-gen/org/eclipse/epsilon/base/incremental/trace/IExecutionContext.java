 /*******************************************************************************
 * This file was automatically generated on: 2018-06-04.
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

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;    

/**
 * The ExecutionContext Interface.
 */
public interface IExecutionContext extends IIdElement {

    /**
     * Returns the value of the '<em><b>Context Variables</b></em>' reference.
     * The variables that make up the context.
     * @return the value of the '<em>Context Variables</em>' reference.
     */
    IExecutionContextHasContextVariables contextVariables();
                
 
    /**
     * ExecutionContext has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IExecutionContext other);
    
    /** The ModelElementVariable Factory. */
    IModelElementVariable createModelElementVariable(String name, IModelElementTrace value) throws EolIncrementalExecutionException;       
   
}
