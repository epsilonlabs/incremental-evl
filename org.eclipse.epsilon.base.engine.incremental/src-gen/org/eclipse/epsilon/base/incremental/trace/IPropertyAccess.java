 /*******************************************************************************
 * This file was automatically generated on: 2018-06-06.
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

import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;    

/**
 * A PropertyAccess denotes access to property of a model element.
 */
public interface IPropertyAccess extends IAccess {
    
    /**
     * Returns the value of the '{@link PropertyAccess#value <em>value</em>}' attribute.
     * The value of the property when it was accessed.
     * @return the value of the '<em>value</em>' attribute.
     */
    String getValue();

    /**
     * Sets the value of the '{@link PropertyAccess#value <em>value</em>}' attribute.
     * The value of the property when it was accessed.
     * @param value the new value of the '<em>value</em>' attribute.
     */
    void setValue(String value);

    /**
     * Returns the value of the '<em><b>property</b></em>' reference.
     * <!-- protected region property-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Property</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region property-getter-doc end --> 
     * @return the value of the '<em>property</em>' reference.
     */
    IPropertyAccessHasProperty property();
                
 
    /**
     * PropertyAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IPropertyAccess other);
    
}
