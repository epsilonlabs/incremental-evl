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
package org.eclipse.epsilon.base.incremental.trace;

import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    

/**
 * An ElementAccess denotes access to a model element as a whole. This usually
   happens when an element is used for a context.
 
 */
public interface IElementAccess extends IAccess {


    /** The element reference. */
    IElementAccessHasElement element();
                
 
    /**
     * ElementAccess has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IElementAccess other);
    
}
