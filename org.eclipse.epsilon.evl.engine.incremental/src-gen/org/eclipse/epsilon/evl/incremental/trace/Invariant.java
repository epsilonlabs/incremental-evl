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

import org.eclipse.epsilon.evl.incremental.trace.Check;    
import org.eclipse.epsilon.evl.incremental.trace.Message;    
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;    

public interface Invariant extends GuardedElement {

    /**
     * Returns the value of the '<em><b>Result</b></em>' attribute.
     * <!-- protected region result-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Result</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region result-getter-doc end --> 
     * @return the value of the '<em>Result</em>' attribute.
     */
    boolean getResult();    

    /**
     * Sets the value of the '{@link Invariant#Result <em>Result</em>}' attribute.
     * <!-- protected region result-setter-doc on begin -->
     * <!-- protected region result-setter-doc end --> 
     * @param value the new value of the '<em>Result/em>' attribute.
     */
    void setResult(boolean value);
            
    InvariantHasCheck check();            
    InvariantHasMessage message();            
    InvariantHasSatisfies satisfies();            

}