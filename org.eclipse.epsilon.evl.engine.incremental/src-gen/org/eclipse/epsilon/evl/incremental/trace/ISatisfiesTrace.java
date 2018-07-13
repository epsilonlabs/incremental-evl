 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    
import org.eclipse.epsilon.evl.incremental.trace.*;    
import org.eclipse.epsilon.evl.incremental.trace.impl.*;    

/**
 * A ModuleElementTrace represents the trace of the execution of a particular element of 
 * an Epsilon module (e.g. Eol, Etl, etc.). The ModuleElementTrace containts information
 * about the different types of accesses that occured during its execution.
 */
public interface ISatisfiesTrace extends IInContextModuleElementTrace {
    
    /**
     * Returns the value of the '{@link SatisfiesTrace#all <em>all</em>}' attribute.
     * <!-- protected region all-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>all</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region all-getter-doc end --> 
     * @return the value of the '<em>all</em>' attribute.
     */
    boolean getAll();

    /**
     * Sets the value of the '{@link SatisfiesTrace#all <em>all</em>}' attribute.
     * <!-- protected region all-setter-doc on begin -->
     * <p>
     * If the meaning of the '<em>all</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region all-setter-doc end --> 
     * @param value the new value of the '<em>all</em>' attribute.
     */
    void setAll(boolean value);

    /**
     * Returns the value of the '<em><b>invariant</b></em>' reference.
     * <!-- protected region invariant-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>invariant</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region invariant-getter-doc end --> 
     * @return the value of the '<em>invariant</em>' reference.
     */
    ISatisfiesTraceHasInvariant invariant();
                
    /**
     * Returns the value of the '<em><b>Satisfied Invariants</b></em>' reference.
     * <!-- protected region satisfiedInvariants-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Satisfied Invariants</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region satisfiedInvariants-getter-doc end --> 
     * @return the value of the '<em>Satisfied Invariants</em>' reference.
     */
    ISatisfiesTraceHasSatisfiedInvariants satisfiedInvariants();
                
 
    /**
     * SatisfiesTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final ISatisfiesTrace other);
    

}
