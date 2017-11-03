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
package org.eclipse.epsilon.evl.incremental.trace;


public interface Satisfies {

    /**
     * Returns the value of the '<em><b>All</b></em>' attribute.
     * <!-- protected region all-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>All</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region all-getter-doc end --> 
     * @return the value of the '<em>All</em>' attribute.
     */
    boolean getAll();    

    /**
     * Sets the value of the '{@link Satisfies#All <em>All</em>}' attribute.
     * <!-- protected region all-setter-doc on begin -->
     * <!-- protected region all-setter-doc end --> 
     * @param value the new value of the '<em>All/em>' attribute.
     */
    void setAll(boolean value);
            
    SatisfiesHasSatsfies satsfies();            

}