 /*******************************************************************************
 * This file was automatically generated on: Tue Oct 17 12:38:47 BST 2017.
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
import org.eclipse.epsilon.incremental.trace.eol.ModelElement;
import org.eclipse.epsilon.incremental.trace.eol.Property;
import org.eclipse.epsilon.eol.incremental.trace.tinkerpop.AbstractElementTraceImpl;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * An implementation of the ElementTrace that delegates to an OrientDB vertex.
 */
public class ElementTraceOrientDbImpl extends AbstractElementTraceImpl<OrientVertex> {
    
    /** The class name for Vertices of this type in the DB schema */
    public static final String VERTEX_TYPE = "ElementTrace";
    /** Placeholder for referenced values */
    private List<ExecutableBlock> blocks;
    
    /** Placeholder for referenced values */
    private List<ModelElement> elements;
    
    /** Placeholder for referenced values */
    private List<Property> accesses;
    
    
	/**
     * Instantiates a new ElementTrace OrientDb Impl.
     *
     * @param delegate the delegate
     */
    public ElementTraceOrientDbImpl(OrientVertex  delegate) {
        super(delegate);
        if (!(delegate.getLabel() == "ElementTrace")) {
            throw new IllegalArgumentException(
                    String.format("Delegate vertex is not of the correct type. Got: %s, expected: %s",
                        delegate.getLabel(), "ElementTrace"));
        }
        initBlocks();
        initElements();
        initAccesses();
    }

    @Override
    public Object getId() {
        return delegate.getId();
    }
    
    @Override
    public void setId(Object value) {
        // TODO Implement ElementTraceOrientDbImpl.setId
        throw new UnsupportedOperationException("Readonly field    ElementTraceOrientDbImpl.setId invoked.");
    }
    
    @Override
    public List<ExecutableBlock> getBlocks() {
        return blocks;
    }
    
    private void initBlocks() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        blocks = new ArrayList<>();
        Iterator<OrientVertex> it = getBlocks(pipeline);
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
            blocks.add(vImpl);
        }
    
    }
    @Override
    public List<ModelElement> getElements() {
        return elements;
    }
    
    private void initElements() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        elements = new ArrayList<>();
        Iterator<OrientVertex> it = getElements(pipeline);
        while (it.hasNext()) {
            ModelElement vImpl = new ModelElementOrientDbImpl(it.next());
            elements.add(vImpl);
        }
    
    }
    @Override
    public List<Property> getAccesses() {
        return accesses;
    }
    
    private void initAccesses() {
        GremlinPipeline<OrientVertex, OrientVertex> pipeline = new GremlinPipeline<>();
        accesses = new ArrayList<>();
        Iterator<OrientVertex> it = getAccesses(pipeline);
        while (it.hasNext()) {
            Property vImpl = new PropertyOrientDbImpl(it.next());
            accesses.add(vImpl);
        }
    
    }
      
}
