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

import java.util.Collection;

import org.eclipse.epsilon.eol.incremental.execute.IModuleIncremental;
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
	 */
	String getModelId();
	
	/**
	 * Supports notifications.
	 *
	 * @return true, if the model can notify of changes.
	 */
	boolean supportsNotifications();
	
	/**
	 * Sets whether this model will deliver notifications to the modules. The notifications are usually enabled after
	 * the intial traces have been executed (e.g. first execution of the ExL script).
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
	 * @return
	 */
	Collection<IModuleIncremental> getModules();
	
}
