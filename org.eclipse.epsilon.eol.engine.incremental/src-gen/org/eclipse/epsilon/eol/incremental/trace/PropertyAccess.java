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
package org.eclipse.epsilon.eol.incremental.trace;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.Property;    

/**
 * The PropertyAccess defines the access methods for the EClass features.
 * Additionally, the PropertyAccess extends Access acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the PropertyAccess must be
 * created through this interface.
 */
public interface PropertyAccess extends Access {

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- protected region value-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region value-getter-doc end --> 
     * @return the value of the '<em>Value</em>' attribute.
     */
    String getValue();    

    /**
     * Sets the value of the '{@link PropertyAccess#Value <em>Value</em>}' attribute.
     * <!-- protected region value-setter-doc on begin -->
     * <!-- protected region value-setter-doc end --> 
     * @param value the new value of the '<em>Value/em>' attribute.
     */
    void setValue(String value);
            
    /** The property reference. */
    PropertyAccessHasProperty property();
                
 
    /**
     * PropertyAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final PropertyAccess other);
    
}
