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
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
/** protected region ModelTraceRepositoryImplImports on begin **/
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
/** protected region ModelTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

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
    
    @Inject
    public ModelTraceGremlinRepositoryImpl(
        GraphTraversalSource trvrslSrc,
        TraceFactory fctry) {
        gts = trvrslSrc;
        factory = fctry;
        
    }

    @Override
    public IModelTrace add(IModelTrace item) {
        logger.info("Adding {} to repository", item);
        assert item instanceof ModelTraceGremlin;
        ModelTraceGremlin impl = (ModelTraceGremlin)item;
        Vertex attached = gts.getGraph().addVertex(impl.delegate().label());
        Iterator<VertexProperty<Object>> it = impl.delegate().properties();
        while (it.hasNext()) {
        	VertexProperty<Object> vp = it.next();
        	attached.property(vp.key(), vp.value());
        }
        
        return factory.createTraceElement(attached, gts);
    }

    @Override
    public IModelTrace remove(IModelTrace item) {
        logger.info("Removing {} from repository", item);
        Vertex v = ((ModelTraceGremlin)item).delegate();
        v.remove();
        return factory.createTraceElement(null, gts);
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
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		IModelTrace result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("EvlModuleTrace")
				.has("uri", moduleUri)
				.out("models")
				.out("modelTrace").has("uri", modelUri);
			if (gt.hasNext()) {
				result = new ModelTraceGremlin(gt.next(), gts, factory);
			}
		}
		catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, IModuleExecutionTrace moduleTrace) {
		IModelTrace result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V(moduleTrace.getId())
					.out("models")
					.out("modelTrace").has("uri", modelUri);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		}
		catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	
	@Override
	public IModelElementTrace getModelElementTrace(String elementUri, IModelTrace modelTrace) {
		IModelElementTrace result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V(modelTrace.getId())
					.out("elements")
					.has("uri", elementUri);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		}
		catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
		
	}
	
	@Override
	public Collection<IModelElementTrace> getModelElementTraces(
		String moduleUri,
		String modelUri,
		Set<String> filteredIds) {
		Set<IModelElementTrace> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("EvlModuleTrace")
					.has("uri", moduleUri)
					.out("models")
					.out("modelTrace").has("uri", modelUri);
			GraphTraversal<Vertex, Vertex> element_gt;
			if (filteredIds.isEmpty()) {
				element_gt = gt.out("elements");
			}
			else {
				element_gt = gt.out("elements").has("uri", P.without(filteredIds));
			}
			while(element_gt.hasNext()) {
				ModelElementTraceGremlin met = factory.createTraceElement(element_gt.next(), gts);
		        result.add(met);
			}
		}
		catch (Exception e) {
			throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}

    /** protected region IModelTraceRepositry end **/

}