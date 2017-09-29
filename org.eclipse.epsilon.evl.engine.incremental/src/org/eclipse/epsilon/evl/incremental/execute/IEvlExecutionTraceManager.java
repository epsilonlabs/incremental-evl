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
package org.eclipse.epsilon.evl.incremental.execute;

import java.util.Set;

import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;

public interface IEvlExecutionTraceManager extends IEolExecutionTraceManager {
	
	/**
	 * During execution the manager queues the execution trace information. The trace information will be stored in 
	 * the trace model after execution. Alternatively, the user can request that the temporal information be persisted.
	 * Implementations are also free to provide additional strategies, e.g. persist information when the queue reaches
	 * a given size.
	 *
	 * @param moduleElementID the moduleElement ID
	 * @param modelId the model id
	 * @param modelElementId the modelElement id
	 * @param property the property
	 * @param satisfiesModuleElementsIDs the ids of the module dependencies
	 * @return true, if successful
	 */
	boolean addTraceInformation(String moduleElementID, String modelId, String modelElementId, String property,
			Set<String> satisfiesModuleElementsIDs);

}
