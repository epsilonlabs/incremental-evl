 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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

import java.util.Map;

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * A PropertyAccess denotes access to property of a model element.
 */
@SuppressWarnings("unused")
public interface IModelTypeTrace extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModelTypeTrace#name <em>name</em>}' attribute.
     * <!-- protected region name-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region name-getter-doc end --> 
     * @return the value of the '<em>name</em>' attribute.
     */
    String getName();

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
    IModelTypeTraceHasModelTrace modelTrace();
                

 
    /**
     * ModelTypeTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IModelTypeTrace other);
    

}
