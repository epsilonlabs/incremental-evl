 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
 * A ModuleElementTrace that defines the context of its child module elements, e.g.
 * an operaion, rule, etc. There is one ModuleElementTrace for each module element
 * present in a module (e.g. one trace per rule), that is, a ModuleElementTrace 
 * represents the static component of a module element.
 * 
 * During execution (dynamic component), an ExecutionContext is created for each
 * invocation of the module element. This ExecutionContext contains the
 * ModelElements that define the context and all of the Accesses that ocurred
 * during execution of the module element and its children. 
 */
@SuppressWarnings("unused")
public interface IContextModuleElementTrace extends IModuleElementTrace {

    /**
     * Returns the value of the '<em><b>Execution Context</b></em>' reference.
     * The execution context in which this module was executed. 
     * @return the value of the '<em>Execution Context</em>' reference.
     */
    IContextModuleElementTraceHasExecutionContext executionContext();
                

    /** The ExecutionContext Factory. */
    IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException;       

}
