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
import java.util.List;

import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.old.IModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModelElement;

import com.tinkerpop.blueprints.Vertex;


/**
 * The Class ModelElementOrientDBImpl.
 */
public class ModelElementOrientDBImpl implements IModelElement {

	/** The delegate. */
	final private NModelElement delegate;

	/**
	 * Instantiates a new model element orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ModelElementOrientDBImpl(NModelElement delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public NModelElement getDelegate() {
		return delegate;
	}
	
	/**
	 * As vertex.
	 *
	 * @return the vertex
	 */
	public Vertex asVertex() {
		return delegate.asVertex();
	}
	
	@Override
	public String getElementId() {
		return delegate.getElementId();
	}

	@Override
	public void setElementId(String elementId) {
		delegate.setElementId(elementId);
	}

	@Override
	public List<IExecutionTrace> getExecutionTraces() {
		ArrayList<IExecutionTrace> result = new ArrayList<IExecutionTrace>();
		for (NExecutionTrace tExex : delegate.getExecutionTraces()){
			ExecutionTraceOrientDBImpl tTraceScopeImpl = new ExecutionTraceOrientDBImpl(tExex);
			result.add(tTraceScopeImpl);
		}
		return result;
	}

	@Override
	public IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace) {
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		NExecutionTrace flow = delegate.addExecutionTrace(tScope);
		return new ExecutionTraceOrientDBImpl(flow);
	}

	@Override
	public void removeExecutionTrace(IExecutionTrace executionTrace) {
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		delegate.removeExecutionTrace(tScope);
	}
	
}
