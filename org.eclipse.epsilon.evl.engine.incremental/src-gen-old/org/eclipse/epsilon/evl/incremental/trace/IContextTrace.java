 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;    

/**
 * The ContextTrace defines the access methods for the EClass features.
 * Additionally, the IContextTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ContextTrace must be
 * created through this interface.
 */
public interface IContextTrace extends IGuardedElementTrace, IRuleTrace {

    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * <!-- protected region kind-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region kind-getter-doc end --> 
     * @return the value of the '<em>Kind</em>' attribute.
     */
    String getKind();            
    /**
     * Returns the value of the '<em><b>Index</b></em>' attribute.
     * <!-- protected region index-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Index</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region index-getter-doc end --> 
     * @return the value of the '<em>Index</em>' attribute.
     */
    Integer getIndex();            

    /** The constraints reference. */
    IContextTraceHasConstraints constraints();
                
 
    /**
     * ContextTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IContextTrace other);
    
    /** The GuardTrace Factory. */
    IGuardTrace createGuardTrace() throws EolIncrementalExecutionException;       
   
    /** The InvariantTrace Factory. */
    IInvariantTrace createInvariantTrace(String name) throws EolIncrementalExecutionException;       
   
}
