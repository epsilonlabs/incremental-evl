/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.Collection;
import java.util.Set;

/**
 * The Interface IOperationInvocations.
 */
public interface IOperationInvocations<T extends IOperationInvocation> {
	
	
	/**
	 * Adds the.
	 *
	 * @param operationInvocation the operation invocation
	 */
	void add(T operationInvocation);
	
	/**
	 * All.
	 *
	 * @return the collection
	 */
	Collection<T> all();
	
	/**
	 * Clear.
	 */
	void clear();
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	boolean isEmpty();
	
	/**
	 * Unique.
	 *
	 * @return the sets the
	 */
	Set<T> unique();

}
