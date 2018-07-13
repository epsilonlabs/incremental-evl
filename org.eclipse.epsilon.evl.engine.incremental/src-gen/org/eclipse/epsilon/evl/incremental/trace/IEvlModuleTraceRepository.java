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
package org.eclipse.epsilon.evl.incremental.trace;


import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
/** protected region IEvlModuleTraceRepositoryImports on begin **/
import java.util.Set;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
/** protected region IEvlModuleTraceRepositoryImports end **/

public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository<IEvlModuleTrace> {

    IEvlModuleTrace getEvlModuleTraceByIdentity(String uri);

/** protected region IEvlModuleTraceRepositry on begin **/
	/**
	 * Find all execution traces for the given property and element. The returned execution traces will 
	 * correspond to the execution of Contexts, Invariants, Guards, Checks and Messages that have at least
	 * one property access for the given element and property.
	 * @param moduleUri the uri of the module being executed
	 * @param propertyName the property name
	 * @param modelElementTrace the model element that contains the property
	 * @throws EolIncrementalExecutionException 
	 */
    Set<IEvlModuleTrace> findPropertyAccessExecutionTraces(String moduleUri, IModelElementTrace modelElementTrace, String propertyName);
	
	/**
	 * Find all execution traces for the given type. The returned execution traces will correspond to the
	 * execution of Contexts, Invariants, Guards, Checks and Messages that have at least one all instances
	 * access for the given type.
	 * @param moduleUri the uri of the module being executed
	 * @param typeName the name of the type 
	 * 
	 * @return
	 */
    Set<IEvlModuleTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName);
	
	/**
	 * Find all execution traces that correspond to Invariant executions in which the given invariant trace
	 * was accessed via a satisfies relation.
	 * 
	 * @param invariantTrace
	 * @return
	 */
    Set<IEvlModuleTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace);
	
	/**
	 * Returns an unmodifiable view of the execution traces
	 * @return
	 */
	Set<IEvlModuleTrace> getAllExecutionTraces();
	
	
	/**
	 * Gets the execution traces related to this element via any type of access.
	 * 
	 * @param moduleUri the uri of the module being executed
	 * @param modelElementTrace the model object uri
	 *
	 * @return the element trace
	 */
	Set<IEvlModuleTrace> findIndirectExecutionTraces(String moduleUri, IModelElementTrace modelElementTrace,
			Object modelElement, IIncrementalModel model);


	/**
	 * Remove the trace information related to a specific object. This should be invoked when an element is deleted
	 * from the model. The result from this operation should be a cascade delete: the ModelElementTrace and 
	 * its properties (PropertyTrace) should be removed from the model, as well as any ElementAccess and 
	 * PropertyAccess that refer to this element and it properties. Removal of the Access information should
	 * remove the associated ExecutionTrace (ModuleElementTrace) if it does not has any additional access
	 * information.
	 *
	 * @param moduleUri the uri of the module being executed
	 * @param model the model
	 * @param modelElementTrace the model element trace
	 */
	void removeTraceInformation(String moduleUri, IIncrementalModel model, IModelElementTrace modelElementTrace);
/** protected region IEvlModuleTraceRepositry end **/

}