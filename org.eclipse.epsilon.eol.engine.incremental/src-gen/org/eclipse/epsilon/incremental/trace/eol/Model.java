 /*******************************************************************************
 * This file was automatically generated on: 2017-10-20.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.incremental.trace.eol;

import java.util.List;    
import org.eclipse.epsilon.incremental.trace.eol.ModelElement;    
import org.eclipse.epsilon.incremental.trace.eol.ModelType;    

public interface Model extends IdElement {

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

    /**
     * Sets the value of the '{@link Model#Name <em>Name</em>}' attribute.
     * <!-- protected region name-setter-doc on begin -->
     * <!-- protected region name-setter-doc end --> 
     * @param value the new value of the '<em>Name/em>' attribute.
     */
    void setName(String value);
            
    /**
     * Returns the value of the '<em><b>Elements</b></em>' attribute.
     * <!-- protected region elements-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Elements</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region elements-getter-doc end --> 
     * @return the value of the '<em>Elements</em>' attribute.
     */
    List<ModelElement> getElements();            
    /**
     * Returns the value of the '<em><b>Types</b></em>' attribute.
     * <!-- protected region types-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Types</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region types-getter-doc end --> 
     * @return the value of the '<em>Types</em>' attribute.
     */
    List<ModelType> getTypes();            
}