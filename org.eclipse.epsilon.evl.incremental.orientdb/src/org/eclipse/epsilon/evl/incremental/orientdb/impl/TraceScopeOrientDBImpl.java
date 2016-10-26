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
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class TraceScopeOrientDBImpl.
 */
public class TraceScopeOrientDBImpl implements ITraceScope {

	/** The delegate. */
	final private TScope delegate;
	
	/**
	 * Instantiates a new trace scope orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public TraceScopeOrientDBImpl(TScope delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public TScope getDelegate() {
		return delegate;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#getResult(boolean)
	 */
	public boolean getResult(boolean result) {
		return delegate.getResult(result);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#addProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
	 */
	public IElementProperty addProperty(IElementProperty property) {
		TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		TProperty flow = delegate.addProperty(tProperty);
		return new ElementPropertyOrientDBImpl(flow);
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
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#setResult(boolean)
	 */
	public void setResult(boolean result) {
		delegate.setResult(result);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#getConstraint()
	 */
	public IConstraintTrace getConstraint() {
		return new ConstraintTraceOrientDBImpl(delegate.getConstraint());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#setConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
	 */
	public void setConstraint(IConstraintTrace constraint) {
		TConstraint tConstraint = ((ConstraintTraceOrientDBImpl) constraint).getDelegate();
		delegate.setConstraint(tConstraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#getRootElement()
	 */
	public IModelElement getRootElement() {
		return new ModelElementOrientDBImpl(delegate.getRootElement());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#setRootElement(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	public void setRootElement(IModelElement element) {
		TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		delegate.setRootElement(tElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#getProperties()
	 */
	public Iterable<IElementProperty> getProperties() {
		ArrayList<IElementProperty> myElementProperty = new ArrayList<IElementProperty>();
		for (TProperty tPr : delegate.getProperties()){
			ElementPropertyOrientDBImpl tElementPropertyImpl = new ElementPropertyOrientDBImpl(tPr);
			myElementProperty.add(tElementPropertyImpl);
		}
		return myElementProperty;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.ITraceScope#removeProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
	 */
	public void removeProperty(IElementProperty property) {
		TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		delegate.removeProperty(tProperty);
	}

}
