 /*******************************************************************************
 * This file was automatically generated on: 2018-02-01.
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

import org.eclipse.epsilon.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;    

/**
 * The GuardedElementTrace defines the access methods for the EClass features.
 * Additionally, the IGuardedElementTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the GuardedElementTrace must be
 * created through this interface.
 */
public interface IGuardedElementTrace extends IExecutionTrace {

    /** The guard reference. */
    IGuardedElementTraceHasGuard guard();
                
    /** The GuardTrace Factory. */
    IGuardTrace createGuardTrace() throws EolIncrementalExecutionException;       
   
}
