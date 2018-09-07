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

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

/**
 * An EVL Context that keeps a reference to the traceManager and current EVL
 * Execution Trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class IncrementalEvlContext<R extends IEvlModuleTraceRepository, F extends IEvlRootElementsFactory, M extends IEvlExecutionTraceManager<R, F>>
		extends EvlContext implements IIncrementalEvlContext<IEvlModuleTrace, R, F, M> {

	private M traceManager;

	/** The execution access listeners */
	private final PropertyAccessExecutionListener<IEvlModuleTrace, R, M> propertyAccessListener = new PropertyAccessExecutionListener<IEvlModuleTrace, R, M>();
	private final AllInstancesInvocationExecutionListener<IEvlModuleTrace, R, M> allAccessListener = new AllInstancesInvocationExecutionListener<IEvlModuleTrace, R, M>();
	private final SatisfiesInvocationExecutionListener<IEvlModuleTrace, R, M> satisfiesListener = new SatisfiesInvocationExecutionListener<IEvlModuleTrace, R, M>();

	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 * @throws EolIncrementalExecutionException
	 */
	public M getTraceManager() throws EolRuntimeException {
		if (traceManager == null) {
			throw new EolRuntimeException(
					"The trace manager of the context is null. The trace manager must be set via the incremental module.");
		}
		return traceManager;
	}

	/**
	 * Sets the trace Manager.
	 *
	 * @param traceManager the new unit of work
	 */
	public void setTraceManager(M traceManager) {
		this.traceManager = traceManager;
	}

	@Override
	public PropertyAccessExecutionListener<IEvlModuleTrace, R, M> getPropertyAccessExecutionListener() {
		return propertyAccessListener;
	}

	@Override
	public AllInstancesInvocationExecutionListener<IEvlModuleTrace, R, M> getAllInstancesInvocationExecutionListener() {
		return allAccessListener;
	}

	@Override
	public SatisfiesInvocationExecutionListener<IEvlModuleTrace, R, M> getSatisfiesListener() {
		return satisfiesListener;
	}

}
