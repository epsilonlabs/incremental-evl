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
import org.eclipse.epsilon.evl.incremental.trace.Invariant;    

/**
 * The Satisfies defines the access methods for the EClass features.
 * Additionally, the Satisfies acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the Satisfies must be
 * created through this interface.
 */
public interface Satisfies {

    /**
     * Returns the value of the '<em><b>All</b></em>' attribute.
     * <!-- protected region all-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>All</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region all-getter-doc end --> 
     * @return the value of the '<em>All</em>' attribute.
     */
    boolean getAll();    

    /**
     * Sets the value of the '{@link Satisfies#All <em>All</em>}' attribute.
     * <!-- protected region all-setter-doc on begin -->
     * <!-- protected region all-setter-doc end --> 
     * @param value the new value of the '<em>All/em>' attribute.
     */
    void setAll(boolean value);
            
    /** The invariants reference. */
    SatisfiesHasInvariants invariants();
                
 
    /**
     * Satisfies has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final Satisfies other);
    
}
