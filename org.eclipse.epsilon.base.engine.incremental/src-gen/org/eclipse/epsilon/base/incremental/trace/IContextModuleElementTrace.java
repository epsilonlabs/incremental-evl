 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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
 * A module element that is executed in a context (e.g. contex operation, Etl rule, 
 * etc.).
 */
public interface IContextModuleElementTrace extends IModuleElementTrace {

    /**
     * Returns the value of the '<em><b>Execution Context</b></em>' reference.
     * The execution context in which this module was executed. This is constitued
     * by the variables that define the context of the module element. In EVL this would
     * be ‘self’ (model element of the Context type) in ETL this would be the input (and 
     * output variables). 
     * @return the value of the '<em>Execution Context</em>' reference.
     */
    IContextModuleElementTraceHasExecutionContext executionContext();
                

    /** The ExecutionContext Factory. */
    IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException;       

}
