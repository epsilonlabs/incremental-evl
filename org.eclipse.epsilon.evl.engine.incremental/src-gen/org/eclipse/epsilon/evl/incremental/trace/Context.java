 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;    
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;    
import org.eclipse.epsilon.evl.incremental.trace.Guard;    
import org.eclipse.epsilon.evl.incremental.trace.Invariant;    

/**
 * The Context defines the access methods for the EClass features.
 * Additionally, the Context extends GuardedElement, ModuleElement acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the Context must be
 * created through this interface.
 */
public interface Context extends GuardedElement, ModuleElement {

    /** The constraints reference. */
    ContextHasConstraints constraints();
                
    /** The context reference. */
    ContextHasContext context();
                
 
    /**
     * Context has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final Context other);
    
    /** The Guard Factory. */
    Guard createGuard() throws EolIncrementalExecutionException;       
   
    /** The Invariant Factory. */
    Invariant createInvariant() throws EolIncrementalExecutionException;       
   
}
