 /*******************************************************************************
 * This file was automatically generated on: Wed Oct 18 16:50:26 BST 2017.
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
import org.eclipse.epsilon.incremental.trace.eol.ElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.tinkerpop.AbstractPropertyImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the Property that delegates to an OrientDB vertex.
 */
public class PropertyOrientDbImpl extends AbstractPropertyImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "Property";
    
    /** The attribute to use as index for vertices of this type */
    public static final String VERTEX_INDEX = "uri";
    
    /** The attribute name in the DB */
    public static final String URI = "uri";
    
    /** The attribute name in the DB */
    public static final String VALUE = "value";
    
    /** Placeholder for referenced values */
    private Set<ElementTrace> traces;
    
    
	/**
     * Instantiates a new Property OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public PropertyOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "Property")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "Property"));
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
        // TODO Implement PropertyOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    PropertyOrientDbImpl.setId invoked.");
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
    public String getValue() {
        return (String) delegate.getProperty("value");
    }
    
    @Override
    public void setValue(String value) {
        delegate.setProperty("value", value);
    }    
    @Override
    public Set<ElementTrace> getTraces() {
        return traces;
    }
    
    private void initTraces() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        traces = new HashSet<>();
        Iterator<OrientVertex> it = getTraces(pipeline);
        while (it.hasNext()) {
            ElementTrace vImpl = new ElementTraceOrientDbImpl(it.next());
            traces.add(vImpl);
        }
    
    }
      
}
