 /*******************************************************************************
 * This file was automatically generated on: 2017-10-18.
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


public interface TraceElement extends IdElement {

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
     * Sets the value of the '{@link TraceElement#Uri <em>Uri</em>}' attribute.
     * <!-- protected region uri-setter-doc on begin -->
	* <!-- protected region uri-setter-doc end --> 
     * @param value the new value of the '<em>Uri/em>' attribute.
     */
    void setUri(String value);
            
}