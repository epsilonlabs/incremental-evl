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
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
/** protected region ModelTraceRepositoryImplImports on begin **/
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
/** protected region ModelTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ModelTraceGremlinRepositoryImpl implements IModelTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModelTraceGremlinRepositoryImpl.class);
 
    protected GraphTraversalSource gts; 
    
    public ModelTraceGremlinRepositoryImpl() {
    }

    
    @Override
    public boolean add(IModelTrace item) {
        logger.info("Adding {} to repository", item);
        ModelTraceGremlin impl = (ModelTraceGremlin)item;
        Vertex a = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(gts.getGraph()));
        impl.delegate(a);
        impl.graphTraversalSource(gts);
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
    public void dispose() {    
        try {
            gts.close();
        } catch (Exception e) {
            logger.warn("Error closing GraphTraversalSource",  e);
        }
    } 
  
    @Override
    public IModelTrace get(Object id) {
        logger.debug("Get ModelTrace with id:{}", id);
        ModelTraceGremlin result = null;
        GraphTraversalSource g = gts.clone();
        Vertex v = g.V(id).next();
        if (v != null) {
            result = new ModelTraceGremlin();
            result.delegate(v);
            result.graphTraversalSource(gts);
        }
        try {
            g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
        return result;
    }
 
    
    /** protected region IModelTraceRepositry on begin **/
    public void setGraphTraversalSource(final GraphTraversalSource gts) {
    	this.gts = gts;
    }
   
    
    @Override
	public IModelTrace getModelTraceByIdentity(String uri) {
    	GraphTraversalSource g = gts.clone();
    	ModelTraceGremlin result = null;
		GraphTraversal<Vertex, Vertex> gt = g.V().hasLabel("ModelTrace").has("uri", uri);
		if (gt.hasNext()) {
			result = new ModelTraceGremlin();
	        result.delegate(gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
            g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		
		return result;
	}


	@Override
	public IModelElementTrace getModelElementTraceFor(String modelUri, String modelElementUri) {
		GraphTraversalSource g = gts.clone();
		ModelElementTraceGremlin result = null;
		GraphTraversal<Vertex, Vertex> gt = g.V().hasLabel("ModelTrace").has("uri", modelUri)
				.out("elements").has("uri", modelElementUri);
		if (gt.hasNext()) {
			result = new ModelElementTraceGremlin();
	        result.delegate(gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
            g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}


	@Override
	public IModelTypeTrace getTypeTraceFor(String modelUri, String typeName) {
		GraphTraversalSource g = gts.clone();
		ModelTypeTraceGremlin result = null;
		GraphTraversal<Vertex, Vertex> gt = g.V().hasLabel("ModelTrace").has("uri", modelUri)
				.out("types").has("name", typeName);
		if (gt.hasNext()) {
			result = new ModelTypeTraceGremlin();
	        result.delegate(gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
            g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}


	@Override
	public IPropertyTrace getPropertyTraceFor(String modelUri, String modelElementUri, String propertyName) {
		GraphTraversalSource g = gts.clone();
		PropertyTraceGremlin result = null;
		GraphTraversal<Vertex, Vertex> gt = g.V().hasLabel("ModelTrace").has("uri", modelUri)
				.out("elements").has("uri", modelElementUri)
				.out("properties").has("name", propertyName);
		if (gt.hasNext()) {
			result = new PropertyTraceGremlin();
	        result.delegate(gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
            g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}

    /** protected region IModelTraceRepositry end **/

}