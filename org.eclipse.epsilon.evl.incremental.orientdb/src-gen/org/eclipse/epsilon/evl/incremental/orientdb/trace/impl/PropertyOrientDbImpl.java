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
 ** Property OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VProperty;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import com.tinkerpop.blueprints.Vertex;


/**
 * An implementation of the Property that delegates to an OrientDB vertex.
 */
public class PropertyOrientDbImpl implements Property {

	/** The delegate. */
	final private VProperty delegate;
	
	/**
	 * Instantiates a new PropertyOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public PropertyOrientDbImpl(VProperty  delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public VProperty getDelegate() {
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
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- protected region name-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region name-getter-doc end --> 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 */
	public java.lang.String getName() {
		return delegate.getName();
 
	}
	
	
	/**
	 * Sets the value of the '{@link Property#Name <em>Name</em>}' attribute.
	 * <!-- protected region name-setter-doc on begin -->
	 * <!-- protected region name-setter-doc end --> 
	 * @param value the new value of the '<em>Name/em>' attribute.
	 * @see #getName()
	 */
	public void setName(java.lang.String value) {
		delegate.setName(value);
	}
	
	
	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' attribute.
	 * <!-- protected region modelElement-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Model Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region modelElement-getter-doc end --> 
	 * @return the value of the '<em>Model Element</em>' attribute.
	 * @see #setModelElement(String)
	 */
	public ModelElement getModelElement() {
		return new ModelElementOrientDbImpl(delegate.getModelElement());
	}
	
	
	/**
	 * Sets the value of the '{@link Property#ModelElement <em>Model Element</em>}' attribute.
	 * <!-- protected region modelElement-setter-doc on begin -->
	 * <!-- protected region modelElement-setter-doc end --> 
	 * @param value the new value of the '<em>Model Element/em>' attribute.
	 * @see #getModelElement()
	 */
	public void setModelElement(ModelElement value) {
		VModelElement vertex = ((ModelElementOrientDbImpl) value).getDelegate();
		delegate.setModelElement(vertex);
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

	
}
