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
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementPropertyOrientDBImpl.
 */
public class ElementPropertyOrientDBImpl implements IElementProperty {

	/** The delegate. */
	final private TProperty delegate;

	/**
	 * Instantiates a new element property orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ElementPropertyOrientDBImpl(TProperty delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public TProperty getDelegate() {
		return delegate;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#addScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	public ITraceScope addScope(ITraceScope scope) {
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		TScope flow = delegate.addScope(tScope);
		return new TraceScopeOrientDBImpl(flow);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#removeScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
	 */
	public void removeScope(ITraceScope scope) {
		TScope tScope = ((TraceScopeOrientDBImpl) scope).getDelegate();
		delegate.removeScope(tScope);
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
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#setName(java.lang.String)
	 */
	public void setName(String name) {
		delegate.setName(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#getOwner()
	 */
	public IModelElement getOwner() {
		return new ModelElementOrientDBImpl(delegate.getOwner());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#setOwner(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
	 */
	public IModelElement setOwner(IModelElement element) {
		TElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
		TElement flow = delegate.setOwner(tElement);
		return new ModelElementOrientDBImpl(flow);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.incremental.trace.IElementProperty#getScopes()
	 */
	public Iterable<ITraceScope> getScopes() {
		ArrayList<ITraceScope> myTraceScope = new ArrayList<ITraceScope>();
		for (TScope tSc : delegate.getScopes()){
			TraceScopeOrientDBImpl tTraceScopeImpl = new TraceScopeOrientDBImpl(tSc);
			myTraceScope.add(tTraceScopeImpl);
		}
		return myTraceScope;
	}
}
