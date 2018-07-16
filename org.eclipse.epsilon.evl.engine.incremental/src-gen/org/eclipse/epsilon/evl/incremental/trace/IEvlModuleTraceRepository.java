/*******************************************************************************
* This file was automatically generated on: 2018-07-13.
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

import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
/** protected region IEvlModuleTraceRepositoryImports on begin **/
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;

/** protected region IEvlModuleTraceRepositoryImports end **/

public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository<IEvlModuleTrace> {

	IEvlModuleTrace getEvlModuleTraceByIdentity(String uri);

	/** protected region IEvlModuleTraceRepositry on begin. **/

	/**
	 * Find all execution traces for the given property and element. The returned
	 * execution traces will correspond to the execution of Contexts, Invariants,
	 * Guards, Checks and Messages that have at least one property access for the
	 * given element and property.
	 * 
	 * @param moduleSource the module source
	 * @param modelUri     the model uri
	 * @param elementId    the element id
	 * @param propertyName the property name
	 * @return the sets the traces
	 */
	Set<IEvlModuleTrace> findPropertyAccessExecutionTraces(String moduleSource, String modelUri, String elementId,
			String propertyName);

	/**
	 * Find all execution traces for the given type. The returned execution traces
	 * will correspond to the execution of Contexts, Invariants, Guards, Checks and
	 * Messages that have at least one all instances access for the given type.
	 * 
	 * @param moduleUri the uri of the module being executed
	 * @param typeName  the name of the type
	 * 
	 * @return
	 */
	Set<IEvlModuleTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName);

	/**
	 * Find all execution traces that correspond to Invariant executions in which
	 * the given invariant trace was accessed via a satisfies relation.
	 * 
	 * @param invariantTrace
	 * @return
	 */
	Set<IEvlModuleTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace);

	/**
	 * Returns an unmodifiable view of the execution traces
	 * 
	 * @return
	 */
	Set<IEvlModuleTrace> getAllExecutionTraces();

	/**
	 * Gets the execution traces related to an element via any type of access. The
	 * element is referenced by its uri
	 *
	 * @param moduleUri  the uri of the module being executed
	 * @param elementUri the element uri
	 * @param modelUri   the model uri
	 * @return the element traces
	 */
	Set<IEvlModuleTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri,
			String elementType, Set<String> allElementTypes);

	/**
	 * Remove the trace information related to a specific object. This should be
	 * invoked when an element is deleted from the model. The result from this
	 * operation should be a cascade delete: the ModelElementTrace and its
	 * properties (PropertyTrace) should be removed from the model, as well as any
	 * ElementAccess and PropertyAccess that refer to this element and it
	 * properties. Removal of the Access information should remove the associated
	 * ExecutionTrace (ModuleElementTrace) if it does not has any additional access
	 * information.
	 *
	 * @param moduleUri         the uri of the module being executed
	 * @param modelElementTrace the model element trace
	 */
	void removeTraceInformation(String moduleUri, String elementUri, String modelUri);

	/** protected region IEvlModuleTraceRepositry end **/

	IModelTrace getModelTraceForModule(String modelUri, String moduleUri);

}