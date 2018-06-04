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
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;    

/**
 * A ModelElementTrace represents a model element that was accessed during 
   execution. The element represented is identified by its uri.
 
 */
public interface IModelElementTrace extends IIdElement {

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

    /** The properties reference. */
    IModelElementTraceHasProperties properties();
                
 
    /**
     * ModelElementTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IModelElementTrace other);
    
    /** The PropertyTrace Factory. */
    IPropertyTrace createPropertyTrace(String name) throws EolIncrementalExecutionException;       
   
}
