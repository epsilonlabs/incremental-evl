 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
/** protected region IEvlModuleTraceRepositoryImports end **/

public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository<IEvlModuleTrace> {


    IEvlModuleTrace getEvlModuleTraceByIdentity(String uri);

/** protected region IEvlModuleTraceRepositry on begin **/
	/**
	 * Find all ExecutionTraces related to the property of the given element
	 * 
	 * @param moduleUri
	 * @param modelUri
	 * @param elementId
	 * @param propertyName
	 * @return
	 */
	Set<IModuleElementTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri, String elementUri,
			String propertyName);

	Set<IModuleElementTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName);

	Set<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace);

	Set<IModuleElementTrace> getAllExecutionTraces();

	/**
	 * Find all indirect execution traces for the element. This method is used when
	 * the element has been deleted from the model, hence, all searches should start
	 * from the ModelElementTrace as we can not retrieve the type information from
	 * the model.
	 * 
	 * The indirectExecutionTraces are the traces in which the element, its
	 * properties, or its type is accessed, but the element itself is not the 'self'
	 * variable in the ExecutionContext.
	 * 
	 */
	Set<IModuleElementTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri);

	Set<IEvlModuleTrace> getAllModuleTraces();

	/**
	 * Find all ModuleTraces in wich the given element is accessed. Access is via element, property
	 * or type.
	 * <p>
	 * @param moduleUri
	 * @param elementUri
	 * @param modelUri
	 * @return
	 */
	Set<IModuleElementTrace> findAllExecutionTraces(String moduleUri, String elementUri, String modelUri);

	IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace);

	IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName);

	/** protected region IEvlModuleTraceRepositry end **/

}