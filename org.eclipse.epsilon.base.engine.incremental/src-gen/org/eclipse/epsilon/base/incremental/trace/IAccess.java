 /*******************************************************************************
 * This file was automatically generated on: 2018-05-31.
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

import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;    

/**
 * An Access is the super class of all possible types of accesses that can ocurr during
   execution.
 
 */
public interface IAccess extends IIdElement {


    /** The executionTrace reference. */
    IAccessHasExecutionTrace executionTrace();
                
}
