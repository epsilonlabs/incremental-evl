 /*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
 /*******************************************************************************
 ** Check implementation automatically generated on 2017-10-18.
 ** Do not modify this file.
 *******************************************************************************/
 
package org.eclipse.epsilon.evl.incremental.trace.tinkerpop;

import org.eclipse.epsilon.eol.incremental.trace.tinkerpop.AbstractExecutableBlockImpl;
import org.eclipse.epsilon.incremental.trace.evl.*;

/**
 * An abstract, generic, implementation of Check. The generic component allows the class
 * to be reused for different Databases. 
 *
 * @param <V> the specifc DB vertex type.
 */
public abstract class AbstractCheckImpl<V> extends AbstractExecutableBlockImpl<V> {

    /**
     * Instantiates a new Check implementation.
     *
     * @param delegate the delegate vertex
     */
    public AbstractCheckImpl(V delegate) {
        super(delegate);
    }
    
    

}