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

import org.eclipse.epsilon.base.incremental.IBaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;

/**
 * The trace manager is responsible form provisioning the required repositories
 * and for implementing different persistence action strategies. That is, how
 * and when to flush changes from memory to a long term location.
 * 
 * By providing different repositories, particular implementations can store the
 * trace information in different formats. Further, the can also pick queueing,
 * flushing and scheduling implementations that work better for a particular
 * technology. Further, some of this parameters can be made available to the
 * user so it can fine tune its execution.
 *
 * @author Horacio Hoyos Rodriguez
 * @param <T> the generic type of the ModuleTrace
 * @param <R> the generic type of the specific module execution trace repository
 *        {@link IModuleExecutionTrace}
 * @param <F> the generic type of the RootElements Factory
 */
public interface IExecutionTrace {

	/**
	 * Instruct the manager to persist the model changes using a parallel execution.
	 *
	 * @param inParallel the new parallel persist
	 */
	void setParallelPersist(boolean inParallel);

	/**
	 * Sets the size of the queue at which point the traces will be persisted in the
	 * trace model.
	 *
	 * @param size the new flush queue size
	 */
	void setFlushQueueSize(int size);

	/**
	 * Set the period time at which point the traces will be persisted in the trace
	 * model.
	 * 
	 * @param period the period, in miliseconds, at which the queue will be
	 *               persisted
	 */
	void setFlushTimeout(float period);

	/**
	 * Makes the trace manager persist any temporal trace information in the trace
	 * model.
	 *
	 * @return true, if successful
	 */
	boolean persistTraceInformation();

	/**
	 * The repository of module executions. Each Epsilon language will return a
	 * specialised repository that provides specialised ModuleExecution types.
	 *
	 * @return the module execution repository
	 */
	IModuleExecutionTraceRepository getExecutionTraceRepository();

	/**
	 * The repository of models.
	 * 
	 * @return
	 */
	IModelTraceRepository getModelTraceRepository();

	/**
	 * A factory for creating root elements.
	 * 
	 * @return
	 */
	IBaseRootElementsFactory getTraceFactory();

}