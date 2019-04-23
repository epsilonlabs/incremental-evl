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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.Collection;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTraceGremlin;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlin;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyAccessGremlin;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils.IncrementalFactoryIterator;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.IReexecutionTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionCheckTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionContextTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionGuardTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionInvariantTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;
/** protected region EvlModuleTraceRepositoryImplImports end **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvlModuleTraceGremlinRepositoryImpl implements IEvlModuleTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceGremlinRepositoryImpl.class);
    private GraphTraversalSource gts; 
    
    public void setGraphTraversalSource(GraphTraversalSource gts) {
		this.gts = gts;
	}

	@Override
    public boolean add(IModuleExecutionTrace item) {
        logger.info("Adding {} to repository", item);
        assert item instanceof EvlModuleTraceGremlin;
        EvlModuleTraceGremlin impl = (EvlModuleTraceGremlin)item;
        Vertex a = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(gts.getGraph()));
        impl.delegate(a);
        impl.graphTraversalSource(gts);
        return a.graph() != null;
    }

    @Override
    public boolean remove(IModuleExecutionTrace item) {
        logger.info("Removing {} from repository", item);
        assert item instanceof EvlModuleTraceGremlin;
        Vertex v = ((EvlModuleTraceGremlin)item).delegate();
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
		GraphTraversal<Vertex, Vertex> module_gt = getEvlModuleTraceByIdentity_gt(moduleUri);
		GraphTraversal<Vertex, Vertex> model_gt = getModelTraceForModule_gt(modelUri, module_gt);
		return wrapGraphModel(model_gt);
	}
	
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, IModuleExecutionTrace moduleTrace) {
		GraphTraversalSource g = gts.clone();
		GraphTraversal<Vertex, Vertex> module_gt = g.V(moduleTrace.getId());
		GraphTraversal<Vertex, Vertex> model_gt = getModelTraceForModule_gt(modelUri, module_gt);
		return wrapGraphModel(model_gt);
	}
	
	private IModelTrace wrapGraphModel(GraphTraversal<Vertex, Vertex> model_gt) {
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
	public Collection<IModelElementTrace> getModelElementTraces(String moduleUri, String modelUri, Set<String> filteredIds) {
		GraphTraversal<Vertex, Vertex> module_gt = getEvlModuleTraceByIdentity_gt(moduleUri);
		GraphTraversal<Vertex, Vertex> model_gt = getModelTraceForModule_gt(modelUri, module_gt);
		GraphTraversal<Vertex, Vertex> element_gt;
		if (filteredIds.isEmpty()) {
			element_gt = model_gt.out("elements");
		}
		else {
			element_gt = model_gt.out("elements").has("uri", P.without(filteredIds));
		}
		Set<IModelElementTrace> result = new HashSet<>();
		while(element_gt.hasNext()) {
			ModelElementTraceGremlin met = new ModelElementTraceGremlin();
	        met.delegate(element_gt.next());
	        met.graphTraversalSource(gts);
	        result.add(met);
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
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(
		String moduleUri,
		String modelUri,
		String typeName) {
		return findAllInstancesExecutionTraces(moduleUri, modelUri, typeName, false);
	}
	
	@Override
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(
	    String moduleUri,
	    String modelUri,
	    String typeName,
	    boolean ofType) {
		GraphTraversal<Vertex, Path> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("accesses") 
				.hasLabel("AllInstancesAccess")
				.and(__.out("type").has("name", typeName),
					 __.out("type").out("modelTrace").has("uri", modelUri),
					 __.has("ofKind", !ofType))
				.as("a")
				.out("from")
				.as("me")
				.select("a")
				.out("in")
				.as("c")
				.select("a")
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTrace(find_gt);
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
	public Set<IReexecutionTrace> findIndirectExecutionTraces(
	    String moduleUri,
	    String elementUri,
	    String modelUri) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		// allElementTypes = g.V(elementId).union(out("type").values("name"), out("kind").values("name"))
		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
		
		GraphTraversal<Vertex, Path> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("access")
				.or(__.hasLabel("ElementAccess")
						.out("element")
						.hasId(modelElementTrace.getId()),
					__.hasLabel("PropertyAccess")
						.out("property")
						.out("elementTrace")
						.hasId(modelElementTrace.getId()),
					__.hasLabel("AllInstancesAccess")
						.out("type")
						.has("name", P.within(allElementTypes))
				)
				.as("a")
				.out("from")
				.as("me")
				.select("a")
				.out("in")
				.as("c")
				.out("contextVariables")
				.as("cv")
				.and(__	.has("name", "self"),
					 __.out("value")
					   .hasId(modelElementTrace.getId())
				)	   	
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTrace(find_gt);
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
				.out("access")
				.as("a")
				.or(__.hasLabel("ElementAccess")
						.out("element")
						.and(__.has("uri", elementUri),
						     __.out("modelTrace")
						       .has("uri", modelUri)	// FIXME For this is better to get the Vertex separately and match ids?
						), 	
					__.hasLabel("PropertyAccess")
					  .out("elementTrace")
					  .has("uri", elementUri),
					__.hasLabel("AllInstancesAccess")
					  .out("type")
					  .has("name", P.within(allElementTypes))
				)
				.out("from")
				.as("me")
				.select("a")
				.out("in")
				.as("c")
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTrace(find_gt);
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
				.out("accesses")
				.hasLabel("PropertyAccess")
				.as("a")
				.out("property")
				.and(__.has("name", propertyName),
					__.out("elementTrace").has("uri", elementUri),
					__.out("elementTrace").out("modelTrace").has("uri", modelUri))
				.select("a")
				.out("from")
				.as("me")
				.select("a")
				.out("in")
				.as("c")
				.select("a")
				.path();
		Set<IReexecutionTrace> result = new HashSet<>();
		while (find_gt.hasNext()) {
			Optional<IReexecutionTrace> ret = makeRexecutionTrace(find_gt);
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

	/**
	 * @Override
	public Collection<IPropertyAccess> getPropertyAccessesForElement(
		String elementUri,
		IModelTrace modelTrace,
		IModuleExecutionTrace moduleTrace) {
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		return IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
				.collect(Collectors.toList());
	}
	 */

	@Override
	public Collection<IPropertyAccess> getPropertyAccessesForElement(
		String elementUri,
		String modelUri,
		String moduleUri) {
		GraphTraversal<Vertex, Vertex> find_gt = getEvlModuleTraceByIdentity_gt(moduleUri)
				.out("accesses")
				.hasLabel("PropertyAccess")
				.as("a")
				.out("property")
				.and(__.out("elementTrace").has("uri", elementUri),
					__.out("elementTrace").out("modelTrace").has("uri", modelUri))
				.select("a");
		Set<IPropertyAccess> result = new HashSet<>();
		while (find_gt.hasNext()) {
			PropertyAccessGremlin pa = new PropertyAccessGremlin();
			pa.delegate(find_gt.next());
			pa.graphTraversalSource(gts);
		}
		try {
			find_gt.close();
        } catch (Exception e) {
            logger.error("Error closing GraphTraversalSource", e);
        }
		return result;
	}

	
	
	private Optional<IReexecutionTrace>  makeRexecutionTrace(GraphTraversal<Vertex, Path> find_gt) {
		Path p = find_gt.next();
		Vertex metVertex = p.get("me");
		Object met = EvlTraceFactory.getFactory().createTraceElement(metVertex, gts);
		Object exContext = EvlTraceFactory.getFactory().createTraceElement(p.get("c"), gts);
		IReexecutionTrace rt = null;
		switch(metVertex.label()) {
		case "ContextTrace":
			rt = new ReexecutionContextTrace((IContextTrace) met, (IExecutionContext) exContext);
			break;
		case "InvariantTrace":
			rt = new ReexecutionInvariantTrace((IInvariantTrace) met, (IExecutionContext) exContext);
			break;
		case "GuardTrace":
			rt = new ReexecutionGuardTrace((IGuardTrace) met, (IExecutionContext) exContext);
			break;
		case "CheckTrace":
			rt = new ReexecutionCheckTrace((ICheckTrace) met, (IExecutionContext) exContext);
			break;
		case "MessageTrace":
			rt = new ReexecutionMessageTrace((IMessageTrace) met, (IExecutionContext) exContext);
			break;
		}
		return Optional.ofNullable(rt);
	}

	/**** PRIVATE METHODS TO CONCATENATE GRAPH TRAVERSALS ******/
	
	private GraphTraversal<Vertex, Vertex> getEvlModuleTraceByIdentity_gt(String moduleUri) {
		GraphTraversalSource g = gts.clone();
		return g.V().hasLabel("EvlModuleTrace").has("uri", moduleUri);
	}
	
	private GraphTraversal<Vertex, Vertex> getModelTraceForModule_gt(String modelUri, GraphTraversal<Vertex, Vertex> module_gt) {
		return module_gt.out("models").out("modelTrace").has("uri", modelUri);
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