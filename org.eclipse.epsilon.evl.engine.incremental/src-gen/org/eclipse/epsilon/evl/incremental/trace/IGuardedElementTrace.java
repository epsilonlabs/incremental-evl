 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    
import org.eclipse.epsilon.evl.incremental.trace.*;    
import org.eclipse.epsilon.evl.incremental.trace.impl.*;    

/**
 * The GuardedElementTrace Interface.
 */
public interface IGuardedElementTrace extends IIdElement {

    /**
     * Returns the value of the '<em><b>guard</b></em>' reference.
     * <!-- protected region guard-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>guard</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region guard-getter-doc end --> 
     * @return the value of the '<em>guard</em>' reference.
     */
    IGuardedElementTraceHasGuard guard();
                
    /** The GuardTrace Factory. */
    IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException;       

}
