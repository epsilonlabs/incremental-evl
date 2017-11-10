 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.Module;    
import org.eclipse.epsilon.evl.incremental.trace.Context;    

/**
 * The EvlModule defines the access methods for the EClass features.
 * Additionally, the EvlModule extends Module acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the EvlModule must be
 * created through this interface.
 */
public interface EvlModule extends Module {

 
    /**
     * EvlModule has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final EvlModule other);
    
    /** The Context Factory. */
    Context createContext() throws EolIncrementalExecutionException;       
            
   
}
