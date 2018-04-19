 /*******************************************************************************
 * This file was automatically generated on: 2018-04-18.
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

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;    
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;    

/**
 * The RuleTrace defines the access methods for the EClass features.
 * Additionally, the IRuleTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the RuleTrace must be
 * created through this interface.
 */
public interface IRuleTrace extends IModuleElementTrace {


    /** The module reference. */
    IRuleTraceHasModule module();
                
    /** The executionContext reference. */
    IRuleTraceHasExecutionContext executionContext();
                
}
