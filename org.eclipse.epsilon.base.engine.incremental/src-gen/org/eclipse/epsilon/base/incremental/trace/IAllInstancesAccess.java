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

import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;    

/**
 * The AllInstancesAccess defines the access methods for the EClass features.
 * Additionally, the IAllInstancesAccess acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the AllInstancesAccess must be
 * created through this interface.
 */
public interface IAllInstancesAccess extends IAccess {

    /**
     * Returns the value of the '<em><b>Of Kind</b></em>' attribute.
     * <!-- protected region ofKind-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Of Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region ofKind-getter-doc end --> 
     * @return the value of the '<em>Of Kind</em>' attribute.
     */
    boolean getOfKind();            

    /** The type reference. */
    IAllInstancesAccessHasType type();
                
 
    /**
     * AllInstancesAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IAllInstancesAccess other);
    
}
