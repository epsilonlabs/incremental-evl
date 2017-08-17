/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Refactoring and Consolidation
 *     
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

import java.util.List;

/**
 * An Execution Context references the ExL script and the models that where involved in an execution. Execution
 * traces are valid for a specific execution context.
 * 
 * @author Horacio Hoyos
 *
 */
public interface IExecutionContext {
	
	/**
	 * Get the id of the executed ExL Script.
	 * 
	 * @return the id of the script used in this execution.
	 */
	String getScriptId();
	
	/**
	 * Set the id of the executed ExL Script. The id could be the path, but for distributed applications it is 
	 * preferable to use unique ids. For example, this could be done by assigning an id in the script itself (e.g. 
	 * in a comment or using an annotation).
	 */
	void setScriptId(String scriptId);
	
	/**
	 * Get the models that are part of the execution of the ExL script, identified by id.
	 * 
	 * @return
	 */
	List<String> getModelsIds();
	
	/**
	 * Set the models that are part of the execution of the ExL script.
	 * 
	 * FIXME the model id would probably be the path and the model type (EMF, csv, json, etc.). As with the script the
	 * main reason is that we need to uniquely relate an execution trace to a specific execution.
	 * 
	 * @param models the models 
	 */
	void setModelsIds(List<String> modelsIds);
	
	/**
	 * Get the Execution Traces that are related to the execution of the associated ExL Script on the associated Models.
	 * 
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