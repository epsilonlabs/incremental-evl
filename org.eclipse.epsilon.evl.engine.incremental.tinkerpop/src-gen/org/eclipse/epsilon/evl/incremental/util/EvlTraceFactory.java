 /*******************************************************************************
 * This file was automatically generated on: 2019-05-31.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * A Factory to wrap Vertices around concrete classes based on the Vertex label.
 * It is useful for instantiating domain elements back from Gremlin elements. This Factory delegates
 * to another factory for labels (types) it does not support, if such facotry exists.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class EvlTraceFactory implements TraceFactory {

    private final TraceFactory delegate;
    
    public EvlTraceFactory () {
        delegate = new BaseTraceFactory();
    }
    
    @Override
    public <E> E createTraceElement(Vertex dlgt, GraphTraversalSource gts) {
        return createTraceElement(dlgt, gts, this);
    
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <E> E createTraceElement(Vertex dlgt, GraphTraversalSource gts, TraceFactory subFactory) {
        String label = dlgt.label();
        switch (label) {
        case "EvlModuleTrace":
            return (E) new EvlModuleTraceGremlin(dlgt, gts, subFactory);
        case "ContextTrace":
            return (E) new ContextTraceGremlin(dlgt, gts, subFactory);
        case "InvariantTrace":
            return (E) new InvariantTraceGremlin(dlgt, gts, subFactory);
        case "GuardResult":
            return (E) new GuardResultGremlin(dlgt, gts, subFactory);
        case "GuardTrace":
            return (E) new GuardTraceGremlin(dlgt, gts, subFactory);
        case "CheckResult":
            return (E) new CheckResultGremlin(dlgt, gts, subFactory);
        case "CheckTrace":
            return (E) new CheckTraceGremlin(dlgt, gts, subFactory);
        case "MessageTrace":
            return (E) new MessageTraceGremlin(dlgt, gts, subFactory);
        case "SatisfiesAccess":
            return (E) new SatisfiesAccessGremlin(dlgt, gts, subFactory);
        /* protected region EvlTraceFactory on begin */
        /* protected region EvlTraceFactory end */
        default:
            return delegate.createTraceElement(dlgt, gts, subFactory);
        }
    }
}
