 /*******************************************************************************
 * This file was automatically generated on: 2018-09-04.
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

import java.util.Map;

import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * An ElementAccess denotes access to a model element as a whole. This usually
 * happens when an element is used for a context.
 */
public interface IElementAccess extends IAccess {

    /**
     * Returns the value of the '<em><b>element</b></em>' reference.
     * <!-- protected region element-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Element</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region element-getter-doc end --> 
     * @return the value of the '<em>element</em>' reference.
     */
    IElementAccessHasElement element();
                

 
    /**
     * ElementAccess has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IElementAccess other);
    

}
