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

import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelElementOrientDBImpl.
 */
public class ModelElementOrientDBImpl implements IModelElement {

	/** The delegate. */
	final private TElement delegate;

	/**
	 * Instantiates a new model element orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ModelElementOrientDBImpl(TElement delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public TElement getDelegate() {
		return delegate;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#getElementId()
	 */
	public String getElementId() {
		return delegate.getElementId();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#setElementId(java.lang.String)
	 */
	public void setElementId(String elementId) {
		delegate.setElementId(elementId);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#getScopes()
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
 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#addScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
 */
public ITraceScope addScope(ITraceScope scope) {
		
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		TScope flow = delegate.addScope(tScope);
		return new TraceScopeOrientDBImpl(flow);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#removeScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	public void removeScope(ITraceScope scope) {
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		delegate.removeScope(tScope);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#getProperties()
	 */
	public Iterable<IElementProperty> getProperties() {
		ArrayList<IElementProperty> myElementProperty = new ArrayList<IElementProperty>();
		for (TProperty tEl : delegate.getProperties()){
			ElementPropertyOrientDBImpl tElementPropertyImpl = new ElementPropertyOrientDBImpl(tEl);
			myElementProperty.add(tElementPropertyImpl);
		}
		return myElementProperty;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#addProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
	 */
	public IElementProperty addProperty(IElementProperty property) {
		TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		TProperty flow = delegate.addProperty(tProperty);
		return new ElementPropertyOrientDBImpl(flow);	}

	/**
	 * As vertex.
	 *
	 * @return the vertex
	 */
	public Vertex asVertex() {
		return delegate.asVertex();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IModelElement#removeProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
	 */
	public void removeProperty(IElementProperty property) {
		TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		delegate.removeProperty(tProperty);
	}
}
