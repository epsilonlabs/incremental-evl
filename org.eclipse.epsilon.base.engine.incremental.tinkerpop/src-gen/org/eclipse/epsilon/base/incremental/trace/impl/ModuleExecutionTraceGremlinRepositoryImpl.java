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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
/** protected region ModuleExecutionTraceRepositoryImplImports on begin **/
/** protected region ModuleExecutionTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public abstract class ModuleExecutionTraceGremlinRepositoryImpl<E extends IModuleExecutionTrace> implements IModuleExecutionTraceRepository<E> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleExecutionTraceGremlinRepositoryImpl.class);
 
    protected GraphTraversalSource gts; 
    
    public ModuleExecutionTraceGremlinRepositoryImpl() {
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