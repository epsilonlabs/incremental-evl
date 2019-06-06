 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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

import java.util.Map;

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    
import org.eclipse.epsilon.evl.incremental.trace.*;    
import org.eclipse.epsilon.evl.incremental.trace.impl.*;    

/**
 * An Access is the super class of all possible types of accesses that can ocurr
 * during execution.
 */
@SuppressWarnings("unused")
public interface ISatisfiesAccess extends IAccess {
    
    /**
     * Returns the value of the '{@link SatisfiesAccess#all <em>all</em>}' attribute.
     * <!-- protected region all-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>all</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region all-getter-doc end --> 
     * @return the value of the '<em>all</em>' attribute.
     */
    Boolean getAll();

    /**
     * Sets the value of the '{@link SatisfiesAccess#all <em>all</em>}' attribute.
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
     * Returns the value of the '<em><b>Model Element</b></em>' reference.
     * <!-- protected region modelElement-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Model Element</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
     * <!-- protected region modelElement-getter-doc end --> 
     * @return the value of the '<em>Model Element</em>' reference.
     */
    ISatisfiesAccessHasModelElement modelElement();
                
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
    ISatisfiesAccessHasSatisfiedInvariants satisfiedInvariants();
                

 
    /**
     * SatisfiesAccess has same identity in the aggregate.
     */
    boolean sameIdentityAs(final ISatisfiesAccess other);
    

}
