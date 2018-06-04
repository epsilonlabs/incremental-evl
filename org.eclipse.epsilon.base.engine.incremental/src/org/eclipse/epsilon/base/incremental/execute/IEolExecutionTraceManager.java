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
package org.eclipse.epsilon.base.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
//import org.eclipse.epsilon.base.incremental.trace.util.IExecutionContextRepository;

/**
 * The manager holds the reference to the Trace Model, keeps a queue of model changes and flushes the changes
 * when the queue has a specific size, when the user requests a flush or when the ExL module finishes execution.
 * 
 * An IIncrementalExecutionManager is responsible for creating and managing the different trace model elements that
 * are created during execution of an ExL module. The manager works both as a factory to create traces and as a manager
 * to find traces relevant to specific model elements and properties.
 * 
 * Implementations are free to select the preferred persistence technology for the traces, e.g. a database, EMF
 * Resource, etc. Implementations are also free to implement a transaction mechanism.  
 *  
 *
 * @author Horacio Hoyos Rodriguez
 * @param <T> the generic type of the specific trace repository
 */
public interface IEolExecutionTraceManager<T extends IRepository<?>> {
	
	/**
	 * Instruct the manager to persist the model changes using a parallel execution.
	 *
	 * @param inParallel the new parallel persist
	 */
	void setParallelPersist(boolean inParallel);
	
	/**
	 * Sets the size of the queue at which point the traces will be persisted in the trace model.
	 *
	 * @param size the new flush queue size
	 */
	void setFlushQueueSize(int size);
	
	/**
	 * Set the period time at which point the traces will be persisted in the trace model.
	 * 
	 * @param period the period, in miliseconds, at which the queue will be persisted
	 */
	void setFlushTimeout(float period);
	
	/**
	 * The repository of module executions. Each Epsilon language will return a specialised repository.
	 *
	 * @return the exl module execution repository
	 */
	T getExecutionTraceRepository();
	
	/**
	 * Makes the trace manager persist any temporal trace information in the trace model.
	 *
	 * @return true, if successful
	 */
	boolean persistTraceInformation();

	/**
	 * Gets the property access listener.
	 *
	 * @return the property access listener
	 */
	PropertyAccessExecutionListener getPropertyAccessListener();

	/**
	 * Gets the all instances access listener.
	 *
	 * @return the all instances access listener
	 */
	AllInstancesInvocationExecutionListener getAllInstancesAccessListener();

	// All available repositories
	//IExecutionContextRepository getExecutionContextRepository();
}