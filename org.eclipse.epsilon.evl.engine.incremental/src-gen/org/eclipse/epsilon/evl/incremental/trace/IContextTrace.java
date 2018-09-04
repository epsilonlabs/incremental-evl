 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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
public interface IContextTrace extends IGuardedElementTrace, IContextModuleElementTrace {
    
    /**
     * Returns the value of the '{@link ContextTrace#kind <em>kind</em>}' attribute.
     * <!-- protected region kind-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>kind</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region kind-getter-doc end --> 
     * @return the value of the '<em>kind</em>' attribute.
     */
    String getKind();
    
    /**
     * Returns the value of the '{@link ContextTrace#index <em>index</em>}' attribute.
     * <!-- protected region index-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>index</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region index-getter-doc end --> 
     * @return the value of the '<em>index</em>' attribute.
     */
    Integer getIndex();

    /**
     * Returns the value of the '<em><b>constraints</b></em>' reference.
     * <!-- protected region constraints-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>constraints</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region constraints-getter-doc end --> 
     * @return the value of the '<em>constraints</em>' reference.
     */
    IContextTraceHasConstraints constraints();
                

 
    /**
     * ContextTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IContextTrace other);
    
    /** The GuardTrace Factory. */
    IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException;       
    /** The ExecutionContext Factory. */
    IExecutionContext getOrCreateExecutionContext() throws EolIncrementalExecutionException;       
    /** The InvariantTrace Factory. */
    IInvariantTrace getOrCreateInvariantTrace(String name) throws EolIncrementalExecutionException;       

}
