 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
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
import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * A ModelElementTrace represents a model element that was accessed during 
 * execution. The element represented is identified by its uri.
 */
public interface IModelElementTrace extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModelElementTrace#uri <em>uri</em>}' attribute.
     * <!-- protected region uri-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region uri-getter-doc end --> 
     * @return the value of the '<em>uri</em>' attribute.
     */
    String getUri();

    /**
     * Returns the value of the '<em><b>properties</b></em>' reference.
     * <!-- protected region properties-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Properties</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region properties-getter-doc end --> 
     * @return the value of the '<em>properties</em>' reference.
     */
    IModelElementTraceHasProperties properties();
                
    /**
     * Returns the value of the '<em><b>Model Trace</b></em>' reference.
     * <!-- protected region modelTrace-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Model Trace</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region modelTrace-getter-doc end --> 
     * @return the value of the '<em>Model Trace</em>' reference.
     */
    IModelElementTraceHasModelTrace modelTrace();
                
 
    /**
     * ModelElementTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IModelElementTrace other);
    
    /** The PropertyTrace Factory. */
    IPropertyTrace createPropertyTrace(String name) throws EolIncrementalExecutionException;       

}
