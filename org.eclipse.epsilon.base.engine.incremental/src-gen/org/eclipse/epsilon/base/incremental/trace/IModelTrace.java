 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.*;    
import org.eclipse.epsilon.base.incremental.trace.impl.*;    

/**
 * A ModelTrace contains trace information for the elements and types in the model 
 * that were accessed during execution.
 */
public interface IModelTrace extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModelTrace#uri <em>uri</em>}' attribute.
     * The uri of the model.
     * @return the value of the '<em>uri</em>' attribute.
     */
    String getUri();

    /**
     * Returns the value of the '<em><b>elements</b></em>' reference.
     * <!-- protected region elements-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Elements</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region elements-getter-doc end --> 
     * @return the value of the '<em>elements</em>' reference.
     */
    IModelTraceHasElements elements();
                
    /**
     * Returns the value of the '<em><b>types</b></em>' reference.
     * <!-- protected region types-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Types</em>' attribute isn't clear,
     * add it to the metamodel as a GenDoc or edit it here.
     * </p>
    * <!-- protected region types-getter-doc end --> 
     * @return the value of the '<em>types</em>' reference.
     */
    IModelTraceHasTypes types();
                

 
    /**
     * ModelTrace has same identity in the aggregate.
     */
    boolean sameIdentityAs(final IModelTrace other);
    
    /** The ModelElementTrace Factory. */
    IModelElementTrace getOrCreateModelElementTrace(String uri, IModelTypeTrace type) throws EolIncrementalExecutionException;       
    /** The ModelTypeTrace Factory. */
    IModelTypeTrace getOrCreateModelTypeTrace(String name) throws EolIncrementalExecutionException;       

}
