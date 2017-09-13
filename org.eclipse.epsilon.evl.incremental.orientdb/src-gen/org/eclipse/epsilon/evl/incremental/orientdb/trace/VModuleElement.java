
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
 ** ModuleElement OrientDB Vertex Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;


import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link VModuleElement } interface represents a ModuleElement as a
 * vertex in the graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface VModuleElement extends VertexFrame {
	
	/** Common name of this trace element. */
	static String TRACE_TYPE = "ModuleElement";
	
	/** Key to the 'moduleId' property of this vertex. */
	static String MODULE_ID = "module_id";	
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
	@Property(VModuleElement.MODULE_ID)
	java.lang.String getModuleId();
	
	/**
	 * Sets the value of the '{@link ModuleElement#ModuleId <em>Module Id</em>}' attribute.
	 * <!-- protected region moduleId-setter-doc on begin -->
	 * <!-- protected region moduleId-setter-doc end --> 
	 * @param value the new value of the '<em>Module Id/em>' attribute.
	 * @see #getModuleId()
	 */
	@Property(VModuleElement.MODULE_ID)
	void setModuleId(java.lang.String value);
	
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
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	Iterable<VExecutionContext> getExecutionContexts();
	
	/**
	 * Add a ExecutionContext to the executionContexts.
	 *
	 * @param executionContext The ExecutionContext to add.
	 * @return the added executionContext but linked to this VModuleElement.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	VExecutionContext addExecutionContexts(VExecutionContext vExecutionContext);
	
	/**
	 * Remove a ExecutionContext from the executionContexts.
	 *
	 * @param executionContext The ExecutionContext to remove.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.IN)
	void removeExecutionContexts(VExecutionContext vExecutionContext);
	
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
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.IN)
	Iterable<VTrace> getTraces();
	
	/**
	 * Add a Trace to the traces.
	 *
	 * @param trace The Trace to add.
	 * @return the added trace but linked to this VModuleElement.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.IN)
	VTrace addTraces(VTrace vTrace);
	
	/**
	 * Remove a Trace from the traces.
	 *
	 * @param trace The Trace to remove.
	 */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.IN)
	void removeTraces(VTrace vTrace);
	

}
