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
package org.eclipse.epsilon.eol.incremental.execute;

import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;

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
 */
public interface IEolExecutionTraceManager {
	
	/**
	 * Instruct the manager to persist the model changes using a parallel execution.
	 * @param inParallel
	 */
	void setParallelPersist(boolean inParallel);
	
	/**
	 * The repository of models. Via the repository it is possible to get access to a specific ModelTrace and from
	 * there find types, elements and properties (see @link {@link ModelUtil}).
	 * @return
	 */
	IModelRepository modelTraces();
	
	/**
	 * The repository of module executions. Each Epsilon language will return a specialised repository.
	 * @return
	 */
	IEolModuleExecutionRepository<? extends IModuleExecution> moduleExecutionTraces();
	
	/**
	 * Sets the size of the queue at which point the traces will be persisted in the trace model.
	 */
	void setFlushQueueSize(int size);
	
	/**
	 * Set the period time at which point the traces will be persisted in the trace model.
	 * 
	 * @param period the period, in miliseconds, at which the queue will be persisted
	 */
	void setFlushTimeout(float period);
	
	/**
	 * Makes the trace manager persist any temporal trace information in the trace model.
	 * @return
	 */
	boolean persistTraceInformation();
	
}