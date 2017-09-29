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
package org.eclipse.epsilon.evl.incremental.execute.introspection.recording;


/**
 * The Interface IOperationInvocationAccessRecorder.
 */
public interface IOperationInvocationRecorder {
	
	/**
	 * Gets the operation invocations.
	 *
	 * @return the operation invocations
	 */
	public IOperationInvocations getOperationInvocations();
	
	/**
	 * Start recording.
	 */
	public void startRecording();

	/**
	 * Stop recording.
	 */
	public void stopRecording();
	
	/**
	 * Record.
	 *
	 * @param operationName the operation
	 */
	void record(String operationName);

}
