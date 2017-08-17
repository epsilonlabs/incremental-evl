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

import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.old.IModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModuleElement;

import com.tinkerpop.blueprints.Vertex;

/**
 * The Class ConstraintTraceOrientDBImpl.
 */
public class ModuleElementOrientDBImpl implements IModuleElement {

	/** The delegate. */
	final private NModuleElement delegate;

	/**
	 * Instantiates a new Module Element Trace for OrientDB.
	 *
	 * @param delegate the delegate
	 */
	public ModuleElementOrientDBImpl(NModuleElement delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public String getId() {
		return delegate.getId();
	}

	@Override
	public void setId(String name) {
		delegate.setId(name);
	}
	
	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public NModuleElement getDelegate() {
		return delegate;
	}


	public Iterable<IExecutionTrace> getExecutionTraces() {
		
		ArrayList<IExecutionTrace> myTraceScope = new ArrayList<IExecutionTrace>();
		for (NExecutionTrace tSc : delegate.getExecutionTraces()){
			ExecutionTraceOrientDBImpl tTraceScopeImpl = new ExecutionTraceOrientDBImpl(tSc);
			myTraceScope.add(tTraceScopeImpl);
		}
		return myTraceScope;
	}

	public IExecutionTrace addExecutionTrace(IExecutionTrace executionTrace) {
		
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		NExecutionTrace flow = delegate.addExecutionTrace(tScope);
		return new ExecutionTraceOrientDBImpl(flow);
	}

	
	public void removeExecutionTrace(IExecutionTrace executionTrace) {
		NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) executionTrace).getDelegate();
		delegate.removeExecutionTrace(tScope);
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
