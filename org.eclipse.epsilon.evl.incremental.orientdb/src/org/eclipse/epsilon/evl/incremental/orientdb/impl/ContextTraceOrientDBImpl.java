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
import java.util.Iterator;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TContext;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextTraceOrientDBImpl.
 */
public class ContextTraceOrientDBImpl implements IContextTrace {

	/** The delegate. */
	final private TContext delegate;
	
	/**
	 * Instantiates a new context trace orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	// Wrap TCOntext in a IContextTrace
	public ContextTraceOrientDBImpl(TContext delegate) {
		this.delegate = delegate;
	}
	
	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	// Unwrap TContext from the IContextTrace
	public TContext getDelegate() {
		return delegate;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IContextTrace#addConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	public IConstraintTrace addConstraint(IConstraintTrace constraint) {
		TConstraint tConstraint = ((ConstraintTraceOrientDBImpl) constraint).getDelegate();
		TConstraint flow = delegate.addConstraint(tConstraint);
		return new ConstraintTraceOrientDBImpl(flow);
	}
	

	/**
	 * As vertex.
	 *
	 * @return the vertex
	 */
	public Vertex asVertex() {
		return delegate.asVertex();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IContextTrace#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IContextTrace#setName(java.lang.String)
	 */
	public void setName(String name) {
		delegate.setName(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IContextTrace#getConstraints()
	 */
	public Iterable<IConstraintTrace> getConstraints() {
		Iterable<TConstraint> myIterable = new ArrayList<TConstraint>();
		ArrayList<IConstraintTrace> myIConstraintTraceIterable = new ArrayList<IConstraintTrace>();

		myIterable = delegate.getConstraints();
		for(TConstraint tCon : myIterable) {
			ConstraintTraceOrientDBImpl tConImpl = new ConstraintTraceOrientDBImpl(tCon);
			myIConstraintTraceIterable.add(tConImpl);
		}
		return myIConstraintTraceIterable;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IContextTrace#removeConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	public void removeConstraint(IConstraintTrace constraint) {
		TConstraint tConstraint = ((ConstraintTraceOrientDBImpl) constraint).getDelegate();
		delegate.removeConstraint(tConstraint);
	}



}
