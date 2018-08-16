 /*******************************************************************************
 * This file was automatically generated on: 2018-08-16.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
/** protected region IEvlModuleTraceRepositoryImports on begin **/
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;

/** protected region IEvlModuleTraceRepositoryImports end **/

public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository<IEvlModuleTrace> {


    IEvlModuleTrace getEvlModuleTraceByIdentity(String uri);

/** protected region IEvlModuleTraceRepositry on begin **/
	// Specialised search methods
	Set<IEvlModuleTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri, String elementId,
			String propertyName);

	Set<IEvlModuleTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName);

	Set<IEvlModuleTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace);

	Set<IEvlModuleTrace> getAllExecutionTraces();

	Set<IEvlModuleTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri,
			String elementType, Set<String> allElementTypes);

	/** protected region IEvlModuleTraceRepositry end **/

}