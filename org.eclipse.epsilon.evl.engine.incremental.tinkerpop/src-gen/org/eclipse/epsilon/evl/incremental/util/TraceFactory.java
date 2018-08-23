 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * A Factory to wrap Vertices around concrete classes based on the Vertex label.
 * It is useful for instantiating domain elements back from Gremlin elements
 * 
 * @author Horacio Hoyos
 *
 */
public class TraceFactory {

    public static Object createModuleElementTrace(Element delegate, Graph graph) {
        String label = delegate.label();
        switch (label) {
  
        case "EvlModuleTrace":
            EvlModuleTraceGremlin evlModuleTrace = new EvlModuleTraceGremlin();
            evlModuleTrace.delegate((Vertex) delegate);
            evlModuleTrace.graph(graph);
            return evlModuleTrace;
  
        case "ContextTrace":
            ContextTraceGremlin contextTrace = new ContextTraceGremlin();
            contextTrace.delegate((Vertex) delegate);
            contextTrace.graph(graph);
            return contextTrace;
  
        case "InvariantTrace":
            InvariantTraceGremlin invariantTrace = new InvariantTraceGremlin();
            invariantTrace.delegate((Vertex) delegate);
            invariantTrace.graph(graph);
            return invariantTrace;
  
        case "GuardTrace":
            GuardTraceGremlin guardTrace = new GuardTraceGremlin();
            guardTrace.delegate((Vertex) delegate);
            guardTrace.graph(graph);
            return guardTrace;
  
        case "CheckTrace":
            CheckTraceGremlin checkTrace = new CheckTraceGremlin();
            checkTrace.delegate((Vertex) delegate);
            checkTrace.graph(graph);
            return checkTrace;
  
        case "MessageTrace":
            MessageTraceGremlin messageTrace = new MessageTraceGremlin();
            messageTrace.delegate((Vertex) delegate);
            messageTrace.graph(graph);
            return messageTrace;
  
        case "SatisfiesTrace":
            SatisfiesTraceGremlin satisfiesTrace = new SatisfiesTraceGremlin();
            satisfiesTrace.delegate((Vertex) delegate);
            satisfiesTrace.graph(graph);
            return satisfiesTrace;
  
        case "ModelAccess":
            ModelAccessGremlin modelAccess = new ModelAccessGremlin();
            modelAccess.delegate((Vertex) delegate);
            modelAccess.graph(graph);
            return modelAccess;
  
        case "ExecutionContext":
            ExecutionContextGremlin executionContext = new ExecutionContextGremlin();
            executionContext.delegate((Vertex) delegate);
            executionContext.graph(graph);
            return executionContext;
  
        case "ModelElementVariable":
            ModelElementVariableGremlin modelElementVariable = new ModelElementVariableGremlin();
            modelElementVariable.delegate((Vertex) delegate);
            modelElementVariable.graph(graph);
            return modelElementVariable;
  
        case "ElementAccess":
            ElementAccessGremlin elementAccess = new ElementAccessGremlin();
            elementAccess.delegate((Vertex) delegate);
            elementAccess.graph(graph);
            return elementAccess;
  
        case "AllInstancesAccess":
            AllInstancesAccessGremlin allInstancesAccess = new AllInstancesAccessGremlin();
            allInstancesAccess.delegate((Vertex) delegate);
            allInstancesAccess.graph(graph);
            return allInstancesAccess;
  
        case "PropertyAccess":
            PropertyAccessGremlin propertyAccess = new PropertyAccessGremlin();
            propertyAccess.delegate((Vertex) delegate);
            propertyAccess.graph(graph);
            return propertyAccess;
  
        case "ModelTrace":
            ModelTraceGremlin modelTrace = new ModelTraceGremlin();
            modelTrace.delegate((Vertex) delegate);
            modelTrace.graph(graph);
            return modelTrace;
  
        case "ModelTypeTrace":
            ModelTypeTraceGremlin modelTypeTrace = new ModelTypeTraceGremlin();
            modelTypeTrace.delegate((Vertex) delegate);
            modelTypeTrace.graph(graph);
            return modelTypeTrace;
  
        case "ModelElementTrace":
            ModelElementTraceGremlin modelElementTrace = new ModelElementTraceGremlin();
            modelElementTrace.delegate((Vertex) delegate);
            modelElementTrace.graph(graph);
            return modelElementTrace;
  
        case "PropertyTrace":
            PropertyTraceGremlin propertyTrace = new PropertyTraceGremlin();
            propertyTrace.delegate((Vertex) delegate);
            propertyTrace.graph(graph);
            return propertyTrace;
        
        }
        return null;
    }
}
