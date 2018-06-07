 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
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

import org.eclipse.epsilon.base.incremental.trace.IAccess;    

/**
 * A ModuleElementTrace represents the trace of the execution of a particular element of 
 * an Epsilon module (e.g. Eol, Etl, etc.). The ModuleElementTrace containts information
 * about the different types of accesses that occured during its execution.
 */
public interface IModuleElementTrace extends IIdElement {

    /**
     * Returns the value of the '<em><b>accesses</b></em>' reference.
     * The different accesses that where recorded during execution for this particular 
     * module element.
     * @return the value of the '<em>accesses</em>' reference.
     */
    IModuleElementTraceHasAccesses accesses();
                
}
