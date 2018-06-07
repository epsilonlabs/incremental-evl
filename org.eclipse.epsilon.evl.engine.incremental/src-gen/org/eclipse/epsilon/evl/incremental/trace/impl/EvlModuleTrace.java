 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModuleElements;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTrace implements IEvlModuleTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The source.
	 */
    private String source;

    /**
     * * The module elements that conform the module.
       * Each language shoud specialize this class to represent the structure of its AST.
     */
    private final IModuleExecutionTraceHasModuleElements moduleElements;

    /**
     * * The different accesses that where recorded during execution.
     */
    private final IModuleExecutionTraceHasAccesses accesses;

    /**
     * * The different models involved in the execution
     */
    private final IModuleExecutionTraceHasModels models;

    /**
     * Instantiates a new EvlModuleTrace. The EvlModuleTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTrace(String source) throws TraceModelDuplicateRelation {
        this.source = source;
        this.moduleElements = new ModuleExecutionTraceHasModuleElements(this);
        this.accesses = new ModuleExecutionTraceHasAccesses(this);
        this.models = new ModuleExecutionTraceHasModels(this);

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
    public String getSource() {
        return source;
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        return models;
    }


                  
                  
    @Override
    public IModelTrace createModelTrace(String name, String uri) throws EolIncrementalExecutionException {
        IModelTrace modelTrace = null;
        try {
            modelTrace = new ModelTrace(name, uri, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelTrace != null) {
    	        return modelTrace;
    	    }
            try {
                modelTrace = this.models.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .filter(item -> item.getUri().equals(uri))
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
    public boolean sameIdentityAs(final IEvlModuleTrace other) {
        if (other == null) {
            return false;
        }
        String source = getSource();
        String otherSource = other.getSource();
        if (source == null) {
            if (otherSource != null)
                return false;
        } else if (!source.equals(otherSource)) {
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
        if (!(obj instanceof EvlModuleTrace))
            return false;
        EvlModuleTrace other = (EvlModuleTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }
}
