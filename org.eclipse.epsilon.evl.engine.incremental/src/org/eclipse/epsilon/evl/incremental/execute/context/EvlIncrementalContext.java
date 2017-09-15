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
package org.eclipse.epsilon.evl.incremental.execute.context;

import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;

public class EvlIncrementalContext extends EvlContext implements IEvlIncrementalContext {
	
	protected IExecutionTraceManager traceManager;
	
	public EvlIncrementalContext() {
		super();
	}

	@Override
	public void setTraceManager(IExecutionTraceManager traceManager) {
		
		this.traceManager = traceManager;

	}

	@Override
	public IExecutionTraceManager getExecutionTraceManager() {
		return traceManager;
	}
	
	@Override
	public IncrementalEvlModule getModule(){
		return (IncrementalEvlModule) module;
	}

}
