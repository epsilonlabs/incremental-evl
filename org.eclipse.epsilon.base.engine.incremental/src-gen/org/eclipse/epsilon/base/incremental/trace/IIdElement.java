 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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
 * The IdElement Interface.
 */
public interface IIdElement {
    
    /**
     * Returns the value of the '{@link IdElement#id <em>id</em>}' attribute.
     * <!-- protected region id-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region id-getter-doc end --> 
     * @return the value of the '<em>id</em>' attribute.
     */
    Object getId();

    /**
     * Sets the value of the '{@link IdElement#id <em>id</em>}' attribute.
     * <!-- protected region id-setter-doc on begin -->
     * <!-- protected region id-setter-doc end --> 
     * @param value the new value of the '<em>id</em>' attribute.
     */
    void setId(java.lang.Object value);

    Map<String,Object> getIdProperties();  


}
