 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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

import java.util.Optional;
import java.util.Collection;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
/** protected region IEvlModuleTraceRepositoryImports on begin **/
import java.util.Iterator;
import java.util.Map;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
/** protected region IEvlModuleTraceRepositoryImports end **/

@SuppressWarnings("unused")
public interface IEvlModuleTraceRepository extends IModuleExecutionTraceRepository<IEvlModuleTrace> {


    Optional<IEvlModuleTrace> getEvlModuleTraceByIdentity(String uri);

/** protected region IEvlModuleTraceRepositry on begin **/
    Iterator<IEvlModuleTrace> getAllModuleTraces();
    
    /**
	 * Find all ExecutionTraces related to an AllInstanceAccess (either by kind or by type) for the
	 * given type name
	 * @param moduleUri		The URI of the module
     * @param modelUri TODO
     * @param typeName		The type name
	 * @return
	 */
	Set<TraceReexecution> findAllInstancesExecutionTraces(
		String moduleUri,
		String modelUri,
		String typeName);
	
	/**
	 * Find all ExecutionTraces related to an AllInstanceAccess for the given type name
	 * @param moduleUri		The URI of the module
	 * @param modelUri TODO
	 * @param typeName		The type name
	 * @param ofKind		If true, then only type accesses are considered. If false, type and kind
	 * 						accesses are considered
	 * @return
	 */
	Set<TraceReexecution> findAllInstancesExecutionTraces(
		String moduleUri,
		String modelUri,
		String typeName,
		boolean ofType);
	
	/**
	 * Find all indirect execution traces for the element. The indirectExecutionTraces are the
	 * traces in which the element, its properties, or its type is accessed, but the element is not
	 * the value of the 'self' ModelElementVariable in the ExecutionContext.
	 * 
	 * This method is used when the element has been deleted from the model, hence, all searches
	 * should start from the ModelElementTrace as we can not retrieve the element's type information
	 * from the model.
	 *
	 * @param moduleUri 			the module uri
	 * @param elementId 			the element id
	 * @param allElementTypes 		all element's types
	 * @return the sets the
	 */
	Set<TraceReexecution> findIndirectExecutionTraces(
		String moduleUri,
		Object elementId,
		Collection<String> allElementTypes);
	
	/**
	 * Find all ExecutionTraces in which the given element is accessed. Access is via element,
	 * property or type.
	 * @param moduleUri
	 * @param elementUri
	 * @param modelUri
	 * @return
	 */
	Set<TraceReexecution> findModelElementExecutionTraces(
		String moduleUri,
		String elementUri,
		String modelUri,
		Set<String> allElementTypes);
	
	/**
	 * Find all ExecutionTraces associated to a PropertyAccess for a given property and element.
	 *
	 * @param moduleUri 			The URI of the module
	 * @param propertyTrace 		the PropertyTrace that represents the model-element-property
	 * @return the sets the
	 */
	Set<TraceReexecution> findPropertyAccessExecutionTraces(
		String moduleUri,
		IPropertyTrace propertyTrace);
	
	/**
	 * Find all ExecutionTraces associated to a PropertyAccess.
	 *
	 * @param pa the pa
	 * @return the sets the
	 */
	Set<TraceReexecution> findPropertyAccessExecutionTraces(IPropertyAccess pa);
	
	/**
	 * Get all property accesses for a given model element
	 * @param elementUri
	 * @param modelUri
	 * @param moduleUri
	 * @return
	 */
	Collection<IPropertyAccess> getPropertyAccessesForElement(
			String elementUri,
			String modelUri,
			String moduleUri);
		
	IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName);

	ICheckResult findResultInCheck(ICheckTrace checkTrace, IExecutionContext currentContext);
	
	IGuardResult findResultInGuard(IGuardTrace guardTrace, IExecutionContext currentContext);
	
	/**
	 * Find an ExecutionContext for the given IContextTrace that matches a set of context variables.
	 * The context variables are passed as a map of name:value pairs, where name should be the
	 * variable name and value a ModelElementTrace id
	 * @param moduleElementTrace
	 * @param keyValues
	 * @return
	 */
	IExecutionContext findExecutionContext(IContextTrace moduleElementTrace, Map<String, Object> contextVariables);
	
	/**
	 * Find the result of a guard trace given a specific execution context
	 * @param gt
	 * @param ec
	 * @return
	 */
	boolean findTraceGuardValue(IGuardTrace gt, IExecutionContext ec);

	/** protected region IEvlModuleTraceRepositry end **/

}