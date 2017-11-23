 /*******************************************************************************
 * This file was automatically generated on: 2017-11-23.
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
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;    

/**
 * The ContextTrace defines the access methods for the EClass features.
 * Additionally, the IContextTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ContextTrace must be
 * created through this interface.
 */
public interface IContextTrace extends IGuardedElementTrace, IExecutionTrace, IModuleElementTrace {

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
     * Sets the value of the '{@link ContextTrace#Kind <em>Kind</em>}' attribute.
     * <!-- protected region kind-setter-doc on begin -->
     * <!-- protected region kind-setter-doc end --> 
     * @param value the new value of the '<em>Kind/em>' attribute.
     */
    void setKind(String value);
            
    /** The constraints reference. */
    IContextTraceHasConstraints constraints();
                
    /** The context reference. */
    IContextTraceHasContext context();
                
 
    /**
     * ContextTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IContextTrace other);
    
    /** The GuardTrace Factory. */
    IGuardTrace createGuardTrace() throws EolIncrementalExecutionException;       
   
   
    /** The InvariantTrace Factory. */
    IInvariantTrace createInvariantTrace(String name) throws EolIncrementalExecutionException;       
   
}
