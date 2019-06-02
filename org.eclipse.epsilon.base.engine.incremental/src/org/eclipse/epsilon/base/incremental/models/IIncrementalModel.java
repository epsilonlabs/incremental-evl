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

import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
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
	boolean registerModule(IModuleIncremental module);
	
	/**
	 * Determine if the module is registered with this model to receive notifications
	 * @param module
	 */
	boolean isRegistered(IModuleIncremental module);
	
	/**
	 * Unregister the module. The module will stop receiving notifications from changes in this
	 * model
	 * @param element
	 * @param propertyName
	 */
	 boolean unregisterModule(IModuleIncremental module);
	
	// FIXME The incremental model should really do the comparing, notifying itself about its own 
	// elements seems silly 
	void notifyChange(Object element, String propertyName);
	
	void notifyDeletion(Object element);
	
	void notifyCreation(Object element);
	
	
	/**
	 * Return a "serialisable" representation of the property value that can be easily stored
	 * in the underlying Tinkerpop Graph. This method is mainly aimed towards providing 
	 * representations for model elements and collections of model elements. As such, any non-model
	 * values could simple be returned unmodified. 
	 *
	 * @param propertyValue		the property value
	 * @param propertyName 		the property name
	 * @param modelElement 		the model element
	 * @return the string
	 * @throws EolNotAModelElementException if the property value is not serialisable by this model
	 */
	Object serializePropertyValue(Object propertyValue, Object modelElement, String propertyName) throws NotSerializableModelException;

	/**
	 * Create a property value from its "serialisable" representation.
	 *
	 * @param value the value
	 * @return the object
	 * @throws UnsupportedOperationException if the value can not be converted to an
	 *                                       object.
	 */
	// Object deserializePropertyValue(Object value) throws NotInstantiableModelElementValueException;
	
	/**
	 * Compare the element's property current value with the oldValue. Return true if the values
	 * are the same.
	 * 
	 * @param modelElement		The element that has the property
	 * @param propertyName		The property name
	 * @param newValue			The new value
	 * @return	True, if the values are equal
	 */
	boolean comparePropertyValue(Object modelElement, String propertyName, Object oldValue);

	/**
	 * Returns the fully qualified names of every type to which the given object
	 * conforms.
	 * 
	 * @param instance The instances which types are retrieved
	 * @return A collection of the names of the types
	 * @throws IllegalArgumentException If the instance is not owned by the model
	 */
	Collection<String> getAllTypeNamesOf(Object instance) throws IllegalArgumentException;
	
	Iterator<? extends Object> getAllElements();
	
}
