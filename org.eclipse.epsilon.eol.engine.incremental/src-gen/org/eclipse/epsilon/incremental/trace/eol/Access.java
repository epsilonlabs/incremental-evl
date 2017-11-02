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

import org.eclipse.epsilon.incremental.trace.eol.Execution;    

public interface Access extends IdElement {

    /**
     * Returns the value of the '<em><b>Execution</b></em>' attribute.
     * <!-- protected region execution-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Execution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region execution-getter-doc end --> 
     * @return the value of the '<em>Execution</em>' attribute.
     */
    Execution getExecution();    

    /**
     * Sets the value of the '{@link Access#Execution <em>Execution</em>}' attribute.
     * <!-- protected region execution-setter-doc on begin -->
     * <!-- protected region execution-setter-doc end --> 
     * @param value the new value of the '<em>Execution/em>' attribute.
     */
    void setExecution(Execution value);
            
}