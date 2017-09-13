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
 ** Trace OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VProperty;
import com.tinkerpop.blueprints.Vertex;


/**
 * An implementation of the Trace that delegates to an OrientDB vertex.
 */
public class TraceOrientDbImpl implements Trace {

	/** The delegate. */
	final private VTrace delegate;
	
	/**
	 * Instantiates a new TraceOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public TraceOrientDbImpl(VTrace  delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public VTrace getDelegate() {
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
	public ExecutionContext getExecutionContext() {
		return new ExecutionContextOrientDbImpl(delegate.getExecutionContext());
	}
	
	
	/**
	 * Sets the value of the '{@link Trace#ExecutionContext <em>Execution Context</em>}' attribute.
	 * <!-- protected region executionContext-setter-doc on begin -->
	 * <!-- protected region executionContext-setter-doc end --> 
	 * @param value the new value of the '<em>Execution Context/em>' attribute.
	 * @see #getExecutionContext()
	 */
	public void setExecutionContext(ExecutionContext value) {
		VExecutionContext vertex = ((ExecutionContextOrientDbImpl) value).getDelegate();
		delegate.setExecutionContext(vertex);
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
	public ModuleElement getTraces() {
		return new ModuleElementOrientDbImpl(delegate.getTraces());
	}
	
	
	/**
	 * Sets the value of the '{@link Trace#Traces <em>Traces</em>}' attribute.
	 * <!-- protected region traces-setter-doc on begin -->
	 * <!-- protected region traces-setter-doc end --> 
	 * @param value the new value of the '<em>Traces/em>' attribute.
	 * @see #getTraces()
	 */
	public void setTraces(ModuleElement value) {
		VModuleElement vertex = ((ModuleElementOrientDbImpl) value).getDelegate();
		delegate.setTraces(vertex);
	}
	
	/**
	 * Returns the value of the '<em><b>Reaches</b></em>' attribute.
	 * <!-- protected region reaches-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Reaches</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region reaches-getter-doc end --> 
	 * @return the value of the '<em>Reaches</em>' attribute.
	 * @see #setReaches(String)
	 */
	public List<ModelElement> getReaches() {
		ArrayList<ModelElement> result = new ArrayList<ModelElement>();
		for (VModelElement dItem : delegate.getReaches()){
			ModelElementOrientDbImpl wrap = new ModelElementOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	/**
	 * Returns the value of the '<em><b>Accesses</b></em>' attribute.
	 * <!-- protected region accesses-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Accesses</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region accesses-getter-doc end --> 
	 * @return the value of the '<em>Accesses</em>' attribute.
	 * @see #setAccesses(String)
	 */
	public List<Property> getAccesses() {
		ArrayList<Property> result = new ArrayList<Property>();
		for (VProperty dItem : delegate.getAccesses()){
			PropertyOrientDbImpl wrap = new PropertyOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	
}
