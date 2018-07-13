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

import org.eclipse.epsilon.base.incremental.trace.IModelTrace;    

/**
 * The ModelAccess Interface.
 */
public interface IModelAccess extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModelAccess#modelName <em>modelName</em>}' attribute.
     * <!-- protected region modelName-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Model Name</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region modelName-getter-doc end --> 
     * @return the value of the '<em>Model Name</em>' attribute.
     */
    String getModelName();

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
    IModelAccessHasModelTrace modelTrace();
                
 
    /**
     * ModelAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IModelAccess other);
    

}
