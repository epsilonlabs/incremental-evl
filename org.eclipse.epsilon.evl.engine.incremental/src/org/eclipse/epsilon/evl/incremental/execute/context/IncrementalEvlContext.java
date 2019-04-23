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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.execute.ExecutionMode;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;

/**
 * An EVL Context that keeps a reference to the traceManager and current EVL
 * Execution Trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class IncrementalEvlContext extends EvlContext implements IIncrementalEvlContext {

	private final IEvlExecutionTraceManager traceManager;

	/** The execution access listeners */
	private final PropertyAccessExecutionListener propertyAccessListener = new PropertyAccessExecutionListener();
	private final AllInstancesInvocationExecutionListener allAccessListener = new AllInstancesInvocationExecutionListener();
	private final SatisfiesInvocationExecutionListener satisfiesListener = new SatisfiesInvocationExecutionListener();
	
	private List<IExecutionListener> executionListeners;
	
	/**
	 * Flag to indicate that we are on live mode, i.e. listening to model changes
	 */
	private ExecutionMode mode;
	
	
	public IncrementalEvlContext(IEvlExecutionTraceManager traceManager) {
		super();
		this.traceManager = traceManager;
	}

	@Override
	public IEvlModuleIncremental getModule() {
		return (IEvlModuleIncremental) module;
	}

	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 * @throws EolIncrementalExecutionException
	 */
	public IEvlExecutionTraceManager getTraceManager() throws EolRuntimeException {
		return traceManager;
	}

	@Override
	public Collection<IExecutionListener> getIncrementalExecutionListeners() {
		if (executionListeners == null) {
			executionListeners = new ArrayList<>();
			executionListeners.add(propertyAccessListener);
			executionListeners.add(allAccessListener);
			executionListeners.add(satisfiesListener);
		}
		return executionListeners;
	}

	@Override
	public void setMode(ExecutionMode mode) {
		this.mode = mode;
	}

	@Override
	public boolean isOnlineExecutionMode() {
		return ExecutionMode.online.equals(mode);
	}

}
