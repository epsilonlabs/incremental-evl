 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
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

import org.eclipse.epsilon.base.incremental.execute.IRepository;
/** protected region IModuleExecutionTraceRepositoryImports on begin **/
/** protected region IModuleExecutionTraceRepositoryImports end **/

public interface IModuleExecutionTraceRepository extends IRepository<IModuleExecutionTrace> {

    IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source);

/** protected region IModuleExecutionTraceRepositry on begin **/
    // Specialised search methods
    IPropertyTrace getPropertyTraceFor(IModuleExecutionTrace moduleExecutionTrace, String modelName, String modelId,
			String elementId, String propertyName);

    IModelTypeTrace getTypeTraceFor(String modelName, String modelId, String typeName);

	IModelTrace getModelTraceFor(String name, String modelId);

	IModelElementTrace getModelElementTraceFor(String modelName, String modelId, String elementId);

/** protected region IModuleExecutionTraceRepositry end **/

}