 /*******************************************************************************
 * This file was automatically generated on: 2019-04-30.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.util.BaseTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

/**
 * A Factory to wrap Vertices around concrete classes based on the Vertex label.
 * It is useful for instantiating domain elements back from Gremlin elements. This Factory delegates
 * to another factory for labels (types) it does not support, if such facotry exists.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class BaseTraceFactory implements TraceFactory {

    public BaseTraceFactory () { }
    
    @Override
    public <E> E createTraceElement(Vertex dlgt, GraphTraversalSource gts) {
        return createTraceElement(dlgt, gts, this);
    
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <E> E createTraceElement(Vertex dlgt, GraphTraversalSource gts, TraceFactory subFactory) {
        String label = dlgt.label();
        switch (label) {
        case "ModelAccess":
            return (E) new ModelAccessGremlin(dlgt, gts, subFactory);
        case "ExecutionContext":
            return (E) new ExecutionContextGremlin(dlgt, gts, subFactory);
        case "ModelElementVariable":
            return (E) new ModelElementVariableGremlin(dlgt, gts, subFactory);
        case "ElementAccess":
            return (E) new ElementAccessGremlin(dlgt, gts, subFactory);
        case "AllInstancesAccess":
            return (E) new AllInstancesAccessGremlin(dlgt, gts, subFactory);
        case "PropertyAccess":
            return (E) new PropertyAccessGremlin(dlgt, gts, subFactory);
        case "ModelTrace":
            return (E) new ModelTraceGremlin(dlgt, gts, subFactory);
        case "ModelTypeTrace":
            return (E) new ModelTypeTraceGremlin(dlgt, gts, subFactory);
        case "ModelElementTrace":
            return (E) new ModelElementTraceGremlin(dlgt, gts, subFactory);
        case "PropertyTrace":
            return (E) new PropertyTraceGremlin(dlgt, gts, subFactory);
        /* protected region BaseTraceFactory on begin */
        // Add special factory overrides 
        /* protected region BaseTraceFactory end */
        }
        throw new IllegalArgumentException(
                String.format("Trace Class %s not present in factory. If you changed the metamodel"
                        + "make sure the Ecore2DomainFactoryImpl egl is executed to regenerate this "
                        + "factory.", label));
    }
}
