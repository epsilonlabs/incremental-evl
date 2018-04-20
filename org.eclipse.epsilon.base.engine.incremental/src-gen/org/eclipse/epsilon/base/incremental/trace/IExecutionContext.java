 /*******************************************************************************
 * This file was automatically generated on: 2018-04-20.
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

import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;    
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;    

/**
 * The ExecutionContext defines the access methods for the EClass features.
 * Additionally, the IExecutionContext acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ExecutionContext must be
 * created through this interface.
 */
public interface IExecutionContext extends IIdElement {


    /** The contextVariables reference. */
    IExecutionContextHasContextVariables contextVariables();
                
    /** The rules reference. */
    IExecutionContextHasRules rules();
                
 
    /**
     * ExecutionContext has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IExecutionContext other);
    
}
