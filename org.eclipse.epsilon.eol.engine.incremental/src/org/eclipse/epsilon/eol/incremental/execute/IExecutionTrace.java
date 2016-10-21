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

import java.util.Collection;


/**
 * The Interface IExecutionTrace.
 */
public interface IExecutionTrace {
	
	
	/**
	 * Adds a trace for the properties accessed on a specific model element during the execution of a Epsilon module
	 * element (constraint, rule, etc). To support different Epsilon languages the executed module is represented by an
	 * id. For example, in EVL this id could be &lt;context&gt;:&lt;constraint&gt; and in ETL &lt;context&gt;:&lt;ruleName&gt;
	 *
	 * @param moduleElementId the module element id
	 * @param elementId the element id
	 * @param properties the properties' name of properties accessed during execution
	 * @param result True if the traces where added
	 * @return true, if successful
	 */
	public boolean addTraces(String moduleElementId, String elementId, Collection<String> properties, Boolean result);
	
	
	/**
	 * Gets the property trace for the given property and element.
	 *
	 * @param propertyName the property name
	 * @param elementId the element id
	 * @return the property
	 */
	public Object getPropertyTrace(String propertyName, String elementId);
	
	
	/**
	 * Gets the element trace.
	 *
	 * @param elementId the element id
	 * @return the element trace
	 */
	public Object getElementTrace(String elementId);

}