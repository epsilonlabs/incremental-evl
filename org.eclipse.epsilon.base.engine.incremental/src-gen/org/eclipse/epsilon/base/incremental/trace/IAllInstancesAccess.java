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
 * An AllInstancesAccess denotes access to all elemens of a given type/kind. This 
 * is the result of a call to all() (and its derivatives).
 */
@SuppressWarnings("unused")
public interface IAllInstancesAccess extends IAccess {
    
    /**
     * Returns the value of the '{@link AllInstancesAccess#ofKind <em>ofKind</em>}' attribute.
     * <!-- protected region ofKind-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Of Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region ofKind-getter-doc end --> 
     * @return the value of the '<em>Of Kind</em>' attribute.
     */
    Boolean getOfKind();

    /**
     * Returns the value of the '<em><b>type</b></em>' reference.
     * <!-- protected region type-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region type-getter-doc end --> 
     * @return the value of the '<em>type</em>' reference.
     */
    IAllInstancesAccessHasType type();
                

 
    /**
     * AllInstancesAccess has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IAllInstancesAccess other);
    

}
