
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
 ** Property OrientDB Vertex Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;


import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link VProperty } interface represents a Property as a
 * vertex in the graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface VProperty extends VertexFrame {
	
	/** Common name of this trace element. */
	static String TRACE_TYPE = "Property";
	
	/** Key to the 'name' property of this vertex. */
	static String NAME = "name";	
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
	@Property(VProperty.NAME)
	java.lang.String getName();
	
	/**
	 * Sets the value of the '{@link Property#Name <em>Name</em>}' attribute.
	 * <!-- protected region name-setter-doc on begin -->
	 * <!-- protected region name-setter-doc end --> 
	 * @param value the new value of the '<em>Name/em>' attribute.
	 * @see #getName()
	 */
	@Property(VProperty.NAME)
	void setName(java.lang.String value);
    
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
	@Adjacency(label = EOwns.TRACE_TYPE, direction = Direction.IN)
	VModelElement getModelElement();
    
    /**
     * Sets the value of the '{@link Property#ModelElement <em>Model Element</em>}' attribute.
     * <!-- protected region modelElement-setter-doc on begin -->
     * <!-- protected region modelElement-setter-doc end --> 
     * @param value the new value of the '<em>Model Element/em>' attribute.
     * @see #getModelElement()
     */
	@Adjacency(label = EOwns.TRACE_TYPE, direction = Direction.IN)
	void setModelElement(VModelElement value);
	
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
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	Iterable<VTrace> getTraces();
	
	/**
	 * Add a Trace to the traces.
	 *
	 * @param trace The Trace to add.
	 * @return the added trace but linked to this VProperty.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	VTrace addTraces(VTrace vTrace);
	
	/**
	 * Remove a Trace from the traces.
	 *
	 * @param trace The Trace to remove.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.IN)
	void removeTraces(VTrace vTrace);
	

}
