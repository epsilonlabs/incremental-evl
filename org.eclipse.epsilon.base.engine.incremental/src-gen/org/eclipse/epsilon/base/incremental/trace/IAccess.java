 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * An Access is the super class of all possible types of accesses that can ocurr during
 * execution.
 */
public interface IAccess extends IIdElement {

    /**
     * Returns the value of the '<em><b>Execution Trace</b></em>' reference.
     * <!-- protected region executionTrace-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Execution Trace</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region executionTrace-getter-doc end --> 
     * @return the value of the '<em>Execution Trace</em>' reference.
     */
    IAccessHasExecutionTrace executionTrace();
                

}
