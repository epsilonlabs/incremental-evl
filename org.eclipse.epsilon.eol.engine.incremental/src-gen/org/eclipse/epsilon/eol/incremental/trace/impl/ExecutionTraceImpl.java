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

import org.eclipse.epsilon.eol.incremental.trace.ExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasExecution;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModel;

/**
 * Implementation of ExecutionTrace. 
 */
public class ExecutionTraceImpl implements ExecutionTrace {

    /** The id */
    private Object id;

    /** The execution relation */
    private final ExecutionTraceHasExecution execution;

    /** The model relation */
    private final ExecutionTraceHasModel model;

    /**
     * Instantiates a new ExecutionTrace.
     */
    public ExecutionTraceImpl() {
        execution = new ExecutionTraceHasExecutionImpl(this);
        model = new ExecutionTraceHasModelImpl(this);
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
    public ExecutionTraceHasExecution execution() {
        return execution;
    }
    @Override
    public ExecutionTraceHasModel model() {
        return model;
    }
 

}