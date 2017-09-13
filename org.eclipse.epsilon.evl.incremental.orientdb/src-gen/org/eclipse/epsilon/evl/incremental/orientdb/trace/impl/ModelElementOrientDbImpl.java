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

/*******************************************************************************
 ** ModelElement OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VProperty;
import com.tinkerpop.blueprints.Vertex;


/**
 * An implementation of the ModelElement that delegates to an OrientDB vertex.
 */
public class ModelElementOrientDbImpl implements ModelElement {

	/** The delegate. */
	final private VModelElement delegate;
	
	/**
	 * Instantiates a new ModelElementOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public ModelElementOrientDbImpl(VModelElement  delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public VModelElement getDelegate() {
		return delegate;
	}
	
	/**
	 * As vertex.
	 *
	 * @return the delegate as a vertex
	 */
	public Vertex asVertex() {
		return delegate.asVertex();
	}
	
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- protected region id-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region id-getter-doc end --> 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 */
	public java.lang.Object getId() {
		return delegate.asVertex().getId(); 
	}
	
	/**
	 * Returns the value of the '<em><b>Element Id</b></em>' attribute.
	 * <!-- protected region elementId-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Element Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region elementId-getter-doc end --> 
	 * @return the value of the '<em>Element Id</em>' attribute.
	 * @see #setElementId(String)
	 */
	public java.lang.String getElementId() {
		return delegate.getElementId();
 
	}
	
	
	/**
	 * Sets the value of the '{@link ModelElement#ElementId <em>Element Id</em>}' attribute.
	 * <!-- protected region elementId-setter-doc on begin -->
	 * <!-- protected region elementId-setter-doc end --> 
	 * @param value the new value of the '<em>Element Id/em>' attribute.
	 * @see #getElementId()
	 */
	public void setElementId(java.lang.String value) {
		delegate.setElementId(value);
	}
	
	
	/**
	 * Returns the value of the '<em><b>Execution Context</b></em>' attribute.
	 * <!-- protected region executionContext-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Execution Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region executionContext-getter-doc end --> 
	 * @return the value of the '<em>Execution Context</em>' attribute.
	 * @see #setExecutionContext(String)
	 */
	public List<ExecutionContext> getExecutionContext() {
		ArrayList<ExecutionContext> result = new ArrayList<ExecutionContext>();
		for (VExecutionContext dItem : delegate.getExecutionContext()){
			ExecutionContextOrientDbImpl wrap = new ExecutionContextOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' attribute.
	 * <!-- protected region traces-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region traces-getter-doc end --> 
	 * @return the value of the '<em>Traces</em>' attribute.
	 * @see #setTraces(String)
	 */
	public List<Trace> getTraces() {
		ArrayList<Trace> result = new ArrayList<Trace>();
		for (VTrace dItem : delegate.getTraces()){
			TraceOrientDbImpl wrap = new TraceOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	/**
	 * Returns the value of the '<em><b>Owns</b></em>' attribute.
	 * <!-- protected region owns-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Owns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region owns-getter-doc end --> 
	 * @return the value of the '<em>Owns</em>' attribute.
	 * @see #setOwns(String)
	 */
	public List<Property> getOwns() {
		ArrayList<Property> result = new ArrayList<Property>();
		for (VProperty dItem : delegate.getOwns()){
			PropertyOrientDbImpl wrap = new PropertyOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	
}
