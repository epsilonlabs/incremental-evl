 /*******************************************************************************
 * This file was automatically generated on: 2018-09-05.
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
 * A ModuleElementTrace represents the trace of the execution of a particular element of 
 * an Epsilon module (e.g. Eol, Etl, etc.). The ModuleElementTrace containts information
 * about the different types of accesses that occured during its execution.
 */
public interface ICheckTrace extends IInContextModuleElementTrace {

    /**
     * Returns the value of the '<em><b>invariant</b></em>' reference.
     * <!-- protected region invariant-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>invariant</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region invariant-getter-doc end --> 
     * @return the value of the '<em>invariant</em>' reference.
     */
    ICheckTraceHasInvariant invariant();
                
    /**
     * Returns the value of the '<em><b>result</b></em>' reference.
     * <!-- protected region result-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>result</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region result-getter-doc end --> 
     * @return the value of the '<em>result</em>' reference.
     */
    ICheckTraceHasResult result();
                

 
    /**
     * CheckTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final ICheckTrace other);
    
    /** The CheckResult Factory. */
    ICheckResult getOrCreateCheckResult(IExecutionContext context) throws EolIncrementalExecutionException;       

}
