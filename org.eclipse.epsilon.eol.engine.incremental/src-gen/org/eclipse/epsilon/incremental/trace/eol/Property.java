 /*******************************************************************************
 * This file was automatically generated on: 2017-10-17.
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

import java.util.Set;    
import org.eclipse.epsilon.incremental.trace.eol.ElementTrace;    

public interface Property extends TraceElement {

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- protected region value-getter-doc on begin -->
	* <p>
	* If the meaning of the '<em>Value</em>' attribute isn't clear,
	* there really should be more of a description here...
	* </p>
	* <!-- protected region value-getter-doc end --> 
     * @return the value of the '<em>Value</em>' attribute.
     */
    String getValue();    

    /**
     * Sets the value of the '{@link Property#Value <em>Value</em>}' attribute.
     * <!-- protected region value-setter-doc on begin -->
	* <!-- protected region value-setter-doc end --> 
     * @param value the new value of the '<em>Value/em>' attribute.
     */
    void setValue(String value);
            
    /**
     * Returns the value of the '<em><b>Traces</b></em>' attribute.
     * <!-- protected region traces-getter-doc on begin -->
	* <p>
	* If the meaning of the '<em>Traces</em>' attribute isn't clear,
	* there really should be more of a description here...
	* </p>
	* <!-- protected region traces-getter-doc end --> 
     * @return the value of the '<em>Traces</em>' attribute.
     */
    Set<ElementTrace> getTraces();            
}