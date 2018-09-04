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

import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IIdElementRepository;
/** protected region IModuleExecutionTraceRepositoryImports on begin **/
/** protected region IModuleExecutionTraceRepositoryImports end **/

public interface IModuleExecutionTraceRepository<E extends IModuleExecutionTrace> extends IIdElementRepository<E> {


    IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri);

/** protected region IModuleExecutionTraceRepositry on begin **/
	/**
	 * Remove all trace information related to the element. This should be called
	 * when an element is deleted.
	 * 
	 * @param moduleUri
	 * @param elementUri
	 * @param modellUri
	 */
	void removeTraceInformation(String moduleUri, String elementUri, String modellUri);

	/**
	 * Get the model trace information of a model used within a module execution.
	 * 
	 * @param modelUri
	 * @param moduleUri
	 * @return
	 */
	IModelTrace getModelTraceForModule(String modelUri, String moduleUri);

	/** protected region IModuleExecutionTraceRepositry end **/

}