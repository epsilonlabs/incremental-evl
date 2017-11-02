 /*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
 /*******************************************************************************
 ** PropertyAccess implementation automatically generated on 2017-10-20.
 ** Do not modify this file.
 *******************************************************************************/
 
package org.eclipse.epsilon.eol.incremental.trace.tinkerpop;

import java.util.Iterator;

import org.eclipse.epsilon.incremental.trace.eol.*;

import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An abstract, generic, implementation of PropertyAccess. The generic component allows the class
 * to be reused for different Databases. 
 *
 * @param <V> the specific DB vertex type.
 */
public abstract class AbstractPropertyAccessImpl<V> implements PropertyAccess {

    /** The delegate vertex in the Graph. */
    final protected V delegate;
 
    /**
     * Instantiates a new PropertyAccess implementation.
     *
     * @param delegate the delegate vertex
     */
    public AbstractPropertyAccessImpl(V delegate) {
        this.delegate = delegate;
    }
    
    /**
     * Get the delegate vertex
     */
    public V getDelegate() {
        return delegate;
    }
    
    /**
     * Get the EReference value(s) using the provided pipeline
     * @param pipeline
     * @return
     */
    protected V getExecution(GremlinPipeline<V, V> pipeline) {
        pipeline.start(delegate).out("execution");
        if (pipeline.hasNext()) {
            return pipeline.next();
        }
        return null;
    }
    /**
     * Get the EReference value(s) using the provided pipeline
     * @param pipeline
     * @return
     */
    protected V getProperty(GremlinPipeline<V, V> pipeline) {
        pipeline.start(delegate).in("property");
        if (pipeline.hasNext()) {
            return pipeline.next();
        }
        return null;
    }
    

}