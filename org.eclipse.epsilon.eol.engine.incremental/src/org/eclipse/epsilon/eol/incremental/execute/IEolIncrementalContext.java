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

public interface IEolIncrementalContext extends IEolContext {

	void setExecutionTraceManager(IEolExecutionTraceManager traceManager);
	
	IEolExecutionTraceManager getExecutionTraceManager();

}
