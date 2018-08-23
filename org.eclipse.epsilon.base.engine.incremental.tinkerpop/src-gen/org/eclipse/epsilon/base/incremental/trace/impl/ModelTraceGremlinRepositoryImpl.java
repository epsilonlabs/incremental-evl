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
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
/** protected region ModelTraceRepositoryImplImports on begin **/
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
/** protected region ModelTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ModelTraceGremlinRepositoryImpl implements IModelTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModelTraceGremlinRepositoryImpl.class);
 
    protected Graph extent;    
    
    public ModelTraceGremlinRepositoryImpl() {
    }

    
    @Override
    public boolean add(IModelTrace item) {
        logger.info("Adding {} to repository", item);
        ModelTraceGremlin impl = (ModelTraceGremlin)item;
        Vertex a = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(extent));
        return a.graph() != null;
    }

    @Override
    public boolean remove(IModelTrace item) {
        logger.info("Removing {} from repository", item);
        Vertex v = ((ModelTraceGremlin)item).delegate();
        v.remove();
        return v.graph() == null;
    }
  
    @Override
    public IModelTrace get(Object id) {
        
        logger.debug("Get ModelTrace with id:{}", id);
        ModelTraceGremlin result = null;
        GraphTraversalSource g = new GraphTraversalSource(extent);
        Vertex v = g.V(id).next();
        if (v != null) {
            result = new ModelTraceGremlin();
            result.delegate(v);
            result.graph(extent);
        }
        return result;
    }

    public void injectGraph(Module tinkerpopGuiceModule) {
        Injector injector = Guice.createInjector(tinkerpopGuiceModule);
        Graph g = injector.getInstance(Graph.class);
        this.extent = g;
    }
    
    
    /** protected region IModelTraceRepositry on begin **/
    @Override
	public IModelTrace getModelTraceByIdentity(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelElementTrace getModelElementTraceFor(String modelUri, String modelElementUri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelTypeTrace getTypeTraceFor(String modelUri, String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyTrace getPropertyTraceFor(String modelUri, String elementId, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

    /** protected region IModelTraceRepositry end **/

}