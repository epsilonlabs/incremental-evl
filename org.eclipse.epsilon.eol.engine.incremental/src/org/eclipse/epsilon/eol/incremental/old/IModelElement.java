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

import java.util.Collection;
import java.util.List;


/**
 * The {@link IModelElement} interface represents a model element from a model used during execution of an ExL
 * script.
 * 
 * @author Jonathan Co
 *
 */
public interface IModelElement extends ITraceComponent {

	/**
	 * Gets the element id.
	 *
	 * @return The id of this model element
	 */
	String getElementId();

	/**
	 * Set the id of this model element.
	 *
	 * @param elementId            The new id
	 */
	void setElementId(String elementId);

	/**
	 * Get the execution traces that reference this model element.
	 * 
	 * @return An {@link Collection} containing all the relevant scopes.
	 */
	List<IExecutionTrace> getExecutionTraces();
	
	/**
	 * Add an execution traces that references this model element
	 * 
	 * @param executionTrace
	 * @return
	 */
	IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace);
	
	/**
	 * Remove an execution traces that references this model element
	 * 
	 * @param executionTrace
	 */
	void removeExecutionTrace(IExecutionTrace executionTrace);
	
}
