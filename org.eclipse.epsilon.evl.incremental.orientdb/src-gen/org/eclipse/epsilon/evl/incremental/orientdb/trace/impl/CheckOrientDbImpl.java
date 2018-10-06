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

import org.eclipse.epsilon.base.incremental.trace.eol.ModuleElement;
import org.eclipse.epsilon.base.incremental.trace.eol.Trace;
import org.eclipse.epsilon.evl.incremental.trace.tinkerpop.AbstractCheckImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the Check that delegates to an OrientDB vertex.
 */
public class CheckOrientDbImpl extends AbstractCheckImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "Check";
    
    /** The attribute to use as index for vertices of this type */
    public static final String VERTEX_INDEX = "uri";
    
    /** The attribute name in the DB */
    public static final String URI = "uri";
    
    /** The attribute name in the DB */
    public static final String RESULT = "result";
    
    /** Placeholder for referenced values */
    private Set<Trace> traces;
    
    /** Placeholder for referenced values */
    private ModuleElement owner;
    
    
	/**
     * Instantiates a new Check OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public CheckOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "Check")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "Check"));
        }
        initTraces();
        initOwner();
        delegate.detach();      // After information has been cached, detach.
    }

    @Override
    public Object getId() {
        return delegate.getId();
    }
    
    @Override
    public void setId(Object value) {
        // TODO Implement CheckOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    CheckOrientDbImpl.setId invoked.");
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
    public String getResult() {
        return (String) delegate.getProperty("result");
    }
    
    @Override
    public void setResult(String value) {
        delegate.setProperty("result", value);
    }    
    @Override
    public Set<Trace> getTraces() {
        return traces;
    }
    
    private void initTraces() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        traces = new HashSet<>();
        Iterator<OrientVertex> it = getTraces(pipeline);
        while (it.hasNext()) {
            OrientVertex vertex = it.next();
            Trace vImpl = null;
            switch(vertex.getLabel()) {
            case("ElementTrace"):
                vImpl = new ElementTraceOrientDbImpl(vertex);
                break;
            case("TypeTrace"):
                vImpl = new TypeTraceOrientDbImpl(vertex);
                break;
      
            default:
                throw new IllegalArgumentException("Unknown Trace subclass " + vertex.getLabel());
            }
            traces.add(vImpl);
        }
    
    }
    @Override
    public ModuleElement getOwner() {
        return owner;
    }
    @Override
    public void setOwner(ModuleElement value) {
        throw new UnsupportedOperationException("Readonly field    ownerOrientDbImpl.setOwner invoked.");
    }
    
    private void initOwner() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        OrientVertex vertex = getOwner(pipeline);
        if (vertex != null) {
            switch(vertex.getLabel()) {
	        case("EvlConstraint"):
	            owner = new EvlConstraintOrientDbImpl(vertex);
	            break;
	        case("EvlContext"):
	            owner = new EvlContextOrientDbImpl(vertex);
	            break;
      
	        default:
	            throw new IllegalArgumentException("Unknown ModuleElement subclass " + vertex.getLabel());
	        }
	    }
    
    }
      
}
