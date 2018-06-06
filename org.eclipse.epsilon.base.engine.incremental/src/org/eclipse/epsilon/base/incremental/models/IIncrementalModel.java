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
package org.eclipse.epsilon.base.incremental.models;

import java.util.Collection;

import org.eclipse.epsilon.base.incremental.exceptions.models.NotInstantiableModelElementValueException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.base.incremental.trace.impl.MemoryModelTraceFactory;
import org.eclipse.epsilon.eol.exceptions.models.EolNotAModelElementException;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * The Interface IIncrementalModel defines the API for models that support incremental execution, both on-line and 
 * off-line modes. 
 * 
 * FIXME This probably just should be added to the IModel interface
 * 
 * @author Horacio Hoyos
 */
public interface IIncrementalModel extends IModel {
	
	/**
	 * Get the model Id. Id's are required to properly identify traces.
	 *
	 * @return the model id
	 */
	String getModelId();
	
	/**
	 * Supports notifications.
	 *
	 * @return true, if the model can notify of changes.
	 */
	boolean supportsNotifications();
	
	/**
	 * Sets whether this model will deliver notifications to the modules. The notifications are usually
	 * enabled after the initial traces have been executed (e.g. first execution of the ExL script).
	 * For models where listening to all model changes can be expensive, the set of element ids can be used
	 * to just listen to chenges in the elements of interest
	 *
	 * @param deliver the new deliver
	 * @param elementIds the element ids
	 */
	void setDeliver(boolean deliver);
	
	/**
	 * Returns whether this model will deliver notifications to the modules.
	 *
	 * @return true, if notifications are being delivered.
	 */
	boolean isDelivering();
	
	/**
	 * Returns list of the modules associated with this model. The associated modules will receive notifications of
	 * changes in the model.
	 *
	 * @return the modules
	 */
	Collection<IIncrementalModule> getModules();
	
	/**
	 * Return a String representation of the object. If the object belongs to the model, it will return a
	 * serializable version of the object.
	 *
	 * @param instance the instance
	 * @return the string
	 * @throws EolNotAModelElementException if the instance does not belong to the model
	 */
	String convertToString(Object instance) throws NotSerializableModelException;
	
	/**
	 * Craete an object from its string representation. 
	 *
	 * @param value the value
	 * @return the object
	 * @throws UnsupportedOperationException if the value can not be converted to an object.
	 */
	Object createFromString(String value) throws NotInstantiableModelElementValueException;
	
}
