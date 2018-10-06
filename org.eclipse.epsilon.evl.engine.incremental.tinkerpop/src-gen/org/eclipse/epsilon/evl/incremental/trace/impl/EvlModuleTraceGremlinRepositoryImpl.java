 /*******************************************************************************
 * This file was automatically generated on: 2018-09-13.
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
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceGremlinRepositoryImpl;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;

import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.IReexecutionTrace;
import org.eclipse.epsilon.evl.ReexecutionCheckTrace;
import org.eclipse.epsilon.evl.ReexecutionContextTrace;
import org.eclipse.epsilon.evl.ReexecutionGuardTrace;
import org.eclipse.epsilon.evl.ReexecutionInvariantTrace;
import org.eclipse.epsilon.evl.ReexecutionMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils.IncrementalFactoryIterator;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
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
	public Set<IEvlModuleTrace> getAllModuleTraces() {
		GraphTraversalSource g = gts.clone();
		IncrementalFactoryIterator<IEvlModuleTrace,Vertex> it = 
				new IncrementalFactoryIterator<IEvlModuleTrace,Vertex>(g.V().hasLabel("EvlModuleTrace"), gts, EvlTraceFactory.getFactory());
		Set<IEvlModuleTrace> result = IncrementalUtils.asSet(it);
		try {
			g.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}
    
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

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri) {
		return getEvlModuleTraceByIdentity(uri);
	}
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		IModuleExecutionTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		return getModelTraceForModule(modelUri, moduleTrace);
	}
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, IModuleExecutionTrace moduleTrace) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> module_gt = g.V(moduleTrace.getId());
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
	
//	@Override
//	public Set<IModuleElementTrace> getAllExecutionTraces() {
//		GraphTraversalSource g = gts.clone();
//		IncrementalFactoryIterator<IModuleElementTrace,Vertex> it = 
//				new IncrementalFactoryIterator<IModuleElementTrace,Vertex>(g.V().hasLabel("EvlModuleTrace").out("moduleElements"), gts, EvlTraceFactory.getFactory());
//		Set<IModuleElementTrace> result = IncrementalUtils.asSet(it);
//		try {
//			g.close();
//        } catch (Exception e) {
//            logger.error("Error closing GraphTraversalSource", e);
//        }
//		return result;
//	}
	
	@Override
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName) {
		return findAllInstancesExecutionTraces(moduleUri, typeName, false);
	}
	
	@Override
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName, boolean ofType) {
		/*
		 * Gremlin Console
		 * g.V().hasLabel("AllInstancesAccess")
		 * 		.and(
		 * 			__.out("type").has("name", "Row"),
		 * 			__.has("ofKind", true))
		 * 		.out("from").as("me")
		 * 		.choose(
		 * 			out("contextModuleElement").count().is(1),
		 * 			out("contextModuleElement").out("executionContext"),
		 * 			out("executionContext")).as("c")
		 * 		.path()
		 */
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Path> find_gt = g.V().hasLabel("AllInstancesAccess")
				.and(__.out("type").has("name", "Row"),
					__.has("ofKind", true)).out("from").as("me").choose(
							__.out("contextModuleElement").count().is(1),
						 	__.out("contextModuleElement").out("executionContext"),
						 	__.out("executionContext")).as("c").path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTraces(find_gt);
			ret.ifPresent(result::add);
		}
		try {
			find_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}
	
	@Override
	public Set<IReexecutionTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		// allElementTypes = g.V(elementId).union(out("type").values("name"), out("kind").values("name"))
		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
		
		GraphTraversal<Vertex, Path> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("moduleElements")
				.as("me")
				.out("executionContext")
				.as("c")
				.and(__.out("contextVariables")
						.as("cv")
						.has("name", "self")
						.select("cv")
						.out("value")
						.hasId(modelElementTrace.getId()),
					__.out("access")
						.or(__.hasLabel("ElementAccess")
								.out("element")
								.hasId(modelElementTrace.getId()),
							__.hasLabel("PropertyAccess")
								.out("elementTrace")
								.hasId(modelElementTrace.getId()),
							__.hasLabel("AllInstancesAccess")
								.out("type")
								.has("name", P.within(allElementTypes))
								)
						)
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTraces(find_gt);
			ret.ifPresent(result::add);
		}
		try {
			find_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}
	
	@Override
	public Set<IReexecutionTrace> findModelElementExecutionTraces(String moduleUri, String elementUri,
			String modelUri) {
		
		GraphTraversal<Vertex, Vertex> module_gt = getEvlModuleTraceByIdentity_gt(moduleUri);
		GraphTraversal<Vertex, Vertex> modelTrace_gt = getModelTraceForModule_gt(modelUri, module_gt);
		GraphTraversal<Vertex, Object> elementTypes_gt = getModelElementTraceFromModel_gt(elementUri, modelTrace_gt)
				.out("kind")
				.values("name");
		Set<String> allElementTypes = new HashSet<>();
		elementTypes_gt.forEachRemaining(n -> allElementTypes.add((String) n));
		try {
			elementTypes_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		GraphTraversal<Vertex, Path> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("moduleElements")
				.as("me")
				.out("context")
				.as("c")
				.out("access")
				.or(__.hasLabel("ElementAccess")
						.out("element")
						.as("element")
						.has("uri", elementUri)
						.select("element")
						.in("elements")
						.has("uri", modelUri), 	// FIXME For this is better to get the Vertex separately and match ids
					__.hasLabel("PropertyAccess")
						.out("elementTrace")
						.has("uri", elementUri),
					__.hasLabel("AllInstancesAccess")
						.out("type")
						.has("name", P.within(allElementTypes))
						)
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTraces(find_gt);
			ret.ifPresent(result::add);
		}
		try {
			find_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}
	
	@Override
	public Set<IReexecutionTrace> findPropertyAccessExecutionTraces(
		String moduleUri,
		String modelUri,
		String elementUri,
		String propertyName) {
		
		GraphTraversal<Vertex, Path> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("moduleElements")
				.as("me")
				.out("context")
				.as("c")
				.out("access")
				.hasLabel("PropertyAccess")
				.as("a")
				.out("elementTrace")
				.has("uri", elementUri)
				.select("a")
				.has("name", propertyName)
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTraces(find_gt);
			ret.ifPresent(result::add);
		}
		try {
			find_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
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


	@Override
	public ICheckResult findResultInCheck(ICheckTrace checkTrace, IExecutionContext currentContext) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> gt =  g.V(checkTrace.getId()).out("result").as("r").out("context").hasId(currentContext.getId()).select("r");
		CheckResultGremlin result = null;
		if (gt.hasNext()) {
			result = new CheckResultGremlin();
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


	@Override
	public IGuardResult findResultInGuard(IGuardTrace guardTrace, IExecutionContext currentContext) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> gt =  g.V(guardTrace.getId()).out("result").as("r").out("context").hasId(currentContext.getId()).select("r");
		GuardResultGremlin result = null;
		if (gt.hasNext()) {
			result = new GuardResultGremlin();
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
	
	
	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {
		
		GraphTraversal<Vertex, Vertex> element_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("models").has("uri", modellUri)
				.out("elements").has("uri", elementUri);
		if (element_gt.hasNext()) {
			Vertex elementVertex = element_gt.next();
			// Remove elementAccessTraces
			GraphTraversal<Vertex, Object> eAccess_gt = gts.clone().V().hasLabel("ElementAccess")
					.as("a")
					.out("element")
					.hasId(elementVertex.id())
					.select("a")
					.drop();
			GraphTraversal<Vertex, Object> pAccess_gt = gts.clone().V().hasLabel("PropertyAccess")
					.as("a")
					.out("elementTrace")
					.hasId(elementVertex.id())
					.select("a")
					.drop();
			// TODO Should we remove empty ExecutionContexts?
			GraphTraversal<Vertex, Object> context_gt = gts.clone().V().hasLabel("ExecutionContext")
					.as("c")
					.out("accesses")
					.count()
					.is(P.eq(0))
					.select("c")
					.drop();
			try {
				eAccess_gt.close();
				pAccess_gt.close();
				context_gt.close();
	        } catch (Exception e) {
	            logger.error("Error closing GraphTraversalSource", e);
	        }		
		}
		try {
			element_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }		
	}


//	@Override
//	public Set<IModuleElementTrace> findAllExecutionTraces(String moduleUri, String elementUri, String modelUri) {
//		// TODO Implement IEvlModuleTraceRepository.findAllExecutionTraces
//		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findAllExecutionTraces invoked.");
//	}


	
	
	private Optional<IReexecutionTrace>  makeRexecutionTraces(GraphTraversal<Vertex, Path> find_gt) {
		Path p = find_gt.next();
		Vertex metVertex = p.get("me");
		Object met = EvlTraceFactory.getFactory().createTraceElement(metVertex, gts);
		Object exContext = EvlTraceFactory.getFactory().createTraceElement(p.get("c"), gts);
		IReexecutionTrace rt = null;
		switch(metVertex.label()) {
		case "ContextTrace":
			rt = new ReexecutionContextTrace((IContextTrace) met, (IExecutionContext) exContext);
		case "IInvariantTrace":
			rt = new ReexecutionInvariantTrace((IInvariantTrace) met, (IExecutionContext) exContext);
		case "IGuardTrace":
			rt = new ReexecutionGuardTrace((IGuardTrace) met, (IExecutionContext) exContext);
		case "ICheckTrace":
			rt = new ReexecutionCheckTrace((ICheckTrace) met, (IExecutionContext) exContext);
		case "IMessageTrace":
			rt = new ReexecutionMessageTrace((IMessageTrace) met, (IExecutionContext) exContext);
		}
		return Optional.ofNullable(rt);
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


    /** protected region IEvlModuleTraceRepositry end **/

}