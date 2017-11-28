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
package org.eclipse.epsilon.eol.incremental.execute;

import org.eclipse.epsilon.eol.execute.context.IEolContext;

/**
 * An EOL Context that is aware of an {@link IEolExecutionTraceManager}. 
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface IEolIncrementalContext extends IEolContext {
	
	/** Set the execution trace manager for this context */
	void setExecutionTraceManager(IEolExecutionTraceManager<?> traceManager);
	
	/** Get the execution trace manager of this context */
	IEolExecutionTraceManager<?> getExecutionTraceManager();

}
