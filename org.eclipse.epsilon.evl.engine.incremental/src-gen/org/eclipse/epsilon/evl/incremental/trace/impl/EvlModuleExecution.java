 /*******************************************************************************
 * This file was automatically generated on: 2017-11-23.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasExecutions;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModel;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModule;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModuleElements;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleExecutionHasExecutions;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleExecutionHasModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleExecutionHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleExecutionHasModuleElements;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;

/**
 * Implementation of IEvlModuleExecution. 
 */
public class EvlModuleExecution implements IEvlModuleExecution {

    /** The id */
    private Object id;

    /** The module relation */
    private final IModuleExecutionHasModule module;

    /** The model relation */
    private final IModuleExecutionHasModel model;

    /** The executions relation */
    private final IModuleExecutionHasExecutions executions;

    /** The moduleElements relation */
    private final IModuleExecutionHasModuleElements moduleElements;

    /**
     * Instantiates a new EvlModuleExecution. The EvlModuleExecution is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleExecution() throws TraceModelDuplicateRelation {
        this.module = new ModuleExecutionHasModule(this);
        this.model = new ModuleExecutionHasModel(this);
        this.executions = new ModuleExecutionHasExecutions(this);
        this.moduleElements = new ModuleExecutionHasModuleElements(this);
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
    public IModuleExecutionHasModule module() {
        return module;
    }

    @Override
    public IModuleExecutionHasModel model() {
        return model;
    }

    @Override
    public IModuleExecutionHasExecutions executions() {
        return executions;
    }

    @Override
    public IModuleExecutionHasModuleElements moduleElements() {
        return moduleElements;
    }

    @Override
    public IEvlModuleTrace createEvlModuleTrace(String source) throws EolIncrementalExecutionException {
        IEvlModuleTrace evlModuleTrace = null;
        try {
            evlModuleTrace = new EvlModuleTrace(source, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (evlModuleTrace != null) {
    	        return evlModuleTrace;
    	    }
            evlModuleTrace = (EvlModuleTrace) this.module.get();
            if (evlModuleTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested EvlModuleTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return evlModuleTrace;
    }      
            
                  
    @Override
    public IModelTrace createModelTrace(String name) throws EolIncrementalExecutionException {
        IModelTrace modelTrace = null;
        try {
            modelTrace = new ModelTrace(name, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelTrace != null) {
    	        return modelTrace;
    	    }
            try {
                modelTrace = this.model.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelTrace;
    }      
                  
    @Override
    public IContextTrace createContextTrace(String kind) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        try {
            contextTrace = new ContextTrace(kind, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
            try {
                contextTrace = this.moduleElements.get().stream()
                    .map(ContextTrace.class::cast)
                    .filter(item -> item.getKind().equals(kind))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return contextTrace;
    }      
            
                  
    @Override
    public boolean sameIdentityAs(final IEvlModuleExecution other) {
        if (other == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EvlModuleExecution))
            return false;
        EvlModuleExecution other = (EvlModuleExecution) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return result;
    }

}
