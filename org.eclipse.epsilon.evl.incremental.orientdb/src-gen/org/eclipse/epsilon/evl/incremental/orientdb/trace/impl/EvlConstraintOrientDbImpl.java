 /*******************************************************************************
 * This file was automatically generated on: Wed Oct 18 11:16:55 BST 2017.
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
import org.eclipse.epsilon.incremental.trace.eol.ExecutableBlock;
import org.eclipse.epsilon.evl.incremental.trace.tinkerpop.AbstractEvlConstraintImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the EvlConstraint that delegates to an OrientDB vertex.
 */
public class EvlConstraintOrientDbImpl extends AbstractEvlConstraintImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "EvlConstraint";
    
    /** The attribute to use as index for vertices of this type */
    public static final String VERTEX_INDEX = "uri";
    
    /** The attribute name in the DB */
    public static final String URI = "uri";
    
    /** Placeholder for referenced values */
    private List<ExecutableBlock> executableblocks;
    
    
	/**
     * Instantiates a new EvlConstraint OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public EvlConstraintOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "EvlConstraint")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "EvlConstraint"));
        }
        delegate.detach();
        initExecutableblocks();
    }

    @Override
    public Object getId() {
        return delegate.getId();
    }
    
    @Override
    public void setId(Object value) {
        // TODO Implement EvlConstraintOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    EvlConstraintOrientDbImpl.setId invoked.");
    }
    

    @Override
    public String getUri() {
        return (String) delegate.getProperties().get("uri");
    }
    
    @Override
    public void setUri(String value) {
        delegate.getProperties().put("EvlConstraint", value);
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
