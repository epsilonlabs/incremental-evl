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
package org.eclipse.epsilon.evl.incremental.trace;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;    
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;    

/**
 * The EvlModuleExecution defines the access methods for the EClass features.
 * Additionally, the IEvlModuleExecution acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the EvlModuleExecution must be
 * created through this interface.
 */
public interface IEvlModuleExecution extends IModuleExecution {

 
    /**
     * EvlModuleExecution has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IEvlModuleExecution other);
    
    /** The EvlModuleTrace Factory. */
    IEvlModuleTrace createEvlModuleTrace(String source) throws EolIncrementalExecutionException;       
            
   
    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name) throws EolIncrementalExecutionException;       
   
    /** The ContextTrace Factory. */
    IContextTrace createContextTrace(String kind) throws EolIncrementalExecutionException;       
            
   
}
