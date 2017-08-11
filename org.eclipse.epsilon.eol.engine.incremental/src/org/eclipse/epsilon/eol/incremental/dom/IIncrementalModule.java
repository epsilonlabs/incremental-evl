/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.dom;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

/**
 * Incremental Modules can be attached to incremental models (models that implement IIncrementalModel) in order to be
 * notified of changes in the model and execute specific elements/sections of the module that are related to the
 * objects that changed and their changes. 
 * 
 * @author Horacio Hoyos
 */
public interface IIncrementalModule {
	
	/**
	 * Called to notify the module that the model has changed. Implementations would usually query the execution trace
	 * model/store to test if the given property of the objcet that changed has a trace. If so, all elements of the
	 * module that are related to the object+property should be re-executed.
	 *
	 * @param objectId the id of the object that changed
	 * @param object the object that changed
	 * @param propertyName the name of the property that changed
	 */
	public void onChange(String objectId, Object object, String propertyName);
	
	/**
	 * Called to notify the module that a new object has been created in the model. Implementations would usually find
	 * the type of the newly created object and re-execute any statements of the module that are related to its type.
	 *
	 * @param object the object that has been created
	 */
	public void onCreate(Object object);
	
	/**
	 * Called to notify the module that an object has been deleted from the model. Implementations would usually
	 * query the execution trace model/store to test if the given object has a trace. If so, all elements of the
	 * module that are related to the traced properties of the object should be re-executed.
	 *
	 * @param objectId the id of the object that was deleted
	 * @param object the object that was deleted
	 * 
	 */
	public void onDelete(String objectId, Object object);

	/**
	 * Create an ID for a module element
	 * 
	 * @param moduleElement the ModuleElement for which an id must be created 
	 * @return The ID for the module
	 * @throws EolRuntimeException if the specific type of the ModuleElement is not supported by this module
	 */
	public String getModuleElementId(ModuleElement moduleElement) throws EolRuntimeException;

	/**
	 * Get a module element by ID
	 * @param moduleElementId The ID of the element
	 * @return The module element, null if the ID is not known to this module.
	 */
	public ModuleElement getModuleElementById(String moduleElementId);
}
