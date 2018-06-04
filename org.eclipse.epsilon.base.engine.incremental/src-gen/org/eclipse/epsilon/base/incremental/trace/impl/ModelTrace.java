 /*******************************************************************************
 * This file was automatically generated on: 2018-05-31.
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
	 * The uri of the model.
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
    public ModelTrace(String name, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        this.name = name;
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@56a01b19 (name: elements) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
        this.elements = new ModelTraceHasElements(this);
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@6d098f04 (name: types) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
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
    public void setUri(String value) {
        this.uri = value;
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
        String name = getName();
        String otherName = other.getName();
        if (name == null) {
            if (otherName != null)
                return false;
        } else if (!name.equals(otherName)) {
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
        return result;
    }
}
