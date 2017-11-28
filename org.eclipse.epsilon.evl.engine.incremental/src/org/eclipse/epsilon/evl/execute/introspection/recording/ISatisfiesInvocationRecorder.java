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

import org.eclipse.epsilon.eol.incremental.execute.introspection.recording.IRecorder;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;

/**
 * The Interface IOperationInvocationAccessRecorder.
 */
@Deprecated
public interface ISatisfiesInvocationRecorder extends IRecorder<ISatisfiesTrace> {
	
	/**
	 * Record execution of an operation.
	 * 
	 * @param operationName the operation
	 * @param invariantNames The parameters' values used for invocation
	 */
	void record(boolean all, Collection<String> invariantNames);

}
