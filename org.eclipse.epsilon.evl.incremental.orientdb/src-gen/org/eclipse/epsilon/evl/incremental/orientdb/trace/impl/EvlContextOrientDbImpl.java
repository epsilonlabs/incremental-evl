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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.eol.ExecutableBlock;
import org.eclipse.epsilon.evl.incremental.trace.tinkerpop.AbstractEvlContextImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the EvlContext that delegates to an OrientDB vertex.
 */
public class EvlContextOrientDbImpl extends AbstractEvlContextImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "EvlContext";
    
    /** The attribute to use as index for vertices of this type */
    public static final String VERTEX_INDEX = "uri";
    
    /** The attribute name in the DB */
    public static final String URI = "uri";
    
    /** Placeholder for referenced values */
    private List<ExecutableBlock> executableblocks;
    
    
	/**
     * Instantiates a new EvlContext OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public EvlContextOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "EvlContext")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "EvlContext"));
        }
        initExecutableblocks();
        delegate.detach();      // After information has been cached, detach.
    }

    @Override
    public Object getId() {
        return delegate.getId();
    }
    
    @Override
    public void setId(Object value) {
        // TODO Implement EvlContextOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    EvlContextOrientDbImpl.setId invoked.");
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
    public List<ExecutableBlock> getExecutableblocks() {
        return executableblocks;
    }
    
    private void initExecutableblocks() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        executableblocks = new ArrayList<>();
        Iterator<OrientVertex> it = getExecutableblocks(pipeline);
        while (it.hasNext()) {
            OrientVertex vertex = it.next();
            ExecutableBlock vImpl = null;
            switch(vertex.getLabel()) {
            case("Guard"):
                vImpl = new GuardOrientDbImpl(vertex);
                break;
            case("Check"):
                vImpl = new CheckOrientDbImpl(vertex);
                break;
            case("Message"):
                vImpl = new MessageOrientDbImpl(vertex);
                break;
      
            default:
                throw new IllegalArgumentException("Unknown ExecutableBlock subclass " + vertex.getLabel());
            }
            executableblocks.add(vImpl);
        }
    
    }
      
}
