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
import java.util.Iterator;

import org.eclipse.epsilon.base.incremental.exceptions.models.NotInstantiableModelElementValueException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.eol.exceptions.models.EolNotAModelElementException;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * The Interface IIncrementalModel defines the API for models that support
 * incremental execution, both on-line and off-line modes.
 * 
 * @author Horacio Hoyos
 */
public interface IIncrementalModel extends IModel {

	/**
	 * Get the model uri. Models must have a uri to properly identify model types,
	 * elements and property traces that belong to this model.
	 *
	 * @return the model id
	 */
	String getModelUri();

	/**
	 * Supports notifications.
	 *
	 * @return true, if the model can notify of changes.
	 */
	boolean supportsNotifications();

	/**
	 * Sets whether this model will deliver notifications to the modules. The
	 * notifications are usually enabled after the initial traces have been executed
	 * (e.g. first execution of the ExL script). For models where listening to all
	 * model changes can be expensive, the set of element ids can be used to just
	 * listen to changes in the elements of interest
	 *
	 * @param deliver    the new deliver
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
	 * Register the module to receive notifications of changes in the model.
	 *
	 * @return true if this collection changed as a result of the call
	 */
	boolean registerModule(IIncrementalModule<?, ?, ?, ?> module);
	
	/**
	 * Determine if the module is registered with this model to receive notifications
	 * @param module
	 */
	boolean isRegistered(IIncrementalModule<?, ?, ?, ?> module);
	
	/**
	 * Unregister the module. The module will stop receiving notifications from changes in this
	 * model
	 * @param element
	 * @param propertyName
	 */
	 boolean unregisterModule(IIncrementalModule<?, ?, ?, ?> module);
	
	// FIXME The incremental model should really do the comparing, notifying itself about its own 
	// elements seems silly 
	void notifyChange(Object element, String propertyName);
	
	void notifyDeletion(Object element);
	
	void notifyCreation(Object element);
	
	
	/**
	 * Return a String representation of the object. If the object belongs to the
	 * model, it will return a serializable version of the object.
	 *
	 * @param instance the instance
	 * @return the string
	 * @throws EolNotAModelElementException if the instance does not belong to the
	 *                                      model
	 */
	String convertToString(Object instance) throws NotSerializableModelException;

	/**
	 * Create an object from its string representation.
	 *
	 * @param value the value
	 * @return the object
	 * @throws UnsupportedOperationException if the value can not be converted to an
	 *                                       object.
	 */
	Object getOrCreateFromString(String value) throws NotInstantiableModelElementValueException;

	/**
	 * Returns the fully qualified names of every type to which the given object
	 * conforms.
	 * 
	 * @param instance The instances which types are retrieved
	 * @return A collection of the names of the types
	 * @throws IllegalArgumentException If the instance is not owned by the model
	 */
	Collection<String> getAllTypeNamesOf(Object instance) throws IllegalArgumentException;
	
	Iterator<Object> getAllElements();
	
}
