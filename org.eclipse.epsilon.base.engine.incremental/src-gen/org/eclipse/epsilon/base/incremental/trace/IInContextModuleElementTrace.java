 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
 * A ModuleElementTrace that inherits its context from another module element.
 * During execution (dynamic component), the InContextModuleElemetTrace
 * populates the Accessses of its parent module element. That is, all accesses
 * of to the module element are delegated to its context module element.
 * 
 * The contextModuleElement reference is derived as each module element
 * can have a different method to determine it's "parent" context module
 * element. E.g. It can be its container or another reference.
 */
@SuppressWarnings("unused")
public interface IInContextModuleElementTrace extends IModuleElementTrace {

    /**
     * Returns the value of the '<em><b>Context Module Element</b></em>' reference.
     * <!-- protected region contextModuleElement-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Context Module Element</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region contextModuleElement-getter-doc end --> 
     * @return the value of the '<em>Context Module Element</em>' reference.
     */
    IInContextModuleElementTraceHasContextModuleElement contextModuleElement();
                


}
