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

import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region ModelTypeTraceImports on begin **/

/** protected region ModelTypeTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;

/**
 * Implementation of IModelTypeTrace. 
 */
public class ModelTypeTrace implements IModelTypeTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The name.
	 */
    private String name;

    /**
     * Instantiates a new ModelTypeTrace. The ModelTypeTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelTypeTrace(String name, IModelTrace container) throws TraceModelDuplicateRelation {
        this.name = name;

        if (!container.types().create(this)) {
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
    public boolean sameIdentityAs(final IModelTypeTrace other) {
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
        if (!(obj instanceof ModelTypeTrace))
            return false;
        ModelTypeTrace other = (ModelTypeTrace) obj;
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
