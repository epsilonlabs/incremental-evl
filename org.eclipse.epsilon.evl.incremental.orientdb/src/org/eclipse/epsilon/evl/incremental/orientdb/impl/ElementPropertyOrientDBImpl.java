/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.impl;

import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.old.IElementProperty;
import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NElementProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;

import com.tinkerpop.blueprints.Vertex;


/**
 * The Class ElementPropertyOrientDBImpl.
 */
public class ElementPropertyOrientDBImpl implements IElementProperty {

	/** The delegate. */
	final private NElementProperty delegate;

	/**
	 * Instantiates a new element property orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ElementPropertyOrientDBImpl(NElementProperty delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public NElementProperty getDelegate() {
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


	public String getName() {
		return delegate.getName();
	}


	public void setName(String name) {
		delegate.setName(name);
	}


	public Iterable<IExecutionTrace> getExecutionTraces() {
		ArrayList<IExecutionTrace> myTraceScope = new ArrayList<IExecutionTrace>();
		for (NExecutionTrace tSc : delegate.getExecutionTraces()){
			ExecutionTraceOrientDBImpl tTraceScopeImpl = new ExecutionTraceOrientDBImpl(tSc);
			myTraceScope.add(tTraceScopeImpl);
		}
		return myTraceScope;
	}
	

	public IExecutionTrace addExecutionTrace(IExecutionTrace scope) {
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) scope).getDelegate();
		NExecutionTrace flow = delegate.addExecutionTrace(tScope);
		return new ExecutionTraceOrientDBImpl(flow);
	}


	public void removeExecutionTrace(IExecutionTrace scope) {
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) scope).getDelegate();
		delegate.removeExecutionTrace(tScope);
	}

}
