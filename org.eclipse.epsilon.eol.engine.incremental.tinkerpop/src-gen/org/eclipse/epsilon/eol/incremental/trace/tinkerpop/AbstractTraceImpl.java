 /*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
 /*******************************************************************************
 ** Trace implementation automatically generated on 2017-10-18.
 ** Do not modify this file.
 *******************************************************************************/
 
package org.eclipse.epsilon.eol.incremental.trace.tinkerpop;

import java.util.Iterator;

import org.eclipse.epsilon.incremental.trace.eol.*;

import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An abstract, generic, implementation of Trace. The generic component allows the class
 * to be reused for different Databases. 
 *
 * @param <V> the specific DB vertex type.
 */
public abstract class AbstractTraceImpl<V> implements Trace {

    /** The delegate vertex in the Graph. */
    final protected V delegate;
 
    /**
     * Instantiates a new Trace implementation.
     *
     * @param delegate the delegate vertex
     */
    public AbstractTraceImpl(V delegate) {
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
    protected Iterator<V> getBlocks(GremlinPipeline<V, V> pipeline) {
        pipeline.start(delegate).out("blocks");
        return pipeline.iterator();
    }   

    

}