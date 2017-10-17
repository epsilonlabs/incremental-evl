 /*******************************************************************************
 * This file was automatically generated on: 2017-10-17.
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
import org.eclipse.epsilon.incremental.trace.eol.ExecutableBlock;    

public interface ModuleElement extends TraceElement {

    /**
     * Returns the value of the '<em><b>Executableblocks</b></em>' attribute.
     * <!-- protected region executableblocks-getter-doc on begin -->
	* <p>
	* If the meaning of the '<em>Executableblocks</em>' attribute isn't clear,
	* there really should be more of a description here...
	* </p>
	* <!-- protected region executableblocks-getter-doc end --> 
     * @return the value of the '<em>Executableblocks</em>' attribute.
     */
    List<ExecutableBlock> getExecutableblocks();            
}