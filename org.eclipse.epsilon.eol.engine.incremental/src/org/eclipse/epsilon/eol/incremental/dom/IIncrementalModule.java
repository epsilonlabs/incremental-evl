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

/**
 * The Interface IIncrementalModuleElement. Incremental Modules can be attached to IIncrementalModel in order to be
 * notified of changes in the model and execute specific elements/sections of the module that are related to the object
 * and its changes. 
 */
public interface IIncrementalModule {
	
	/**
	 * On change. Implementations would usually query the execution trace model/store to test if the given property
	 * of the object has a trace. If so, all elements of the module that are related to the property should be 
	 * re-executed.
	 *
	 * @param notifier the notifier
	 * @param propertyName the property name
	 */
	public void onChange(String elementId, Object element, String propertyName);
	
	/**
	 * On create. Implementations would usually find the type of the newly created element and re-execute any elements
	 * of the module that apply to this type. 
	 *
	 * @param notifier the notifier
	 */
	public void onCreate(Object newElement);
	
	/**
	 * On delete. Implementations would usually query the execution trace model/store to test if the given object has a
	 * trace. If so, all elements of the module that are related to the traced properties of the object should be
	 * re-executed.
	 *
	 * @param notifier the notifier
	 * @param propertyName the property name
	 */
	public void onDelete(String elementId, Object element, String propertyName);

}
