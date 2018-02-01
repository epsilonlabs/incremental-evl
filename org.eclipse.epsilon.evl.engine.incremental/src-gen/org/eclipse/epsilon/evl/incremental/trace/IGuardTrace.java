 /*******************************************************************************
 * This file was automatically generated on: 2018-02-01.
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

import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;    

/**
 * The GuardTrace defines the access methods for the EClass features.
 * Additionally, the IGuardTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the GuardTrace must be
 * created through this interface.
 */
public interface IGuardTrace extends IExecutionTrace {

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
     * Sets the value of the '{@link GuardTrace#Result <em>Result</em>}' attribute.
     * <!-- protected region result-setter-doc on begin -->
     * <!-- protected region result-setter-doc end --> 
     * @param value the new value of the '<em>Result/em>' attribute.
     */
    void setResult(boolean value);
            
    /** The limits reference. */
    IGuardTraceHasLimits limits();
                
 
    /**
     * GuardTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IGuardTrace other);
    
}
