 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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
 * A ModelElementVariable pairs a variable name to the model element assigned to 
 * that variable during execution.
 */
@SuppressWarnings("unused")
public interface IModelElementVariable extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModelElementVariable#name <em>name</em>}' attribute.
     * <!-- protected region name-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region name-getter-doc end --> 
     * @return the value of the '<em>name</em>' attribute.
     */
    String getName();

    /**
     * Returns the value of the '<em><b>value</b></em>' reference.
     * <!-- protected region value-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region value-getter-doc end --> 
     * @return the value of the '<em>value</em>' reference.
     */
    IModelElementVariableHasValue value();
                

 
    /**
     * ModelElementVariable has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IModelElementVariable other);
    

}
