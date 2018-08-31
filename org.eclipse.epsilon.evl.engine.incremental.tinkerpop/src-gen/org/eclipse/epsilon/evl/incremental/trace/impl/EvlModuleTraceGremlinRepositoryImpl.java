 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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

import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceGremlinRepositoryImpl;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.HashSet;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;

import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.util.TraceFactory;
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
        Vertex a = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(gts.getGraph()));
        impl.delegate(a);
        impl.graphTraversalSource(gts);
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
    public void dispose() {    
        super.dispose();
    } 
  
    @Override
    public IEvlModuleTrace get(Object id) {
        logger.debug("Get EvlModuleTrace with id:{}", id);
        EvlModuleTraceGremlin result = null;
        GraphTraversalSource g = gts.clone();
        Vertex v = g.V(id).next();
        if (v != null) {
            result = new EvlModuleTraceGremlin();
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
 
    
    /** protected region IEvlModuleTraceRepositry on begin **/
	@Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String uri) {
		GraphTraversalSource g = gts.clone();
		EvlModuleTraceGremlin result = null;
		GraphTraversal<Vertex, Vertex> gt = g.V().hasLabel("EvlModuleTrace").has("uri", uri);
		if (gt.hasNext()) {
			result = new EvlModuleTraceGremlin();
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
	
	
	/**** PRIVATE METHODS TO CONCATENATE GRAPH TRAVERSALS ******/
	
	private GraphTraversal<Vertex, Vertex> getEvlModuleTraceByIdentity_gt(String moduleUri) {
		GraphTraversalSource g = gts.clone();
		return g.V().hasLabel("EvlModuleTrace").has("uri", moduleUri);
	}
	
	private GraphTraversal<Vertex, Vertex> getModelTraceForModule_gt(String modelUri, GraphTraversal<Vertex, Vertex> module_gt) {
		GraphTraversalSource g = gts.clone();
		return module_gt.out("models").has("uri", modelUri);
	}
	
	private GraphTraversal<Vertex, Vertex> getModelElementTraceFromModel_gt(String elementUri, GraphTraversal<Vertex, Vertex> model_gt) {
		return model_gt.out("elements").has("uri", elementUri);
	}
	
	private GraphTraversal<Vertex, Vertex> getPropertyTraceFromElement_gt(String name, GraphTraversal<Vertex, Vertex> element_gt) {
		return element_gt.out("properties").has("name", name);
	}
	
	/*****************************/

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri) {
		return getEvlModuleTraceByIdentity(uri);
	}
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		GraphTraversal<Vertex, Vertex> module_gt = getEvlModuleTraceByIdentity_gt(moduleUri);
		GraphTraversal<Vertex, Vertex> model_gt = getModelTraceForModule_gt(modelUri, module_gt);
		ModelTraceGremlin result = null;
		if (model_gt.hasNext()) {
			result = new ModelTraceGremlin();
	        result.delegate(model_gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
            model_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}
	

	@Override
	public IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> modelTrace_gt = g.V(modelTrace.getId());
		GraphTraversal<Vertex, Vertex> element_gt = getModelElementTraceFromModel_gt(elementUri, modelTrace_gt);
		ModelElementTraceGremlin result = null;
		if (element_gt.hasNext()) {
			result = new ModelElementTraceGremlin();
	        result.delegate(element_gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
			element_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
		
	}

	
	@Override
	public Set<IModuleElementTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri,
			String elementUri, String propertyName) {
		GraphTraversal<Vertex, Vertex> module_gt = getEvlModuleTraceByIdentity_gt(moduleUri);
		GraphTraversal<Vertex, Vertex> model_gt = getModelTraceForModule_gt(modelUri, module_gt);
		GraphTraversal<Vertex, Vertex> element_gt = getModelElementTraceFromModel_gt(elementUri, model_gt);
		GraphTraversal<Vertex, Vertex> properties_gt = getPropertyTraceFromElement_gt(propertyName, element_gt);
		
		Set<IModuleElementTrace> result = new HashSet<>();
		if (properties_gt.hasNext()) {
			GraphTraversal<Vertex, Vertex> mt_gt = properties_gt.in("property").out("executionTrace");
			while(mt_gt.hasNext()) {
				Object mt = TraceFactory.createModuleElementTrace(mt_gt.next(), gts);
				result.add((IModuleElementTrace) mt);
			}
		}
		try {
			properties_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
		
	}


	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.removeTraceInformation
		throw new RuntimeException("Unimplemented Method IModuleExecutionTraceRepository<IEvlModuleTrace>.removeTraceInformation invoked.");
	}

	@Override
	public Set<IModuleElementTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName) {
		// TODO Implement IEvlModuleTraceRepository.findAllInstancesExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findAllInstancesExecutionTraces invoked.");
	}


	@Override
	public Set<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace) {
		// TODO Implement IEvlModuleTraceRepository.findSatisfiesExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findSatisfiesExecutionTraces invoked.");
	}


	@Override
	public Set<IModuleElementTrace> getAllExecutionTraces() {
		// TODO Implement IEvlModuleTraceRepository.getAllExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.getAllExecutionTraces invoked.");
	}


	@Override
	public Set<IModuleElementTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		// TODO Implement IEvlModuleTraceRepository.findIndirectExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findIndirectExecutionTraces invoked.");
	}


	@Override
	public Set<IEvlModuleTrace> getAllModuleTraces() {
		// TODO Implement IEvlModuleTraceRepository.getAllModuleTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.getAllModuleTraces invoked.");
	}


	@Override
	public Set<IModuleElementTrace> findAllExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		// TODO Implement IEvlModuleTraceRepository.findAllExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findAllExecutionTraces invoked.");
	}


	@Override
	public IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> gt = g.V(contextTrace.getId()).out("constraints").has("name", invariantName);
		InvariantTraceGremlin result = null;
		if (gt.hasNext()) {
			result = new InvariantTraceGremlin();
	        result.delegate(gt.next());
	        result.graphTraversalSource(gts);
		}
		try {
			gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}




    /** protected region IEvlModuleTraceRepositry end **/

}