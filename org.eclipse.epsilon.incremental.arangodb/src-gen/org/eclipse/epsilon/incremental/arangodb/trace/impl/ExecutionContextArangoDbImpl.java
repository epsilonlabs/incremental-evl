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
 ** ExecutionContext OrientDB Trace Model Implementation automatically
 ** generated on Sat Sep 09 20:01:33 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.incremental.arangodb.trace.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.generation.*;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * An implementation of the ExecutionContext .
 */
public class ExecutionContextArangoDbImpl extends JSONObject implements ExecutionContext {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public ExecutionContextArangoDbImpl() { }
    
    /**
     * Instantiates a new ExecutionContextArangoDbImpl.
     *
     * @param delegate the delegate
     */
    public ExecutionContextArangoDbImpl(
            java.lang.String scriptId,
            List<java.lang.String> modelsIds) {
        put("scriptId", scriptId);
        JSONArray list = new JSONArray();
        list.addAll(modelsIds);
        put("modelsIds", list);
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
        return (java.lang.String)get("scriptId");
    }
    
    
    /**
     * Sets the value of the '{@link ExecutionContext#ScriptId <em>Script Id</em>}' attribute.
     * <!-- protected region scriptId-setter-doc on begin -->
     * <!-- protected region scriptId-setter-doc end --> 
     * @param value the new value of the '<em>Script Id/em>' attribute.
     * @see #getScriptId()
     */
    public void setScriptId(java.lang.String value) {
        put("scriptId", value);
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
        JSONArray values = (JSONArray) getOrDefault("modelsIds", Collections.emptyList());
        Iterator<Object> iterator = values.iterator();
        while (iterator.hasNext()) {
            result.add((java.lang.String)iterator.next());
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
        return null;
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
        return null;
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
        return null;
    }

 
}
