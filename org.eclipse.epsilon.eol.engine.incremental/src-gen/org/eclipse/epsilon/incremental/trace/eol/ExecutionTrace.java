 /*******************************************************************************
 * This file was automatically generated on: 2017-10-20.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.incremental.trace.eol;

import java.util.List;    
import org.eclipse.epsilon.incremental.trace.eol.Execution;    
import org.eclipse.epsilon.incremental.trace.eol.Model;    

public interface ExecutionTrace extends IdElement {

    /**
     * Returns the value of the '<em><b>Execution</b></em>' attribute.
     * <!-- protected region execution-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Execution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region execution-getter-doc end --> 
     * @return the value of the '<em>Execution</em>' attribute.
     */
    List<Execution> getExecution();            
    /**
     * Returns the value of the '<em><b>Model</b></em>' attribute.
     * <!-- protected region model-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Model</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region model-getter-doc end --> 
     * @return the value of the '<em>Model</em>' attribute.
     */
    List<Model> getModel();            
}