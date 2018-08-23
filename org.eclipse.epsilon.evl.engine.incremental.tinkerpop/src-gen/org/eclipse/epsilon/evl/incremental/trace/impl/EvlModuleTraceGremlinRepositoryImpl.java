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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceGremlinRepositoryImpl;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
/** protected region EvlModuleTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class EvlModuleTraceGremlinRepositoryImpl extends ModuleExecutionTraceGremlinRepositoryImpl<IEvlModuleTrace> implements IEvlModuleTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceGremlinRepositoryImpl.class);
 
    
    public EvlModuleTraceGremlinRepositoryImpl() {
    }

    
    @Override
    public boolean add(IEvlModuleTrace item) {
        logger.info("Adding {} to repository", item);
        EvlModuleTraceGremlin impl = (EvlModuleTraceGremlin)item;
        Vertex a = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(extent));
        return a.graph() != null;
    }

    @Override
    public boolean remove(IEvlModuleTrace item) {
        logger.info("Removing {} from repository", item);
        Vertex v = ((EvlModuleTraceGremlin)item).delegate();
        v.remove();
        return v.graph() == null;
    }
  
    @Override
    public IEvlModuleTrace get(Object id) {
        
        logger.debug("Get EvlModuleTrace with id:{}", id);
        EvlModuleTraceGremlin result = null;
        GraphTraversalSource g = new GraphTraversalSource(extent);
        Vertex v = g.V(id).next();
        if (v != null) {
            result = new EvlModuleTraceGremlin();
            result.delegate(v);
            result.graph(extent);
        }
        return result;
    }

    
    
    /** protected region IEvlModuleTraceRepositry on begin **/
	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String uri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri,
			String elementId, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> getAllExecutionTraces() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IEvlModuleTrace> getAllModuleTraces() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<IModuleElementTrace> findAllExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace) {
		// TODO Auto-generated method stub
		return null;
	}


    /** protected region IEvlModuleTraceRepositry end **/

}