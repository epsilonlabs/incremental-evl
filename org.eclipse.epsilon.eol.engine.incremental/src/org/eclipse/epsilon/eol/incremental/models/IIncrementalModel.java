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
package org.eclipse.epsilon.eol.incremental.models;

import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;


/**
 * The Interface IIncrementalModel defines the API for models that support incremental execution, both on-line and 
 * off-line modes. 
 * 
 */
public interface IIncrementalModel {
	
	/**
	 * Supports notifications.
	 *
	 * @return true, if the model can notify of changes
	 */
	public boolean supportsNotifications();
	
	/**
	 * Enable notifications. Registered listeners will receive notifications of any changes in the model. 
	 *
	 * @return true, if successful
	 */
	public boolean enableNotifications();
	
	/**
	 * Disable notifications. Registered listeners will stop receiving notifications of any changes in the model. 
	 *
	 * @return true, if successful
	 */
	public boolean disableNotifications();
	
	/**
	 * Adds a listener to the list of listeners of the model.
	 *
	 * @param moduleElement the module element
	 */
	public void addListener(IIncrementalModule moduleElement);
	
	/**
	 * Gets the property value for an element with the given id. 
	 *
	 * @param elementId the element id
	 * @param propertyName the property name
	 * @return the property value
	 */
	public Object getPropertyValue(String elementId, String propertyName); 

}
