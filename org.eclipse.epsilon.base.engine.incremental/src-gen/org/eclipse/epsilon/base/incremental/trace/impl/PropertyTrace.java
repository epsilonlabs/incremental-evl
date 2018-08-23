 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region PropertyTraceImports on begin **/
/** protected region PropertyTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IPropertyTrace. 
 */
public class PropertyTrace implements IPropertyTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The name.
	 */
    private String name;

    /**
     * The elementTrace.
     */
    private final IPropertyTraceHasElementTrace elementTrace;

    /**
     * Instantiates a new PropertyTrace. The PropertyTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyTrace(String name, IModelElementTrace container) throws TraceModelDuplicateRelation {
        this.name = name;
        this.elementTrace = new PropertyTraceHasElementTrace(this);

        if (!container.properties().create(this)) {
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
    public IPropertyTraceHasElementTrace elementTrace() {
        return elementTrace;
    }

    @Override
    public boolean sameIdentityAs(final IPropertyTrace other) {
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
        if (!(obj instanceof PropertyTrace))
            return false;
        PropertyTrace other = (PropertyTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        if (elementTrace.get() == null) {
            if (other.elementTrace.get() != null)
                return false;
        }
        if (!elementTrace.get().equals(other.elementTrace.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((elementTrace.get() == null) ? 0 : elementTrace.get().hashCode());
        return result;
    }
}
