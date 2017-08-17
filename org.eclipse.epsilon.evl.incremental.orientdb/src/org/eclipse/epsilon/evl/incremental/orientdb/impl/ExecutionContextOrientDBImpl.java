/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.old.IExecutionContext;
import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;

import com.tinkerpop.blueprints.Vertex;

/**
 * The Class ContextTraceOrientDBImpl.
 */
public class ExecutionContextOrientDBImpl implements IExecutionContext {

	/** The delegate. */
	final private NExecutionContext delegate;
	
	/**
	 * Instantiates a new context trace orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	// Wrap TCOntext in a IContextTrace
	public ExecutionContextOrientDBImpl(NExecutionContext delegate) {
		this.delegate = delegate;
	}
	
	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public NExecutionContext getDelegate() {
		return delegate;
	}

	@Override
	public String getScriptId() {
		return delegate.getScriptId();
	}

	@Override
	public void setScriptId(String scriptId) {
				
		delegate.setScriptId(scriptId);
	}

	@Override
	public List<String> getModelsIds() {
		return delegate.getModelsIds();
	}

	@Override
	public void setModelsIds(List<String> modelsIds) {
		delegate.setModelsIds(modelsIds);
		
	}

	@Override
	public Collection<IExecutionTrace> getExecutionTraces() {
		Iterable<NExecutionTrace> myIterable;
		ArrayList<IExecutionTrace> myIConstraintTraceIterable = new ArrayList<IExecutionTrace>();

		myIterable = delegate.getExecutionTraces();
		for(NExecutionTrace tExecTr : myIterable) {
			ExecutionTraceOrientDBImpl tConImpl = new ExecutionTraceOrientDBImpl(tExecTr);
			myIConstraintTraceIterable.add(tConImpl);
		}
		return myIConstraintTraceIterable;
	}

	@Override
	public IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace) {
		NExecutionTrace tExecutionTrace = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		NExecutionTrace flow = delegate.addExecutionTrace(tExecutionTrace);
		return new ExecutionTraceOrientDBImpl(flow);
	}

	@Override
	public void removeExecutionTrace(IExecutionTrace executionTrace) {
		NExecutionTrace tExecutionTrace = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		delegate.removeExecutionTrace(tExecutionTrace);		
	}

	/**
	 * As vertex.
	 *
	 * @return the vertex
	 */
	public Vertex asVertex() {
		return delegate.asVertex();
	}

}
