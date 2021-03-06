/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.execute.context;

import java.util.Collection;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

/**
 * The Interface IIncrementalEolContext.
 *
 * @param <T> the type of ExecutionTraceManager (see
 *        {@link IExecutionTraceManager}
 */
public interface IIncrementalBaseContext<
		T extends IModuleExecutionTrace,
		R extends IModuleExecutionTraceRepository<?>,
		M extends IExecutionTraceManager<?, ?, ?>
	> extends IEolContext {

	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 * @throws EolIncrementalExecutionException
	 */
	M getTraceManager() throws EolRuntimeException;

	/**
	 * Sets the trace manager.
	 *
	 * @param traceManager the new trace manager
	 */
	void setTraceManager(M traceManager);

	Collection<IExecutionListener> getIncrementalExecutionListeners();
	
	/**
	 * Gets the property access execution listener.
	 *
	 * @return the property access execution listener
	 */
	//PropertyAccessExecutionListener<T, R, M> getPropertyAccessExecutionListener();

	/**
	 * Gets the all instances invocation execution listener.
	 *
	 * @return the all instances invocation execution listener
	 */
	//AllInstancesInvocationExecutionListener<T, R, M> getAllInstancesInvocationExecutionListener();

}
