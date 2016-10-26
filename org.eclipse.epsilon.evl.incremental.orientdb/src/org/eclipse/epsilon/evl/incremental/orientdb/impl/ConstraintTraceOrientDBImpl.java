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

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstraintTraceOrientDBImpl.
 */
public class ConstraintTraceOrientDBImpl implements IConstraintTrace {

	/** The delegate. */
	final private TConstraint delegate;

	/**
	 * Instantiates a new constraint trace orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ConstraintTraceOrientDBImpl(TConstraint delegate) {
		this.delegate = delegate;
	}
	
	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public TConstraint getDelegate() {
		// TODO Auto-generated method stub
		return delegate;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#setName(java.lang.String)
	 */
	public void setName(String name) {
		delegate.setName(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#getScopes()
	 */
	public Iterable<ITraceScope> getScopes() {
		
		ArrayList<ITraceScope> myTraceScope = new ArrayList<ITraceScope>();
		for (TScope tSc : delegate.getScopes()){
			TraceScopeOrientDBImpl tTraceScopeImpl = new TraceScopeOrientDBImpl(tSc);
			myTraceScope.add(tTraceScopeImpl);
		}
		return myTraceScope;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#addScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	public ITraceScope addScope(ITraceScope scope) {
		
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		TScope flow = delegate.addScope(tScope);
		return new TraceScopeOrientDBImpl(flow);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#removeScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	public void removeScope(ITraceScope scope) {
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		delegate.removeScope(tScope);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#getContext()
	 */
	public IContextTrace getContext() {
		return new ContextTraceOrientDBImpl(delegate.getContext());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace#addContext(org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
	 */
	public IContextTrace addContext(IContextTrace context) {
		TContext tContext = ((ContextTraceOrientDBImpl) context).getDelegate();
		TContext flow = delegate.addContext(tContext);
		return new ContextTraceOrientDBImpl(flow);
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
