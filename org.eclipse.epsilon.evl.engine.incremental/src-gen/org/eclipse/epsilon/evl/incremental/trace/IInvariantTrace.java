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
public interface IInvariantTrace extends IGuardedElementTrace, IInContextModuleElementTrace {
    
    /**
     * Returns the value of the '{@link InvariantTrace#name <em>name</em>}' attribute.
     * <!-- protected region name-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>name</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region name-getter-doc end --> 
     * @return the value of the '<em>name</em>' attribute.
     */
    String getName();

    /**
     * Returns the value of the '<em><b>check</b></em>' reference.
     * <!-- protected region check-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>check</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region check-getter-doc end --> 
     * @return the value of the '<em>check</em>' reference.
     */
    IInvariantTraceHasCheck check();
                
    /**
     * Returns the value of the '<em><b>message</b></em>' reference.
     * <!-- protected region message-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>message</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region message-getter-doc end --> 
     * @return the value of the '<em>message</em>' reference.
     */
    IInvariantTraceHasMessage message();
                
    /**
     * Returns the value of the '<em><b>satisfies</b></em>' reference.
     * <!-- protected region satisfies-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>satisfies</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region satisfies-getter-doc end --> 
     * @return the value of the '<em>satisfies</em>' reference.
     */
    IInvariantTraceHasSatisfies satisfies();
                
    /**
     * Returns the value of the '<em><b>Invariant Context</b></em>' reference.
     * <!-- protected region invariantContext-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Invariant Context</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region invariantContext-getter-doc end --> 
     * @return the value of the '<em>Invariant Context</em>' reference.
     */
    IInvariantTraceHasInvariantContext invariantContext();
                

 
    /**
     * InvariantTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IInvariantTrace other);
    
    /** The GuardTrace Factory. */
    IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException;       
    /** The CheckTrace Factory. */
    ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException;       
    /** The MessageTrace Factory. */
    IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException;       
    /** The SatisfiesTrace Factory. */
    ISatisfiesTrace getOrCreateSatisfiesTrace() throws EolIncrementalExecutionException;       

}
