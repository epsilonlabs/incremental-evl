 /*******************************************************************************
 * This file was automatically generated on: 2019-02-07.
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
import org.eclipse.epsilon.evl.incremental.IReexecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;

/** protected region IEvlModuleTraceRepositoryImports on begin **/
import java.util.Collection;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
/** protected region IEvlModuleTraceRepositoryImports end **/

public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository {


    IEvlModuleTrace getEvlModuleTraceByIdentity(String uri);

/** protected region IEvlModuleTraceRepositry on begin **/
     
	Set<IEvlModuleTrace> getAllModuleTraces();
	
	IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace);
    
    /**
	 * Find all ExecutionTraces related to an AllInstanceAccess (either by kind or by type) for the
	 * given type name
	 * @param moduleUri		The URI of the module
     * @param modelUri TODO
     * @param typeName		The type name
	 * @return
	 */
	Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String modelUri, String typeName);
	
	/**
	 * Find all ExecutionTraces related to an AllInstanceAccess for the given type name
	 * @param moduleUri		The URI of the module
	 * @param modelUri TODO
	 * @param typeName		The type name
	 * @param ofKind		If true, then only type accesses are considered. If false, type and kind
	 * 						accesses are considered
	 * @return
	 */
	Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String modelUri, String typeName, boolean ofType);
	
	/**
	 * Find all indirect execution traces for the element. The indirectExecutionTraces are the
	 * traces in which the element, its properties, or its type is accessed, but the element is not
	 * the value of the 'self' ModelElementVariable in the ExecutionContext.
	 * 
	 * This method is used when the element has been deleted from the model, hence, all searches
	 * should start from the ModelElementTrace as we can not retrieve the element's type information
	 * from the model.
	 */
	Set<IReexecutionTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri);
	
	/**
	 * Find all ExecutionTraces in which the given element is accessed. Access is via element,
	 * property or type.
	 * @param moduleUri
	 * @param elementUri
	 * @param modelUri
	 * @return
	 */
	Set<IReexecutionTrace> findModelElementExecutionTraces(String moduleUri, String elementUri, String modelUri);
	
	/**
	 * Find all ExecutionTraces associated to a PropertyAccess for a given property and element.
	 * 
	 * @param moduleUri		The URI of the module
	 * @param modelUri		The URI of the model
	 * @param elementId		The element id
	 * @param propertyName	The property name
	 * @return
	 */
	Set<IReexecutionTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri,
			String elementUri, String propertyName);
	
	/**
	 * Find all ExecutionTraces associated to a PropertyAccess.
	 *
	 * @param pa the pa
	 * @return the sets the
	 */
	Set<IReexecutionTrace> findPropertyAccessExecutionTraces(IPropertyAccess pa);

	
	IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName);

	ICheckResult findResultInCheck(ICheckTrace checkTrace, IExecutionContext currentContext);
	
	IGuardResult findResultInGuard(IGuardTrace guardTrace, IExecutionContext currentContext);

	Collection<IPropertyAccess> getPropertyAccessesForElement(
		String elementUri,
		String modelUri,
		String moduleUri);
	
	
	
	// Collection<IModelElementTrace> getAllModelElementTraces(String modelUri);
	
	/**
	 * Get all model element traces from the model. Elements with id included the filteredIds set
	 * will be excluded.
	 *
	 * @param moduleUri 			the module uri
	 * @param modelUri 				the model uri
	 * @param filteredIds 			the set of ids to be filtered (excluded)
	 * @return the model element traces
	 */
	Collection<IModelElementTrace> getModelElementTraces(String moduleUri, String modelUri, Set<String> filteredIds);



	/** protected region IEvlModuleTraceRepositry end **/

}