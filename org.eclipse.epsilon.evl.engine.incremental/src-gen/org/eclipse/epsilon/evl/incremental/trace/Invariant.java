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
import org.eclipse.epsilon.evl.incremental.trace.Check;    
import org.eclipse.epsilon.evl.incremental.trace.Guard;    
import org.eclipse.epsilon.evl.incremental.trace.Message;    
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;    

/**
 * The Invariant defines the access methods for the EClass features.
 * Additionally, the Invariant extends GuardedElement acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the Invariant must be
 * created through this interface.
 */
public interface Invariant extends GuardedElement {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- protected region name-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region name-getter-doc end --> 
     * @return the value of the '<em>Name</em>' attribute.
     */
    String getName();    

    /**
     * Sets the value of the '{@link Invariant#Name <em>Name</em>}' attribute.
     * <!-- protected region name-setter-doc on begin -->
     * <!-- protected region name-setter-doc end --> 
     * @param value the new value of the '<em>Name/em>' attribute.
     */
    void setName(String value);
            
    /**
     * Returns the value of the '<em><b>Result</b></em>' attribute.
     * <!-- protected region result-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Result</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region result-getter-doc end --> 
     * @return the value of the '<em>Result</em>' attribute.
     */
    boolean getResult();    

    /**
     * Sets the value of the '{@link Invariant#Result <em>Result</em>}' attribute.
     * <!-- protected region result-setter-doc on begin -->
     * <!-- protected region result-setter-doc end --> 
     * @param value the new value of the '<em>Result/em>' attribute.
     */
    void setResult(boolean value);
            
    /** The check reference. */
    InvariantHasCheck check();
                
    /** The message reference. */
    InvariantHasMessage message();
                
    /** The satisfies reference. */
    InvariantHasSatisfies satisfies();
                
 
    /**
     * Invariant has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final Invariant other);
    
    /** The Guard Factory. */
    Guard createGuard() throws EolIncrementalExecutionException;       
   
    /** The Check Factory. */
    Check createCheck() throws EolIncrementalExecutionException;       
   
    /** The Message Factory. */
    Message createMessage() throws EolIncrementalExecutionException;       
   
    /** The Satisfies Factory. */
    Satisfies createSatisfies() throws EolIncrementalExecutionException;       
   
}
