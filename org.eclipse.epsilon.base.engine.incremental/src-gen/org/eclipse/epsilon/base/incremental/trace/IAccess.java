 /*******************************************************************************
 * This file was automatically generated on: 2018-05-04.
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
 * The Access defines the access methods for the EClass features.
 * Additionally, the IAccess acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the Access must be
 * created through this interface.
 */
public interface IAccess extends IIdElement {


    /** The executionTrace reference. */
    IAccessHasExecutionTrace executionTrace();
                
}
