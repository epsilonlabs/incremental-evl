
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
 ** ExecutionContext OrientDB Vertex Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import java.util.List;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link VExecutionContext } interface represents a ExecutionContext as a
 * vertex in the graph.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface VExecutionContext extends VertexFrame {
	
	/** Common name of this trace element. */
	static String TRACE_TYPE = "ExecutionContext";
	
	/** Key to the 'scriptId' property of this vertex. */
	static String SCRIPT_ID = "script_id";	
	/** Key to the 'modelsIds' property of this vertex. */
	static String MODELS_IDS = "models_ids";	
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
	@Property(VExecutionContext.SCRIPT_ID)
	java.lang.String getScriptId();
	
	/**
	 * Sets the value of the '{@link ExecutionContext#ScriptId <em>Script Id</em>}' attribute.
	 * <!-- protected region scriptId-setter-doc on begin -->
	 * <!-- protected region scriptId-setter-doc end --> 
	 * @param value the new value of the '<em>Script Id/em>' attribute.
	 * @see #getScriptId()
	 */
	@Property(VExecutionContext.SCRIPT_ID)
	void setScriptId(java.lang.String value);
	
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
	@Property(VExecutionContext.MODELS_IDS)
	List<java.lang.String> getModelsIds();
	
	/**
	 * Sets the value of the '{@link ExecutionContext#ModelsIds <em>Models Ids</em>}' attribute.
	 * <!-- protected region modelsIds-setter-doc on begin -->
	 * <!-- protected region modelsIds-setter-doc end --> 
	 * @param value the new value of the '<em>Models Ids/em>' attribute.
	 * @see #getModelsIds()
	 */
	@Property(VExecutionContext.MODELS_IDS)
	void setModelsIds(List<java.lang.String>     value);
	
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
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VModuleElement> getFor();
	
	/**
	 * Add a ModuleElement to the for.
	 *
	 * @param moduleElement The ModuleElement to add.
	 * @return the added moduleElement but linked to this VExecutionContext.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.OUT)
	VModuleElement addFor(VModuleElement vModuleElement);
	
	/**
	 * Remove a ModuleElement from the for.
	 *
	 * @param moduleElement The ModuleElement to remove.
	 */
	@Adjacency(label = EFor.TRACE_TYPE, direction = Direction.OUT)
	void removeFor(VModuleElement vModuleElement);
	
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
	@Adjacency(label = EContains.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VTrace> getContains();
	
	/**
	 * Add a Trace to the contains.
	 *
	 * @param trace The Trace to add.
	 * @return the added trace but linked to this VExecutionContext.
	 */
	@Adjacency(label = EContains.TRACE_TYPE, direction = Direction.OUT)
	VTrace addContains(VTrace vTrace);
	
	/**
	 * Remove a Trace from the contains.
	 *
	 * @param trace The Trace to remove.
	 */
	@Adjacency(label = EContains.TRACE_TYPE, direction = Direction.OUT)
	void removeContains(VTrace vTrace);
	
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
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.OUT)
	Iterable<VModelElement> getInvolves();
	
	/**
	 * Add a ModelElement to the involves.
	 *
	 * @param modelElement The ModelElement to add.
	 * @return the added modelElement but linked to this VExecutionContext.
	 */
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.OUT)
	VModelElement addInvolves(VModelElement vModelElement);
	
	/**
	 * Remove a ModelElement from the involves.
	 *
	 * @param modelElement The ModelElement to remove.
	 */
	@Adjacency(label = EInvolves.TRACE_TYPE, direction = Direction.OUT)
	void removeInvolves(VModelElement vModelElement);
	

}
