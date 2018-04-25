 /*******************************************************************************
 * This file was automatically generated on: 2018-04-25.
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

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    

/**
 * The ModelElementVariable defines the access methods for the EClass features.
 * Additionally, the IModelElementVariable acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ModelElementVariable must be
 * created through this interface.
 */
public interface IModelElementVariable {

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

    /** The value reference. */
    IModelElementVariableHasValue value();
                
 
    /**
     * ModelElementVariable has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IModelElementVariable other);
    
}
