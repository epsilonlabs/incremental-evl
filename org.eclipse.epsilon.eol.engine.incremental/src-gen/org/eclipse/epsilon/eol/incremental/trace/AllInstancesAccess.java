 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace;


public interface AllInstancesAccess extends Access {

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

    /**
     * Sets the value of the '{@link AllInstancesAccess#OfKind <em>Of Kind</em>}' attribute.
     * <!-- protected region ofKind-setter-doc on begin -->
     * <!-- protected region ofKind-setter-doc end --> 
     * @param value the new value of the '<em>Of Kind/em>' attribute.
     */
    void setOfKind(boolean value);
            
    AllInstancesAccessHasType type();            

}