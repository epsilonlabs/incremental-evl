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

/**
 * The Module defines the access methods for the EClass features.
 * Additionally, the Module extends IdElement acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the Module must be
 * created through this interface.
 */
public interface Module extends IdElement {

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute.
     * <!-- protected region source-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region source-getter-doc end --> 
     * @return the value of the '<em>Source</em>' attribute.
     */
    String getSource();    

    /**
     * Sets the value of the '{@link Module#Source <em>Source</em>}' attribute.
     * <!-- protected region source-setter-doc on begin -->
     * <!-- protected region source-setter-doc end --> 
     * @param value the new value of the '<em>Source/em>' attribute.
     */
    void setSource(String value);
            
    /** The modules reference. */
    ModuleHasModules modules();
                
   
}
