 /*******************************************************************************
 * This file was automatically generated on: 2018-04-13.
 * Only modify protected regions indicated by "<!-- -->"
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
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;    

/**
 * The ExecutionAccess defines the access methods for the EClass features.
 * Additionally, the IExecutionAccess acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ExecutionAccess must be
 * created through this interface.
 */
public interface IExecutionAccess {

    /** The access reference. */
    IExecutionAccessHasAccess access();
                
    /** The executionContext reference. */
    IExecutionAccessHasExecutionContext executionContext();
                
 
    /**
     * ExecutionAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IExecutionAccess other);
    
}
