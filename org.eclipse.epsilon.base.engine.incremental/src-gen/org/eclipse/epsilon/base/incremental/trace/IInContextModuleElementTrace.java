 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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
 * A module element that is not executed in a context, but that is nested in one that
 * is (e.g. guard, check, etc.)
 */
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
