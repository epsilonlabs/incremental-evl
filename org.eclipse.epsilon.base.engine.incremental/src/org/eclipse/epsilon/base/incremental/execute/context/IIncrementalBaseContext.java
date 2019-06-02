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
import org.eclipse.epsilon.base.incremental.execute.ExecutionMode;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

/**
 * The Interface IIncrementalEolContext.
 *
 * @param <T> the type of ExecutionTraceManager (see
 *        {@link IExecutionTrace}
 */
public interface IIncrementalBaseContext extends IEolContext {

	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 * @throws EolIncrementalExecutionException
	 */
	IExecutionTrace getTraceManager() throws EolRuntimeException;
	


	Collection<IExecutionListener> getIncrementalExecutionListeners();
	
	/**
	 * Casts the IModule to IEvlModule
	 * @see org.eclipse.epsilon.eol.execute.context.IEolContext#getModule()
	 * @since 1.6
	 */
	@Override
	default IModuleIncremental getModule() {
		return (IModuleIncremental) this.getModule();
	}
	
	/**
	 * Sets the online execution mode.
	 *
	 * @param onlineExecution the new online execution
	 */
	public void setMode(ExecutionMode mode);

	/**
	 * Checks if is online execution mode is on.
	 *
	 * @return true, if is online execution
	 */
	public boolean isOnlineExecutionMode();
	
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
