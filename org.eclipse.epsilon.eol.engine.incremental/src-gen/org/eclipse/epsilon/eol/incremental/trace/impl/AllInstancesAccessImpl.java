 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace.impl;

import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.AccessHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.AccessHasExecutionImpl;

/**
 * Implementation of AllInstancesAccess. 
 */
public class AllInstancesAccessImpl implements AllInstancesAccess {

    /** The id */
    private Object id;

    /** The ofKind */
    private boolean ofKind;

    /** The execution relation */
    private final AccessHasExecution execution;

    /** The type relation */
    private final AllInstancesAccessHasType type;

    /**
     * Instantiates a new AllInstancesAccess.
     */
    public AllInstancesAccessImpl() {
        execution = new AccessHasExecutionImpl(this);
        type = new AllInstancesAccessHasTypeImpl(this);
    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(Object value) {
        this.id = value;
    }   
     
    @Override
    public boolean getOfKind() {
        return ofKind;
    }
    
    
    @Override
    public void setOfKind(boolean value) {
        this.ofKind = value;
    }   
     
    @Override
    public AccessHasExecution execution() {
        return execution;
    }
    @Override
    public AllInstancesAccessHasType type() {
        return type;
    }
 

}