 /*******************************************************************************
 * This file was automatically generated on: 2018-06-05.
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

import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region PropertyAccessImports on begin **/
/** protected region PropertyAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAccessHasExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccessHasProperty;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.AccessHasExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyAccessHasProperty;

/**
 * Implementation of IPropertyAccess. 
 */
public class PropertyAccess implements IPropertyAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The value.
	 */
    private String value;

    /**
     * The executionTrace.
     */
    private final IAccessHasExecutionTrace executionTrace;

    /**
     * The property.
     */
    private final IPropertyAccessHasProperty property;

    /**
     * Instantiates a new PropertyAccess. The PropertyAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public PropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property, IModuleExecutionTrace container) throws TraceModelDuplicateRelation {
        // From Equals org.eclipse.emf.ecore.impl.EReferenceImpl@49c599bb (name: property) (ordered: true, unique: true, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: false, resolveProxies: true)
        this.property = new PropertyAccessHasProperty(this);
        if (!this.property.create(property)) {
            throw new TraceModelDuplicateRelation();
        }
        this.executionTrace = new AccessHasExecutionTrace(this);
        if (!this.executionTrace.create(executionTrace)) {
            throw new TraceModelDuplicateRelation();
        }

        if (!container.accesses().create(this)) {
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
    public String getValue() {
        return value;
    }
    
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }   
     
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        return executionTrace;
    }

    @Override
    public IPropertyAccessHasProperty property() {
        return property;
    }


    @Override
    public boolean sameIdentityAs(final IPropertyAccess other) {
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
        if (!(obj instanceof PropertyAccess))
            return false;
        PropertyAccess other = (PropertyAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        if (property.get() == null) {
            if (other.property.get() != null)
                return false;
        }
        if (!property.get().equals(other.property.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((property.get() == null) ? 0 : property.get().hashCode());
        return result;
    }
}
