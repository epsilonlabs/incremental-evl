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
 ** ModuleElement OrientDB Trace Model Implementation automatically
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
 * An implementation of the ModuleElement .
 */
public class ModuleElementArangoDbImpl extends JSONObject implements ModuleElement {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public ModuleElementArangoDbImpl() { }
    
    /**
     * Instantiates a new ModuleElementArangoDbImpl.
     *
     * @param delegate the delegate
     */
    public ModuleElementArangoDbImpl(
            java.lang.String moduleId) {
        put("moduleId", moduleId);
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
        return (java.lang.String)get("moduleId");
    }
    
    
    /**
     * Sets the value of the '{@link ModuleElement#ModuleId <em>Module Id</em>}' attribute.
     * <!-- protected region moduleId-setter-doc on begin -->
     * <!-- protected region moduleId-setter-doc end --> 
     * @param value the new value of the '<em>Module Id/em>' attribute.
     * @see #getModuleId()
     */
    public void setModuleId(java.lang.String value) {
        put("moduleId", value);
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

 
}
