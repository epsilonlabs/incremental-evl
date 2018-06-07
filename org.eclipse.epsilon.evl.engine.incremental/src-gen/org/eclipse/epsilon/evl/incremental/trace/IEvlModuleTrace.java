 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;    

/**
 * A ModuleExecutionTrace represents the trace of the execution of a particular Epsilon module
 * (e.g. Eol, Etl, etc.) for a given script (identified by the source) and a given set of Models.
 */
public interface IEvlModuleTrace extends IModuleExecutionTrace {

 
    /**
     * EvlModuleTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IEvlModuleTrace other);
    
   
   
    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name, String uri) throws EolIncrementalExecutionException;       
   
}
