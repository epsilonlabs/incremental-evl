 /*******************************************************************************
 * This file was automatically generated on: 2019-04-25.
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
    public Object createTraceElement(Vertex dlgt, GraphTraversalSource gts) {
        String label = dlgt.label();
        switch (label) {
  
        case "ModelAccess":
            return new ModelAccessGremlin(dlgt, gts, this);
  
        case "ExecutionContext":
            return new ExecutionContextGremlin(dlgt, gts, this);
  
        case "ModelElementVariable":
            return new ModelElementVariableGremlin(dlgt, gts, this);
  
        case "ElementAccess":
            return new ElementAccessGremlin(dlgt, gts, this);
  
        case "AllInstancesAccess":
            return new AllInstancesAccessGremlin(dlgt, gts, this);
  
        case "PropertyAccess":
            return new PropertyAccessGremlin(dlgt, gts, this);
  
        case "ModelTrace":
            return new ModelTraceGremlin(dlgt, gts, this);
  
        case "ModelTypeTrace":
            return new ModelTypeTraceGremlin(dlgt, gts, this);
  
        case "ModelElementTrace":
            return new ModelElementTraceGremlin(dlgt, gts, this);
  
        case "PropertyTrace":
            return new PropertyTraceGremlin(dlgt, gts, this);
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
