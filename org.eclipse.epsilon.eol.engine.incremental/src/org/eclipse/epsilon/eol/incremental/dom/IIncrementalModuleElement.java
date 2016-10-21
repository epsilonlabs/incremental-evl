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
package org.eclipse.epsilon.eol.incremental.dom;

import java.util.Collection;

import org.eclipse.epsilon.eol.incremental.execute.IExecutionTrace;


/**
 * The Interface IIncrementalModuleElement.
 */
public interface IIncrementalModuleElement {
	
	/**
	 * On change.
	 *
	 * @param notifier the notifier
	 * @param propertyName the property name
	 * @return the collection
	 */
	public Collection<IExecutionTrace> onChange(Object notifier, String propertyName);
	
	/**
	 * On create.
	 *
	 * @param notifier the notifier
	 */
	public void onCreate(Object notifier);
	
	/**
	 * On delete.
	 *
	 * @param notifier the notifier
	 * @param propertyName the property name
	 * @return the collection
	 */
	public Collection<IExecutionTrace> onDelete(Object notifier, String propertyName);

}
