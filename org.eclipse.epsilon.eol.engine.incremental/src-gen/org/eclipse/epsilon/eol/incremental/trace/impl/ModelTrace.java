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
package org.eclipse.epsilon.eol.incremental.trace.impl;

import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTraceHasElements;
import org.eclipse.epsilon.eol.incremental.trace.IModelTraceHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTraceHasElements;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTraceHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTypeTrace;

/**
 * Implementation of IModelTrace. 
 */
public class ModelTrace implements IModelTrace {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The elements relation */
    private final IModelTraceHasElements elements;

    /** The types relation */
    private final IModelTraceHasTypes types;

    /**
     * Instantiates a new ModelTrace. The ModelTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTrace(String name, IModuleExecution container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.elements = new ModelTraceHasElements(this);
        this.types = new ModelTraceHasTypes(this);
        if (!container.model().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
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
    public String getName() {
        return name;
    }
    
    
    @Override
    public void setName(String value) {
        this.name = value;
    }   
     
    @Override
    public IModelTraceHasElements elements() {
        return elements;
    }

    @Override
    public IModelTraceHasTypes types() {
        return types;
    }

    @Override
    public IModelElementTrace createModelElementTrace(String uri) throws EolIncrementalExecutionException {
        IModelElementTrace modelElementTrace = null;
        try {
            modelElementTrace = new ModelElementTrace(uri, this);
            
            this.elements().create(modelElementTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelElementTrace != null) {
    	        return modelElementTrace;
    	    }
            try {
                modelElementTrace = this.elements.get().stream()
                    .filter(item -> item.getUri().equals(uri))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElementTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelElementTrace;
    }      
                  
    @Override
    public IModelTypeTrace createModelTypeTrace(String name) throws EolIncrementalExecutionException {
        IModelTypeTrace modelTypeTrace = null;
        try {
            modelTypeTrace = new ModelTypeTrace(name, this);
            
            this.types().create(modelTypeTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelTypeTrace != null) {
    	        return modelTypeTrace;
    	    }
            try {
                modelTypeTrace = this.types.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelTypeTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelTypeTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IModelTrace other) {
        if (other == null) {
            return false;
        }
        if (getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!getName().equals(other.getName()))
            return false;
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ModelTrace))
            return false;
        ModelTrace other = (ModelTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
