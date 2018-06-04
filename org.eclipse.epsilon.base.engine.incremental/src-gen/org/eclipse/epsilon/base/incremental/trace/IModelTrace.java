 /*******************************************************************************
 * This file was automatically generated on: 2018-05-31.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;    

/**
 * A ModelTrace contains trace information for the elements and types in the model 
   that were accessed during execution.
 
 */
public interface IModelTrace extends IIdElement {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- protected region name-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region name-getter-doc end --> 
     * @return the value of the '<em>Name</em>' attribute.
     */
    String getName();            
    /**
     * Returns the value of the '<em><b>Uri</b></em>' attribute.
     * <!-- protected region uri-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region uri-getter-doc end --> 
     * @return the value of the '<em>Uri</em>' attribute.
     */
    String getUri();    

    /**
     * Sets the value of the '{@link ModelTrace#Uri <em>Uri</em>}' attribute.
     * <!-- protected region uri-setter-doc on begin -->
     * <!-- protected region uri-setter-doc end --> 
     * @param value the new value of the '<em>Uri/em>' attribute.
     */
    void setUri(String value);
            

    /** The elements reference. */
    IModelTraceHasElements elements();
                
    /** The types reference. */
    IModelTraceHasTypes types();
                
 
    /**
     * ModelTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IModelTrace other);
    
    /** The ModelElementTrace Factory. */
    IModelElementTrace createModelElementTrace(String uri) throws EolIncrementalExecutionException;       
   
    /** The ModelTypeTrace Factory. */
    IModelTypeTrace createModelTypeTrace(String name) throws EolIncrementalExecutionException;       
   
}
