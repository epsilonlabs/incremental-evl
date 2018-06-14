 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelTraceImports on begin **/
/** protected region ModelTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceHasElements;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceHasTypes;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceHasElements;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceHasTypes;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTrace;

/**
 * Implementation of IModelTrace. 
 */
public class ModelTrace implements IModelTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The name.
	 */
    private String name;

    /**
	 * The uri.
	 */
    private String uri;

    /**
     * The elements.
     */
    private final IModelTraceHasElements elements;

    /**
     * The types.
     */
    private final IModelTraceHasTypes types;

    /**
     * Instantiates a new ModelTrace. The ModelTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTrace(String name, String uri, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.uri = uri;
        this.elements = new ModelTraceHasElements(this);
        this.types = new ModelTraceHasTypes(this);

        if (!container.models().create(this)) {
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
    public String getUri() {
        return uri;
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
                    .filter(item -> item.getUri() == uri)
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
                    .filter(item -> item.getName() == name)
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
        String name = getName();
        String otherName = other.getName();
        if (name == null) {
            if (otherName != null)
                return false;
        } else if (!name.equals(otherName)) {
            return false;
        }
        String uri = getUri();
        String otherUri = other.getUri();
        if (uri == null) {
            if (otherUri != null)
                return false;
        } else if (!uri.equals(otherUri)) {
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
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }
}
