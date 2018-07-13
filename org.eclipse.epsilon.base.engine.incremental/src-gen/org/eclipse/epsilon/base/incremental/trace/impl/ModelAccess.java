 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
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

import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region ModelAccessImports on begin **/
/** protected region ModelAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IModelAccess. 
 */
public class ModelAccess implements IModelAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The modelName.
	 */
    private String modelName;

    /**
     * The modelTrace.
     */
    private final IModelAccessHasModelTrace modelTrace;

    /**
     * Instantiates a new ModelAccess. The ModelAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ModelAccess(String modelName, IModelTrace modelTrace, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        this.modelName = modelName;
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@c21e26d (name: modelTrace) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.modelTrace = new ModelAccessHasModelTrace(this);
        if (!this.modelTrace.create(modelTrace)) {
            throw new TraceModelDuplicateRelation();
        }

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
    public String getModelName() {
        return modelName;
    }
    
    @Override
    public IModelAccessHasModelTrace modelTrace() {
        return modelTrace;
    }

    @Override
    public boolean sameIdentityAs(final IModelAccess other) {
        if (other == null) {
            return false;
        }
        String modelName = getModelName();
        String otherModelName = other.getModelName();
        if (modelName == null) {
            if (otherModelName != null)
                return false;
        } else if (!modelName.equals(otherModelName)) {
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
        if (!(obj instanceof ModelAccess))
            return false;
        ModelAccess other = (ModelAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        if (modelTrace.get() == null) {
            if (other.modelTrace.get() != null)
                return false;
        }
        if (!modelTrace.get().equals(other.modelTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
        result = prime * result + ((modelTrace.get() == null) ? 0 : modelTrace.get().hashCode());
        return result;
    }
}
