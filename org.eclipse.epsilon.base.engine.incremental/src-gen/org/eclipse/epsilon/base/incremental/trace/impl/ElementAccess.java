 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region ElementAccessImports on begin **/
/** protected region ElementAccessImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * Implementation of IElementAccess. 
 */
public class ElementAccess implements IElementAccess {

    /**
	 * The id.
	 */
    private Object id;

    /**
     * The executionTrace.
     */
    private final IAccessHasExecutionTrace executionTrace;

    /**
     * The element.
     */
    private final IElementAccessHasElement element;

    /**
     * Instantiates a new ElementAccess. The ElementAccess is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ElementAccess(IModuleElementTrace executionTrace,
                         IModelElementTrace element,
                         IModuleExecutionTrace container) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        if (!container.accesses().create(this)) {
            throw new TraceModelDuplicateElement();
        };

        this.executionTrace = new AccessHasExecutionTrace(this);
        this.element = new ElementAccessHasElement(this);
        if (!this.executionTrace.create(executionTrace)) {
            throw new TraceModelDuplicateElement();
        }
        if (!this.element.create(element)) {
            throw new TraceModelDuplicateElement();
        }


    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
        this.id = value;
    }   
     
    @Override
    public IAccessHasExecutionTrace executionTrace() {
        return executionTrace;
    }

    @Override
    public IElementAccessHasElement element() {
        return element;
    }

    @Override
    public boolean sameIdentityAs(final IElementAccess other) {
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
        if (!(obj instanceof ElementAccess))
            return false;
        ElementAccess other = (ElementAccess) obj;
        if (!sameIdentityAs(other))
            return false;
        if (executionTrace.get() == null) {
            if (other.executionTrace.get() != null)
                return false;
        }
        if (!executionTrace.get().equals(other.executionTrace.get())) {
            return false;
        }
        if (element.get() == null) {
            if (other.element.get() != null)
                return false;
        }
        if (!element.get().equals(other.element.get())) {
            return false;
        }
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((executionTrace.get() == null) ? 0 : executionTrace.get().hashCode());
        result = prime * result + ((element.get() == null) ? 0 : element.get().hashCode());
        return result;
    }
}
