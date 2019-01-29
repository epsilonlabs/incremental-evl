 /*******************************************************************************
 * This file was automatically generated on: 2019-01-24.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import java.util.Map;

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    
import org.eclipse.epsilon.evl.incremental.trace.*;    
import org.eclipse.epsilon.evl.incremental.trace.impl.*;    

/**
 * The CheckResult Interface.
 */
public interface ICheckResult extends IIdElement {
    
    /**
     * Returns the value of the '{@link CheckResult#value <em>value</em>}' attribute.
     * <!-- protected region value-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>value</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region value-getter-doc end --> 
     * @return the value of the '<em>value</em>' attribute.
     */
    Boolean getValue();

    /**
     * Sets the value of the '{@link CheckResult#value <em>value</em>}' attribute.
     * <!-- protected region value-setter-doc on begin -->
     * <p>
     * If the meaning of the '<em>value</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region value-setter-doc end --> 
     * @param value the new value of the '<em>value</em>' attribute.
     */
    void setValue(boolean value);

    /**
     * Returns the value of the '<em><b>context</b></em>' reference.
     * <!-- protected region context-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>context</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region context-getter-doc end --> 
     * @return the value of the '<em>context</em>' reference.
     */
    ICheckResultHasContext context();
                

 
    /**
     * CheckResult has same identity in the aggregate.
     */
    boolean sameIdentityAs(final ICheckResult other);
    

}
