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
 * A module element that is not executed in a context, but that is nested in one that
 * is (e.g. guard, check, etc.)
 */
public interface IInContextModuleElementTrace extends IModuleElementTrace {

    /**
     * Returns the value of the '<em><b>Parent Trace</b></em>' reference.
     * <!-- protected region parentTrace-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Parent Trace</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region parentTrace-getter-doc end --> 
     * @return the value of the '<em>Parent Trace</em>' reference.
     */
    IInContextModuleElementTraceHasParentTrace parentTrace();
                

}
