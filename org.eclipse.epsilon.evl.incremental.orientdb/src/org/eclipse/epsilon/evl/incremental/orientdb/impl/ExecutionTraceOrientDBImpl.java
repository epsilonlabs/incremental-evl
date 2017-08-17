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
import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.old.IElementProperty;
import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.old.IModelElement;
import org.eclipse.epsilon.eol.incremental.old.IModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NElementProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NExecutionTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.trace.NModuleElement;

import com.tinkerpop.blueprints.Vertex;

/**
 * The Class TraceScopeOrientDBImpl.
 */
public class ExecutionTraceOrientDBImpl implements IExecutionTrace {

	/** The delegate. */
	final private NExecutionTrace delegate;
	
	/**
	 * Instantiates a new trace scope orient DB impl.
	 *
	 * @param delegate the delegate
	 */
	public ExecutionTraceOrientDBImpl(NExecutionTrace delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public NExecutionTrace getDelegate() {
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
	
	
	public IElementProperty addProperty(IElementProperty property) {
		NElementProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		NElementProperty flow = delegate.addProperty(tProperty);
		return new ElementPropertyOrientDBImpl(flow);
	}


	public IModuleElement getModuleElement() {
		return new ModuleElementOrientDBImpl(delegate.getModuleElement());
	}


	public void setModuleElement(IModuleElement moduleElement) {
		NModuleElement tConstraint = ((ModuleElementOrientDBImpl) moduleElement).getDelegate();
		delegate.setModuleElement(tConstraint);
	}


	public List<IModelElement> getModelElements() {
		ArrayList<IModelElement> myTraceScope = new ArrayList<IModelElement>();
		for (NModelElement tSc : delegate.getModelElements()){
			ModelElementOrientDBImpl tTraceScopeImpl = new ModelElementOrientDBImpl(tSc);
			myTraceScope.add(tTraceScopeImpl);
		}
		return myTraceScope;
	}


	public IModelElement addModelElementTrace(IModelElement modelElement) {
		NModelElement tElement = ((ModelElementOrientDBImpl) modelElement).getDelegate();
		NModelElement flow = delegate.addModelElement(tElement);
		return new ModelElementOrientDBImpl(flow);
	}
	
	
	public void removeModelElementTrace(IModelElement modelElement) {
		NModelElement tElement = ((ModelElementOrientDBImpl) modelElement).getDelegate();
		delegate.removeModelElement(tElement);
	}
	

	public List<IElementProperty> getElementProperties() {
		ArrayList<IElementProperty> myElementProperty = new ArrayList<IElementProperty>();
		for (NElementProperty tPr : delegate.getProperties()){
			ElementPropertyOrientDBImpl tElementPropertyImpl = new ElementPropertyOrientDBImpl(tPr);
			myElementProperty.add(tElementPropertyImpl);
		}
		return myElementProperty;
	}
	
	public IElementProperty addElementPropertyTrace(IElementProperty elementProperty) {
		NElementProperty tProperty = ((ElementPropertyOrientDBImpl) elementProperty).getDelegate();
		NElementProperty flow = delegate.addProperty(tProperty);
		return new ElementPropertyOrientDBImpl(flow);
	}


	public void removeElementPropertyTrace(IElementProperty property) {
		NElementProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
		delegate.removeProperty(tProperty);
	}

}
