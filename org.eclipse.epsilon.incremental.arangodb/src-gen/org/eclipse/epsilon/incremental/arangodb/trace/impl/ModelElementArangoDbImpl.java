/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/

/*******************************************************************************
 ** ModelElement OrientDB Trace Model Implementation automatically
 ** generated on Sat Sep 09 20:01:33 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.incremental.arangodb.trace.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.trace.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * An implementation of the ModelElement .
 */
public class ModelElementArangoDbImpl extends JSONObject implements ModelElement {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public ModelElementArangoDbImpl() { }
    
    /**
     * Instantiates a new ModelElementArangoDbImpl.
     *
     * @param delegate the delegate
     */
    public ModelElementArangoDbImpl(
            java.lang.String elementId) {
        put("elementId", elementId);
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
        return (java.lang.Object)get("_id");
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
        return (java.lang.String)get("elementId");
    }
    
    
    /**
     * Sets the value of the '{@link ModelElement#ElementId <em>Element Id</em>}' attribute.
     * <!-- protected region elementId-setter-doc on begin -->
     * <!-- protected region elementId-setter-doc end --> 
     * @param value the new value of the '<em>Element Id/em>' attribute.
     * @see #getElementId()
     */
    public void setElementId(java.lang.String value) {
        put("elementId", value);
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
        return null;
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
        return null;
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
        return null;
    }

 
}
