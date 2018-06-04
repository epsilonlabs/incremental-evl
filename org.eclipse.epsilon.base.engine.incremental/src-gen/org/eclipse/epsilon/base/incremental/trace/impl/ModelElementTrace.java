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

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelElementTraceImports on begin **/

/** protected region ModelElementTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTraceHasProperties;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyTrace;

/**
 * Implementation of IModelElementTrace. 
 */
public class ModelElementTrace implements IModelElementTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The uri.
	 */
    private String uri;

    /**
     * The properties.
     */
    private final IModelElementTraceHasProperties properties;

    /**
     * Instantiates a new ModelElementTrace. The ModelElementTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelElementTrace(String uri, IModelTrace container) throws TraceModelDuplicateRelation {
        this.uri = uri;
        // Not derived org.eclipse.emf.ecore.impl.EReferenceImpl@d21fd12 (name: properties) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: true)
        this.properties = new ModelElementTraceHasProperties(this);

        if (!container.elements().create(this)) {
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
    public String getUri() {
        return uri;
    }
    
    @Override
    public IModelElementTraceHasProperties properties() {
        return properties;
    }


    @Override
    public IPropertyTrace createPropertyTrace(String name) throws EolIncrementalExecutionException {
        IPropertyTrace propertyTrace = null;
        try {
            propertyTrace = new PropertyTrace(name, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyTrace != null) {
    	        return propertyTrace;
    	    }
            try {
                propertyTrace = this.properties.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return propertyTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IModelElementTrace other) {
        if (other == null) {
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
        if (!(obj instanceof ModelElementTrace))
            return false;
        ModelElementTrace other = (ModelElementTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }
}
