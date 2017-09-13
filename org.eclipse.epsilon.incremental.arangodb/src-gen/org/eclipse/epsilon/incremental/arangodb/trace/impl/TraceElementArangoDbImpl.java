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
 ** TraceElement OrientDB Trace Model Implementation automatically
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
 * An implementation of the TraceElement .
 */
public class TraceElementArangoDbImpl extends JSONObject implements TraceElement {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public TraceElementArangoDbImpl() { }
    
    /**
     * Instantiates a new TraceElementArangoDbImpl.
     *
     * @param delegate the delegate
     */
    public TraceElementArangoDbImpl(
            java.lang.Object id) {
        put("id", id);
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
    
 
 
}
