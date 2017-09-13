
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
 ** Trace OrientDB Vertex Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;


import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link VTrace } interface represents a Trace as a
 * vertex in the graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface VTrace extends VertexFrame {
	
	/** Common name of this trace element. */
	static String TRACE_TYPE = "Trace";
    
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
	@Adjacency(label = EContains.TRACE_TYPE, direction = Direction.IN)
	VExecutionContext getExecutionContext();
    
    /**
     * Sets the value of the '{@link Trace#ExecutionContext <em>Execution Context</em>}' attribute.
     * <!-- protected region executionContext-setter-doc on begin -->
     * <!-- protected region executionContext-setter-doc end --> 
     * @param value the new value of the '<em>Execution Context/em>' attribute.
     * @see #getExecutionContext()
     */
	@Adjacency(label = EContains.TRACE_TYPE, direction = Direction.IN)
	void setExecutionContext(VExecutionContext value);
    
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
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.OUT)
	VModuleElement getTraces();
    
    /**
     * Sets the value of the '{@link Trace#Traces <em>Traces</em>}' attribute.
     * <!-- protected region traces-setter-doc on begin -->
     * <!-- protected region traces-setter-doc end --> 
     * @param value the new value of the '<em>Traces/em>' attribute.
     * @see #getTraces()
     */
	@Adjacency(label = ETraces.TRACE_TYPE, direction = Direction.OUT)
	void setTraces(VModuleElement value);
	
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
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VModelElement> getReaches();
	
	/**
	 * Add a ModelElement to the reaches.
	 *
	 * @param modelElement The ModelElement to add.
	 * @return the added modelElement but linked to this VTrace.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	VModelElement addReaches(VModelElement vModelElement);
	
	/**
	 * Remove a ModelElement from the reaches.
	 *
	 * @param modelElement The ModelElement to remove.
	 */
	@Adjacency(label = EReaches.TRACE_TYPE, direction = Direction.OUT)
	void removeReaches(VModelElement vModelElement);
	
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
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VProperty> getAccesses();
	
	/**
	 * Add a Property to the accesses.
	 *
	 * @param property The Property to add.
	 * @return the added property but linked to this VTrace.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	VProperty addAccesses(VProperty vProperty);
	
	/**
	 * Remove a Property from the accesses.
	 *
	 * @param property The Property to remove.
	 */
	@Adjacency(label = EAccesses.TRACE_TYPE, direction = Direction.OUT)
	void removeAccesses(VProperty vProperty);
	

}
