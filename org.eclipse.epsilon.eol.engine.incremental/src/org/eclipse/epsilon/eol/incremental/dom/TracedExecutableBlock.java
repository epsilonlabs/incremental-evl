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
package org.eclipse.epsilon.eol.incremental.dom;

import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;

/**
 * An executable block that has different type of recorders and that is capable of starting/stopping all its recorders
 * in a single invocation.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T> The expected type of the execution of the block
 */
public class TracedExecutableBlock<T> extends ExecutableBlock<T> {

	/**
	 * A reference to the specific type of ExecutionTrace being traced.
	 * This is necessary because an executable block can represent different elements,
	 * for example in EVL it can be a guard, check, message, etc.
	 */
	private IExecutionTrace trace;

	/**
	 * Gets the trace.
	 *
	 * @return the trace
	 */
	public IExecutionTrace getTrace() {
		return trace;
	}

	/**
	 * Sets the trace.
	 *
	 * @param trace the new trace
	 */
	public void setTrace(IExecutionTrace trace) {
		this.trace = trace;
	}
		
	/**
	 * Instantiates a new traced executable block.
	 *
	 * @param expectedResultClass the expected result class
	 */
	public TracedExecutableBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
	}
	
}
