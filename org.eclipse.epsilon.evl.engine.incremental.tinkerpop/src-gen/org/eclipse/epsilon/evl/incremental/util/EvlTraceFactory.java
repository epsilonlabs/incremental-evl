 /*******************************************************************************
 * This file was automatically generated on: 2019-02-07.
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
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * A Factory to wrap Vertices around concrete classes based on the Vertex label.
 * It is useful for instantiating domain elements back from Gremlin elements
 * 
 * @author Horacio Hoyos
 *
 */
public class EvlTraceFactory implements TraceFactory {
    
    private final TraceFactory delegate;
    
    protected EvlTraceFactory (TraceFactory dlgtFctry) {
        delegate = dlgtFctry;
    }
    
    public Object createTraceElement(Vertex dlgt, GraphTraversalSource gts) {
        String label = dlgt.label();
        switch (label) {
  
        case "EvlModuleTrace":
            EvlModuleTraceGremlin evlModuleTrace = new EvlModuleTraceGremlin();
            evlModuleTrace.delegate((Vertex) dlgt);
            evlModuleTrace.graphTraversalSource(gts);
            return evlModuleTrace;
  
        case "ContextTrace":
            ContextTraceGremlin contextTrace = new ContextTraceGremlin();
            contextTrace.delegate((Vertex) dlgt);
            contextTrace.graphTraversalSource(gts);
            return contextTrace;
  
        case "InvariantTrace":
            InvariantTraceGremlin invariantTrace = new InvariantTraceGremlin();
            invariantTrace.delegate((Vertex) dlgt);
            invariantTrace.graphTraversalSource(gts);
            return invariantTrace;
  
        case "GuardResult":
            GuardResultGremlin guardResult = new GuardResultGremlin();
            guardResult.delegate((Vertex) dlgt);
            guardResult.graphTraversalSource(gts);
            return guardResult;
  
        case "GuardTrace":
            GuardTraceGremlin guardTrace = new GuardTraceGremlin();
            guardTrace.delegate((Vertex) dlgt);
            guardTrace.graphTraversalSource(gts);
            return guardTrace;
  
        case "CheckResult":
            CheckResultGremlin checkResult = new CheckResultGremlin();
            checkResult.delegate((Vertex) dlgt);
            checkResult.graphTraversalSource(gts);
            return checkResult;
  
        case "CheckTrace":
            CheckTraceGremlin checkTrace = new CheckTraceGremlin();
            checkTrace.delegate((Vertex) dlgt);
            checkTrace.graphTraversalSource(gts);
            return checkTrace;
  
        case "MessageTrace":
            MessageTraceGremlin messageTrace = new MessageTraceGremlin();
            messageTrace.delegate((Vertex) dlgt);
            messageTrace.graphTraversalSource(gts);
            return messageTrace;
  
        case "SatisfiesAccess":
            SatisfiesAccessGremlin satisfiesAccess = new SatisfiesAccessGremlin();
            satisfiesAccess.delegate((Vertex) dlgt);
            satisfiesAccess.graphTraversalSource(gts);
            return satisfiesAccess;
        /* protected region EvlTraceFactory on begin */

        /* protected region EvlTraceFactory end */
        default:
            return delegate.createTraceElement(dlgt, gts);
        }
    }
}
