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

import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;

/**
 * An EVL Context that keeps a reference to the traceManager and current EVL Execution Trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class IncrementalEvlContext extends EvlContext 
	implements IIncrementalEvlContext<IEvlExecutionTraceManager<?>> {
	
	private IEvlExecutionTraceManager<?> traceManager;
	
	/** The execution access listeners */
	private PropertyAccessExecutionListener propertyAccessListener = new PropertyAccessExecutionListener();
	private AllInstancesInvocationExecutionListener allAccessListener = new AllInstancesInvocationExecutionListener();
	private SatisfiesInvocationExecutionListener satisfiesListener = new SatisfiesInvocationExecutionListener();
	
	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 */
	public IEvlExecutionTraceManager<?> getTraceManager() {
		return traceManager;
	}
	
	/**
	 * Sets the trace Manager.
	 *
	 * @param traceManager the new unit of work
	 */
	public void setTraceManager(IEvlExecutionTraceManager<?> traceManager) {
		this.traceManager = traceManager;
	}

	@Override
	public PropertyAccessExecutionListener getPropertyAccessExecutionListener() {
		return propertyAccessListener;
	}

	@Override
	public AllInstancesInvocationExecutionListener getAllInstancesInvocationExecutionListener() {
		return allAccessListener;
	}
	
	@Override
	public SatisfiesInvocationExecutionListener getSatisfiesListener() {
		return satisfiesListener;
	}
	


}
