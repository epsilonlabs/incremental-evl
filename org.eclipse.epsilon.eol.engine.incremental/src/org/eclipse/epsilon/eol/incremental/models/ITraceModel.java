/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.models;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.incremental.trace.eol.ExecutionContext;
import org.eclipse.epsilon.incremental.trace.eol.Trace;

/**
 * An ITraceModel extends the ITraceModelBase to provide additional search/modify methods that can not be
 * easily generated from the ExecutionTrace metamodel. For example, find an element by using heterogeneous combinations
 * of attributes and references. 
 * 
 * @author Horacio Hoyos
 *
 */
public interface ITraceModel extends IModel {
	
	/**
	 * Allows instantiation of an element by type with a given set of parameters. Rather than by order (see {@link IModel#createInstance(String, Collection)})
	 * this method allow parameters to be identified by name.
	 * 
	 * @param type the type to instantiate
	 * @param parameters a map of attributeName:value used for instantiation
	 * @return the new object
	 * @throws EolModelElementTypeNotFoundException
	 * @throws EolNotInstantiableModelElementTypeException
	 */
	public Object createInstance(String type, Map<String, Object> parameters) throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException;
	
	/**
	 * Convenience method to encapsulate the create-if-not-found logic. Given that this might also require the creation
	 * of the Script and Model entries, implementations targeting multi-threaded execution should take the necessary
	 * provisions to ensure that elements are created only once.
	 *    
	 * @param scriptId
	 * @param modelsUris
	 * @return
	 */
	ExecutionContext acquireExecutionContext(String scriptId, List<String> modelsUris);
	
	/**
	 * Find all traces for the given element and property. This method assumes that it is invoked in the context of
	 * an existing ExecutionContext that will be used to filter relevant traces. 
	 * 
	 * @param elementId
	 * @param propertyId
	 * @return A list of traces that involve the element and access the property
	 */
	List<Trace> findTraces(String elementId, String propertyId);
	
	/**
	 * Find all traces for the given element. This method assumes that it is invoked in the context of
	 * an existing ExecutionContext that will be used to filter relevant traces. 
	 * 
	 * @param elementId
	 * @return A list of traces that involve the element
	 */
	List<Trace> findTraces(String elementId);
	
	// FIXME Add methods for offline compare. This methods would also need to check changes in properties. 

}

