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
 ** ExecutionContext OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModelElement;
import com.tinkerpop.blueprints.Vertex;


/**
 * An implementation of the ExecutionContext that delegates to an OrientDB vertex.
 */
public class ExecutionContextOrientDbImpl implements ExecutionContext {

	/** The delegate. */
	final private VExecutionContext delegate;
	
	/**
	 * Instantiates a new ExecutionContextOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public ExecutionContextOrientDbImpl(VExecutionContext  delegate) {
		this.delegate = delegate;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	public VExecutionContext getDelegate() {
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
	 * Returns the value of the '<em><b>Script Id</b></em>' attribute.
	 * <!-- protected region scriptId-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Script Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region scriptId-getter-doc end --> 
	 * @return the value of the '<em>Script Id</em>' attribute.
	 * @see #setScriptId(String)
	 */
	public java.lang.String getScriptId() {
		return delegate.getScriptId();
 
	}
	
	
	/**
	 * Sets the value of the '{@link ExecutionContext#ScriptId <em>Script Id</em>}' attribute.
	 * <!-- protected region scriptId-setter-doc on begin -->
	 * <!-- protected region scriptId-setter-doc end --> 
	 * @param value the new value of the '<em>Script Id/em>' attribute.
	 * @see #getScriptId()
	 */
	public void setScriptId(java.lang.String value) {
		delegate.setScriptId(value);
	}
	
	/**
	 * Returns the value of the '<em><b>Models Ids</b></em>' attribute.
	 * <!-- protected region modelsIds-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Models Ids</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region modelsIds-getter-doc end --> 
	 * @return the value of the '<em>Models Ids</em>' attribute.
	 * @see #setModelsIds(String)
	 */
	public List<java.lang.String> getModelsIds() {
		List<java.lang.String>     result = new ArrayList<java.lang.String>    ();
		for (java.lang.String item : delegate.getModelsIds()){
			result.add(item);
		}
		return result;
	}	
	/**
	 * Returns the value of the '<em><b>For</b></em>' attribute.
	 * <!-- protected region for-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>For</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region for-getter-doc end --> 
	 * @return the value of the '<em>For</em>' attribute.
	 * @see #setFor(String)
	 */
	public List<ModuleElement> getFor() {
		ArrayList<ModuleElement> result = new ArrayList<ModuleElement>();
		for (VModuleElement dItem : delegate.getFor()){
			ModuleElementOrientDbImpl wrap = new ModuleElementOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	/**
	 * Returns the value of the '<em><b>Contains</b></em>' attribute.
	 * <!-- protected region contains-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Contains</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region contains-getter-doc end --> 
	 * @return the value of the '<em>Contains</em>' attribute.
	 * @see #setContains(String)
	 */
	public List<Trace> getContains() {
		ArrayList<Trace> result = new ArrayList<Trace>();
		for (VTrace dItem : delegate.getContains()){
			TraceOrientDbImpl wrap = new TraceOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	/**
	 * Returns the value of the '<em><b>Involves</b></em>' attribute.
	 * <!-- protected region involves-getter-doc on begin -->
	 * <p>
	 * If the meaning of the '<em>Involves</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- protected region involves-getter-doc end --> 
	 * @return the value of the '<em>Involves</em>' attribute.
	 * @see #setInvolves(String)
	 */
	public List<ModelElement> getInvolves() {
		ArrayList<ModelElement> result = new ArrayList<ModelElement>();
		for (VModelElement dItem : delegate.getInvolves()){
			ModelElementOrientDbImpl wrap = new ModelElementOrientDbImpl(dItem);
			result.add(wrap);
		}
		return result;
	}

	
}
