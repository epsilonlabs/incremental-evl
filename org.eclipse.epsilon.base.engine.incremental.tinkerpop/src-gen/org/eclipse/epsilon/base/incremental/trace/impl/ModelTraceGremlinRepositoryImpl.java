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
package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
/** protected region ModelTraceRepositoryImplImports on begin **/
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
/** protected region ModelTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A repository for handling elements in the domain of ModelTrace.
 *
 * @author Horacio Hoyos Rodriguez
 */
@SuppressWarnings("unused")
public class ModelTraceGremlinRepositoryImpl implements IModelTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModelTraceGremlinRepositoryImpl.class);
 
    protected GraphTraversalSource gts; 
    protected final TraceFactory factory;
    
    public ModelTraceGremlinRepositoryImpl(
        GraphTraversalSource trvrslSrc, TraceFactory fctry) {
        gts = trvrslSrc;
        factory = fctry;
    }

    
    @Override
    public boolean add(IModelTrace item) {
        logger.info("Adding {} to repository", item);
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> existing = agts.V(item.getId());
            Vertex newVertex;
            if (existing.hasNext()) {
                newVertex = existing.next();
            }
            else {
                List<Object> keyValues = new ArrayList<>();
                keyValues.add("id");
                keyValues.add(item.getId());
                keyValues.add("uri");
                keyValues.add(item.getUri());
                newVertex = agts.addV("ModelTraceGremlin").property(T.id, item.getId(), keyValues.toArray()).next();
            }
            item = (IModelTrace) factory.createTraceElement(newVertex, gts);
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        } 
        return true;
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
    } 
  
    @Override
    public IModelTrace get(Object id) {
        logger.debug("Get ModelTrace with id:{}", id);
        ModelTraceGremlin result = null;
        try (ActiveTraversal agts = new ActiveTraversal(gts)) {
            GraphTraversal<Vertex, Vertex> gt = agts.V(id);
	        if (gt.hasNext()) {
    	        result = factory.createTraceElement(gt.next(), gts);
	        }
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
    }
 
    
    /** protected region IModelTraceRepositry on begin **/
    @Override
	public IModelTrace getModelTraceByIdentity(String uri) {
    	ModelTraceGremlin result = null;
    	try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("ModelTrace").has("uri", uri);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}


	@Override
	public IModelElementTrace getModelElementTraceFor(String modelUri, String modelElementUri) {
		ModelElementTraceGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("ModelTrace").has("uri", modelUri)
					.out("elements").has("uri", modelElementUri);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}


	@Override
	public IModelTypeTrace getTypeTraceFor(String modelUri, String typeName) {
		ModelTypeTraceGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("ModelTrace")
					.has("uri", modelUri)
					.out("types").has("name", typeName);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}


	@Override
	public IPropertyTrace getPropertyTraceFor(String modelUri, String modelElementUri, String propertyName) {
		PropertyTraceGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("ModelTrace")
					.has("uri", modelUri)
					.out("elements").has("uri", modelElementUri)
					.out("properties").has("name", propertyName);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}

    /** protected region IModelTraceRepositry end **/

}