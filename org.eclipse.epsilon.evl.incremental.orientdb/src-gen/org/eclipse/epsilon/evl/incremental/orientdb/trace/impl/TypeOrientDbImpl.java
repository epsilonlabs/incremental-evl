 /*******************************************************************************
 * This file was automatically generated on: Thu Oct 19 08:58:19 BST 2017.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.eol.TypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.tinkerpop.AbstractTypeImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the Type that delegates to an OrientDB vertex.
 */
public class TypeOrientDbImpl extends AbstractTypeImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "Type";
    
    /** The attribute to use as index for vertices of this type */
    public static final String VERTEX_INDEX = "uri";
    
    /** The attribute name in the DB */
    public static final String URI = "uri";
    
    /** Placeholder for referenced values */
    private Set<TypeTrace> traces;
    
    
	/**
     * Instantiates a new Type OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public TypeOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "Type")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "Type"));
        }
        initTraces();
        delegate.detach();      // After information has been cached, detach.
    }

    @Override
    public Object getId() {
        return delegate.getId();
    }
    
    @Override
    public void setId(Object value) {
        // TODO Implement TypeOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    TypeOrientDbImpl.setId invoked.");
    }
    

    @Override
    public String getUri() {
        return (String) delegate.getProperty("uri");
    }
    
    @Override
    public void setUri(String value) {
        delegate.setProperty("uri", value);
    }    
    @Override
    public Set<TypeTrace> getTraces() {
        return traces;
    }
    
    private void initTraces() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        traces = new HashSet<>();
        Iterator<OrientVertex> it = getTraces(pipeline);
        while (it.hasNext()) {
            TypeTrace vImpl = new TypeTraceOrientDbImpl(it.next());
            traces.add(vImpl);
        }
    
    }
      
}
