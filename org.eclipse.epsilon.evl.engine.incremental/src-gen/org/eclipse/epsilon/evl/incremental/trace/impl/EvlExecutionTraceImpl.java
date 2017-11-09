 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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

import org.eclipse.epsilon.evl.incremental.trace.EvlExecutionTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModel;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasModelImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasModuleImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelImpl;
import org.eclipse.epsilon.evl.incremental.trace.EvlModule;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleImpl;

/**
 * Implementation of EvlExecutionTrace. 
 */
public class EvlExecutionTraceImpl implements EvlExecutionTrace {

    /** The id */
    private Object id;

    /** The model relation */
    private final ExecutionTraceHasModel model;

    /** The module relation */
    private final ExecutionTraceHasModule module;

    /**
     * Instantiates a new EvlExecutionTrace. The EvlExecutionTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlExecutionTraceImpl() throws TraceModelDuplicateRelation {
        this.model = new ExecutionTraceHasModelImpl(this);
        this.module = new ExecutionTraceHasModuleImpl(this);
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
    public ExecutionTraceHasModel model() {
        return model;
    }

    @Override
    public ExecutionTraceHasModule module() {
        return module;
    }

    @Override
    public Model createModel(String name) throws EolIncrementalExecutionException {
            try {
                return new ModelImpl(name, this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            Model model = null;
            
            try {
                model = this.model.get().stream()
                    .filter(mt -> mt.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Model was "
                        + "duplicate but previous one was not found.");
            }
            return model;
    }      
                  
    @Override
    public EvlModule createEvlModule(String source) throws EolIncrementalExecutionException {
            try {
                return new EvlModuleImpl(source, this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            EvlModule evlModule = null;
            
            try {
                evlModule = this.module.get().stream()
                    .map(EvlModule.class::cast)
                    .filter(mt -> mt.getSource().equals(source))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested EvlModule was "
                        + "duplicate but previous one was not found.");
            }
            return evlModule;
    }      
            
                  
    @Override
    public boolean sameIdentityAs(final EvlExecutionTrace other) {
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
        if (!(obj instanceof EvlExecutionTraceImpl))
            return false;
        EvlExecutionTraceImpl other = (EvlExecutionTraceImpl) obj;
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
