
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
 ** ModelElement OrientDB Vertex Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;


import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link VModelElement } interface represents a ModelElement as a
 * vertex in the graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface VModelElement extends VertexFrame {
	
	/** Common name of this trace element. */
	static String TRACE_TYPE = "ModelElement";
	
	/** Key to the 'elementId' property of this vertex. */
	static String ELEMENT_ID = "element_id";	
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
	@Property(VModelElement.ELEMENT_ID)
	java.lang.String getElementId();
	
	/**
	 * Sets the value of the '{@link ModelElement#ElementId <em>Element Id</em>}' attribute.
	 * <!-- protected region elementId-setter-doc on begin -->
	 * <!-- protected region elementId-setter-doc end --> 
	 * @param value the new value of the '<em>Element Id/em>' attribute.
	 * @see #getElementId()
	 */
	@Property(VModelElement.ELEMENT_ID)
	void setElementId(java.lang.String value);
	
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
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.IN)
	Iterable<VExecutionContext> getExecutionContext();
	
	/**
	 * Add a ExecutionContext to the executionContext.
	 *
	 * @param executionContext The ExecutionContext to add.
	 * @return the added executionContext but linked to this VModelElement.
	 */
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.IN)
	VExecutionContext addExecutionContext(VExecutionContext vExecutionContext);
	
	/**
	 * Remove a ExecutionContext from the executionContext.
	 *
	 * @param executionContext The ExecutionContext to remove.
	 */
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.IN)
	void removeExecutionContext(VExecutionContext vExecutionContext);
	
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
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	Iterable<VTrace> getTraces();
	
	/**
	 * Add a Trace to the traces.
	 *
	 * @param trace The Trace to add.
	 * @return the added trace but linked to this VModelElement.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	VTrace addTraces(VTrace vTrace);
	
	/**
	 * Remove a Trace from the traces.
	 *
	 * @param trace The Trace to remove.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.IN)
	void removeTraces(VTrace vTrace);
	
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
	@Adjacency(label = EOwns.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VProperty> getOwns();
	
	/**
	 * Add a Property to the owns.
	 *
	 * @param property The Property to add.
	 * @return the added property but linked to this VModelElement.
	 */
	@Adjacency(label = EOwns.TRACE_TYPE, direction = Direction.OUT)
	VProperty addOwns(VProperty vProperty);
	
	/**
	 * Remove a Property from the owns.
	 *
	 * @param property The Property to remove.
	 */
	@Adjacency(label = EOwns.TRACE_TYPE, direction = Direction.OUT)
	void removeOwns(VProperty vProperty);
	

}
