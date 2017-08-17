/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

/**
 * A reference to an ExL Module Element. The module element is referenced by id (name) which is usually
 * unique in the context of the ExL module.
 * 
 * A module element can be executed multiple times in the same execution. Hence, a module element is traced by multiple
 * execution traces.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface IModuleElement extends ITraceComponent {
	
	String getId();
	
	void setId(String id);
	
	/**
	 * Get the execution traces related to this module element.
	 * @return
	 */
	Iterable<IExecutionTrace> getExecutionTraces();
	
	/**
	 * Add an execution trace that traces this module element.
	 * @param executionTrace
	 * @return
	 */
	IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace);
	
	/**
	 * Remove an execution trace that traces this module element.
	 * @param executionTrace
	 */
	void removeExecutionTrace(IExecutionTrace executionTrace);

}
