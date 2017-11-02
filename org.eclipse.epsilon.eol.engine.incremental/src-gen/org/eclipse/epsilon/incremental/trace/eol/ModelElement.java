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
import org.eclipse.epsilon.incremental.trace.eol.Model;    
import org.eclipse.epsilon.incremental.trace.eol.Property;    

public interface ModelElement extends IdElement {

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

    /**
     * Sets the value of the '{@link ModelElement#Uri <em>Uri</em>}' attribute.
     * <!-- protected region uri-setter-doc on begin -->
     * <!-- protected region uri-setter-doc end --> 
     * @param value the new value of the '<em>Uri/em>' attribute.
     */
    void setUri(String value);
            
    /**
     * Returns the value of the '<em><b>Model</b></em>' attribute.
     * <!-- protected region model-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Model</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region model-getter-doc end --> 
     * @return the value of the '<em>Model</em>' attribute.
     */
    Model getModel();    

    /**
     * Sets the value of the '{@link ModelElement#Model <em>Model</em>}' attribute.
     * <!-- protected region model-setter-doc on begin -->
     * <!-- protected region model-setter-doc end --> 
     * @param value the new value of the '<em>Model/em>' attribute.
     */
    void setModel(Model value);
            
    /**
     * Returns the value of the '<em><b>Properties</b></em>' attribute.
     * <!-- protected region properties-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Properties</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region properties-getter-doc end --> 
     * @return the value of the '<em>Properties</em>' attribute.
     */
    List<Property> getProperties();            
}