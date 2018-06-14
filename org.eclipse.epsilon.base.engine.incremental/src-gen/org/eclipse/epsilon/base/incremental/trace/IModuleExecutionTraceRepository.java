 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
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

import org.eclipse.epsilon.base.incremental.trace.IIdElementRepository;
/** protected region IModuleExecutionTraceRepositoryImports on begin **/
/** protected region IModuleExecutionTraceRepositoryImports end **/

public interface IModuleExecutionTraceRepository<E extends IModuleExecutionTrace> extends IIdElementRepository<E> {

    IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri);

/** protected region IModuleExecutionTraceRepositry on begin **/
    /**
     * Get the model trace by identity for a given module execution.
     * @param moduleUri The module uri of the module being executed
     * @param modelName The name of the model
     * @param modelUri The uri of the model
     * @return The model trace, or null if not found
     */
    IModelTrace getModelTraceByIdentity(String moduleUri, String modelName, String modelUri);
    
    /**
     * Get the model element trace for a given element id within a model trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The name of the model
     * @param modelUri The uri of the model
     * @param modelElementUri The uri of the element for which the trace is being retrieved
     * @return The model element trace, or null if not found
     */
    IModelElementTrace getModelElementTraceFor(String moduleUri, String modelName, String modelUri, String modelElementUri);
    
    /**
     * Get the model type trace for a given type name within a model trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The model name
     * @param modelUri The uri of the model
     * @param typeName The name of the type for which the trace is being retrieved
     * @return The model type trace, or null if not found
     */
    IModelTypeTrace getTypeTraceFor(String moduleUri, String modelName, String modelUri, String typeName);
    
    /**
     * Get the property trace for a given property name within a model element trace
     * @param moduleUri The module uri of the module being executed
     * @param modelName The model name
     * @param modelUri The uri of the model
     * @param elementId The model element trace
     * @param propertyName The property trace
     * @return The property trace, or null if not found
     */
    IPropertyTrace getPropertyTraceFor(String moduleUri, String modelName, String modelUri, String elementId, String propertyName);

/** protected region IModuleExecutionTraceRepositry end **/

}