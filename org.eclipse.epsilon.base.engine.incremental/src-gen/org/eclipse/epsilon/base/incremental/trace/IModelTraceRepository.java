 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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

import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IIdElementRepository;
/** protected region IModelTraceRepositoryImports on begin **/
/** protected region IModelTraceRepositoryImports end **/

public interface IModelTraceRepository extends IIdElementRepository<IModelTrace> {


    IModelTrace getModelTraceByIdentity(String uri);

/** protected region IModelTraceRepositry on begin **/
    /**
     * Get the model element trace for a given element id within a model trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The name of the model
     * @param modelUri The uri of the model
     * @param modelElementUri The uri of the element for which the trace is being retrieved
     * @return The model element trace, or null if not found
     */
    IModelElementTrace getModelElementTraceFor(String modelUri, String modelElementUri);
    
    /**
     * Get the model type trace for a given type name within a model trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The model name
     * @param modelUri The uri of the model
     * @param typeName The name of the type for which the trace is being retrieved
     * @return The model type trace, or null if not found
     */
    IModelTypeTrace getTypeTraceFor(String modelUri, String typeName);
    
    /**
     * Get the property trace for a given property name within a model element trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The model name
     * @param modelUri The uri of the model
     * @param elementId The model element trace
     * @param propertyName The property trace
     * @return The property trace, or null if not found
     */
    IPropertyTrace getPropertyTraceFor(String modelUri, String elementId, String propertyName);


/** protected region IModelTraceRepositry end **/

}