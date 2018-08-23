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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
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
 
    protected Graph extent;    
    
    public ModuleExecutionTraceGremlinRepositoryImpl() {
    }


    public void injectGraph(Module tinkerpopGuiceModule) {
        Injector injector = Guice.createInjector(tinkerpopGuiceModule);
        Graph g = injector.getInstance(Graph.class);
        this.extent = g;
    }
    
    
    /** protected region IModuleExecutionTraceRepositry on begin **/
    // Specialised search methods

    /** protected region IModuleExecutionTraceRepositry end **/

}