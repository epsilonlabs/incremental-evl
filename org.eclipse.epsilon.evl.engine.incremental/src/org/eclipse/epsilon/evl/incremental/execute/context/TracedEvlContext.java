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


import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * An EVL Context that keeps a reference to the traceManager and current EVL Execution Trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedEvlContext extends EvlContext {
	
	/** The trace manager. */
	private IEvlExecutionTraceManager traceManager;
	private IEvlModuleTrace evlModuleTrace;
	
	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 */
	public IEvlExecutionTraceManager getTraceManager() {
		return traceManager;
	}
	
	/**
	 * Sets the trace Manager.
	 *
	 * @param traceManager the new unit of work
	 */
	public void setTraceManager(IEvlExecutionTraceManager traceManager) {
		this.traceManager = traceManager;
	}
	
	/**
	 * Gets the evl module trace.
	 *
	 * @return the evl modeule trace
	 */
	public IEvlModuleTrace getEvlModuleTrace() {
		return evlModuleTrace;
	}
	
	/**
	 * Sets the evl module trace.
	 *
	 * @param evlModuleTrace the new evl module trace
	 */
	public void setEvlModuleTrace(IEvlModuleTrace evlModuleTrace) {
		this.evlModuleTrace = evlModuleTrace;
	}

}
