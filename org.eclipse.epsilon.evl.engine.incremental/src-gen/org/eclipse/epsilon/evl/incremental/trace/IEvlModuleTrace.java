 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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

import java.util.Map;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    
import org.eclipse.epsilon.evl.incremental.trace.*;    
import org.eclipse.epsilon.evl.incremental.trace.impl.*;    

/**
 * A ModuleExecutionTrace represents the trace of the execution of a particular Epsilon module
 * (e.g. Eol, Etl, etc.) for a given script (identified by the source) and a given set of Models.
 */
@SuppressWarnings("unused")
public interface IEvlModuleTrace extends IModuleExecutionTrace {


 
    /**
     * EvlModuleTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IEvlModuleTrace other);
    
    /** The ContextTrace Factory. */
    IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException;       

    /** The ModelAccess Factory. */
    IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException;       
            
    /** The Access Factory. */        
    public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException;
          

}
