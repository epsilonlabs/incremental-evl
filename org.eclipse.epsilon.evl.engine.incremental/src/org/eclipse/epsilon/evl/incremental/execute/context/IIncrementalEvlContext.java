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
package org.eclipse.epsilon.evl.incremental.execute.context;

import org.eclipse.epsilon.base.incremental.execute.ExecutionMode;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

/**
 * The Interface IIncrementalEvlContext allows extra information about incremental
 * execution to be stored and accessed.
 *
 * @param <T> the generic type
 * @param <R> the generic type
 * @param <F> the generic type
 * @param <M> the generic type
 */
public interface IIncrementalEvlContext<
			T extends IEvlModuleTrace,
			R extends IEvlModuleTraceRepository,
			F extends IEvlRootElementsFactory,
			M extends IEvlExecutionTraceManager<R, F>
		> extends IIncrementalBaseContext<T, R, M>, IEvlContext {

	
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

}