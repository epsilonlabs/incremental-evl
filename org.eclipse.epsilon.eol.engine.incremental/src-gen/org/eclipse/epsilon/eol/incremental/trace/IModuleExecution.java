 /*******************************************************************************
 * This file was automatically generated on: 2017-12-07.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;    

/**
 * The ModuleExecution defines the access methods for the EClass features.
 * Additionally, the IModuleExecution acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ModuleExecution must be
 * created through this interface.
 */
public interface IModuleExecution extends IIdElement {

    /** The module reference. */
    IModuleExecutionHasModule module();
                
    /** The model reference. */
    IModuleExecutionHasModel model();
                
    /** The executions reference. */
    IModuleExecutionHasExecutions executions();
                
    /** The moduleElements reference. */
    IModuleExecutionHasModuleElements moduleElements();
                
   
    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name) throws EolIncrementalExecutionException;       
   
   
}
