 /*******************************************************************************
 * This file was automatically generated on: 2019-05-09.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
/** protected region ModuleExecutionTraceRepositoryImplImports on begin **/
/** protected region ModuleExecutionTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * A repository for handling elements in the domain of ModuleExecutionTrace.
 *
 * @author Horacio Hoyos Rodriguez
 */
@SuppressWarnings("unused")
public abstract class ModuleExecutionTraceGremlinRepositoryImpl<ST extends IModuleExecutionTrace> implements IModuleExecutionTraceRepository<ST> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleExecutionTraceGremlinRepositoryImpl.class);
 
    protected GraphTraversalSource gts; 
    protected final TraceFactory factory;
    
    @Inject
    public ModuleExecutionTraceGremlinRepositoryImpl(
        GraphTraversalSource trvrslSrc,
        TraceFactory fctry) {
        gts = trvrslSrc;
        factory = fctry;
        
    }

 
    
    /** protected region IModuleExecutionTraceRepositry on begin **/
    public void setGraphTraversalSource(final GraphTraversalSource gts) {
    	this.gts = gts;
    }
    
	@Override
	public void dispose() {
		try {
			gts.close();
		} catch (Exception e) {
			logger.warn("Error closing GraphTraversalSource",  e);
		}
	}
    
    /** protected region IModuleExecutionTraceRepositry end **/

}