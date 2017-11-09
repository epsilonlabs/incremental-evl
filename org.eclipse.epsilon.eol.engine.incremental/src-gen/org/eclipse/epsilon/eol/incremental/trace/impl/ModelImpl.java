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
package org.eclipse.epsilon.eol.incremental.trace.impl;

import org.eclipse.epsilon.eol.incremental.trace.Model;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasElements;
import org.eclipse.epsilon.eol.incremental.trace.ModelHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelHasElementsImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelHasTypesImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelTypeImpl;

/**
 * Implementation of Model. 
 */
public class ModelImpl implements Model {

    /** The id */
    private Object id;

    /** The name */
    private String name;

    /** The elements relation */
    private final ModelHasElements elements;

    /** The types relation */
    private final ModelHasTypes types;

    /**
     * Instantiates a new Model. The Model is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelImpl(String name, ExecutionTrace container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.elements = new ModelHasElementsImpl(this);
        this.types = new ModelHasTypesImpl(this);
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
    public ModelHasElements elements() {
        return elements;
    }

    @Override
    public ModelHasTypes types() {
        return types;
    }

    @Override
    public ModelElement createModelElement(String uri) throws EolIncrementalExecutionException {
            try {
                return new ModelElementImpl(uri, this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            ModelElement modelElement = null;
            
            try {
                modelElement = this.elements.get().stream()
                    .filter(mt -> mt.getUri().equals(uri))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelElement was "
                        + "duplicate but previous one was not found.");
            }
            return modelElement;
    }      
                  
    @Override
    public ModelType createModelType(String name) throws EolIncrementalExecutionException {
            try {
                return new ModelTypeImpl(name, this);
            } catch (TraceModelDuplicateRelation e) {
                // Pass
            }
            ModelType modelType = null;
            
            try {
                modelType = this.types.get().stream()
                    .filter(mt -> mt.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelType was "
                        + "duplicate but previous one was not found.");
            }
            return modelType;
    }      
                  
    @Override
    public boolean sameIdentityAs(final Model other) {
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
        if (!(obj instanceof ModelImpl))
            return false;
        ModelImpl other = (ModelImpl) obj;
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
