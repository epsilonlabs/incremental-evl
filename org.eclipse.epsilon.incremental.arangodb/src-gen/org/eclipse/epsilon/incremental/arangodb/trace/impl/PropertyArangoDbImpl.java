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
 ** Property OrientDB Trace Model Implementation automatically
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
 * An implementation of the Property .
 */
public class PropertyArangoDbImpl extends JSONObject implements Property {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public PropertyArangoDbImpl() { }
    
    /**
     * Instantiates a new PropertyArangoDbImpl.
     *
     * @param delegate the delegate
     */
    public PropertyArangoDbImpl(
            java.lang.String name) {
        put("name", name);
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
    public java.lang.String getName() {
        return (java.lang.String)get("name");
    }
    
    
    /**
     * Sets the value of the '{@link Property#Name <em>Name</em>}' attribute.
     * <!-- protected region name-setter-doc on begin -->
     * <!-- protected region name-setter-doc end --> 
     * @param value the new value of the '<em>Name/em>' attribute.
     * @see #getName()
     */
    public void setName(java.lang.String value) {
        put("name", value);
    }
    
 
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
    public ModelElement getModelElement() {
        return null;
    }
    
    
    /**
     * Sets the value of the '{@link Property#ModelElement <em>Model Element</em>}' attribute.
     * <!-- protected region modelElement-setter-doc on begin -->
     * <!-- protected region modelElement-setter-doc end --> 
     * @param value the new value of the '<em>Model Element/em>' attribute.
     * @see #getModelElement()
     */
    public void setModelElement(ModelElement value) {
        
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
