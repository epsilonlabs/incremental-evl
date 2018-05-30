 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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

import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;    

/**
 * The NestedModuleElementTrace defines the access methods for the EClass features.
 * Additionally, the INestedModuleElementTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the NestedModuleElementTrace must be
 * created through this interface.
 */
public interface INestedModuleElementTrace extends IModuleElementTrace {


    /** The parentTrace reference. */
    INestedModuleElementTraceHasParentTrace parentTrace();
                
}
