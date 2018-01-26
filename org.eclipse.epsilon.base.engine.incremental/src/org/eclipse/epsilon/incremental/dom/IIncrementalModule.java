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
package org.eclipse.epsilon.incremental.dom;

import java.util.Set;

import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleTrace;

/**
 * Incremental Modules can be attached to incremental models (models that implement IIncrementalModel) in order to be
 * notified of changes in the model and execute specific elements/sections of the module that are related to the
 * objects that changed and their changes.
 * 
 * Implementations need to create an instance of an {@link IEolExecutionTraceManager} that will be used to manage
 * the execution traces for the module.
 * 
 * An Incremental Module should not be executed completely all the time. Instead it should be executed completely once
 * in order to create all the trace information. After that, the module should only respond to the changes in the
 * models associated with the initial execution. For this, the module should attach itself as a listener to all the
 * models used during execution. 
 * 
 * @author Horacio Hoyos Rodriguez
 */
public interface IIncrementalModule {
	
	public static final String ONLINE_MODE = "online";
	
	/**
	 * Return the ExecutionTrace for the execution
	 * @return
	 */
	public IModuleExecution getModuleExecution();
	
	/** 
	 * Return the ExecutionTrace for the executed module
	 * @return
	 */
	public IModuleTrace getModuleTrace();
	
	/**
	 * Returns the set of models from which the module receives notification.
	 * @return
	 */
	public Set<IIncrementalModel> getTargets();
	
	/**
	 * Set a flag to listen or not to model changes.
	 */
	public void listenToModelChanges(boolean listen);
	
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
	
}

