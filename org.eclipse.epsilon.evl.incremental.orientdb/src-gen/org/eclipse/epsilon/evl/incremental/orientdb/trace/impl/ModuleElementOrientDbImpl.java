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
 ** ModuleElement OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import com.tinkerpop.blueprints.Vertex;


/**
 * An implementation of the ModuleElement that delegates to an OrientDB vertex.
 */
public class ModuleElementOrientDbImpl implements ModuleElement {

	/** The delegate. */
	final private VModuleElement delegate;
	
	/**
	 * Instantiates a new ModuleElementOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public ModuleElementOrientDbImpl(VModuleElement  delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public VModuleElement getDelegate() {
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
	 * Returns the value of the '<em><b>Module Id</b></em>' attribute.
	 * <!-- protected region moduleId-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Module Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region moduleId-getter-doc end --> 
	 * @return the value of the '<em>Module Id</em>' attribute.
	 * @see #setModuleId(String)
	 */
	public java.lang.String getModuleId() {
		return delegate.getModuleId();
 
	}
	
	
	/**
	 * Sets the value of the '{@link ModuleElement#ModuleId <em>Module Id</em>}' attribute.
	 * <!-- protected region moduleId-setter-doc on begin -->
	 * <!-- protected region moduleId-setter-doc end --> 
	 * @param value the new value of the '<em>Module Id/em>' attribute.
	 * @see #getModuleId()
	 */
	public void setModuleId(java.lang.String value) {
		delegate.setModuleId(value);
	}
	
	
	/**
	 * Returns the value of the '<em><b>Execution Contexts</b></em>' attribute.
	 * <!-- protected region executionContexts-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Execution Contexts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region executionContexts-getter-doc end --> 
	 * @return the value of the '<em>Execution Contexts</em>' attribute.
	 * @see #setExecutionContexts(String)
	 */
	public List<ExecutionContext> getExecutionContexts() {
		ArrayList<ExecutionContext> result = new ArrayList<ExecutionContext>();
		for (VExecutionContext dItem : delegate.getExecutionContexts()){
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

	
}
