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
package org.eclipse.epsilon.evl.execute.context;

import org.eclipse.epsilon.eol.dom.TypeExpression;
import org.eclipse.epsilon.eol.execute.introspection.recording.IPropertyAccessRecorder;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.execute.context.EvlContext;

public class EvlIncrementalContext extends EvlContext implements IEvlIncrementalContext {
	
	protected IEolExecutionTraceManager traceManager;
	
	public EvlIncrementalContext() {
		super();
	}

	@Override
	public void setTraceManager(IEolExecutionTraceManager traceManager) {
		
		this.traceManager = traceManager;

	}

	@Override
	public IEolExecutionTraceManager getExecutionTraceManager() {
		return traceManager;
	}
	
	@Override
	public IncrementalEvlModule getModule(){
		return (IncrementalEvlModule) module;
	}

	@Override
	public void addPropertyAccessTraces(IPropertyAccessRecorder recorder) {
		// TODO Implement IEvlIncrementalContext.addPropertyAccessTraces
		throw new UnsupportedOperationException("Unimplemented Method    IEvlIncrementalContext.addPropertyAccessTraces invoked.");
	}

	@Override
	public void addTypeAccessTrace(TypeExpression typeExpression) {
		// TODO Implement IEvlIncrementalContext.addTypeAccessTrace
		throw new UnsupportedOperationException("Unimplemented Method    IEvlIncrementalContext.addTypeAccessTrace invoked.");
	}

}
