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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.Attachable;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.util.ActiveTraversal;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceGremlinRepositoryImpl;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.eclipse.epsilon.base.incremental.ModuleElementTraceExecution;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils.IncrementalFactoryIterator;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.ReexecutionCheckTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionContextTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionGuardTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionInvariantTrace;
import org.eclipse.epsilon.evl.incremental.ReexecutionMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.*;
/** protected region EvlModuleTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * A repository for handling elements in the domain of EvlModuleTrace.
 *
 * @author Horacio Hoyos Rodriguez
 */
@SuppressWarnings("unused")
public class EvlModuleTraceGremlinRepositoryImpl extends ModuleExecutionTraceGremlinRepositoryImpl<IEvlModuleTrace> implements IEvlModuleTraceRepository<IEvlModuleTrace> {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceGremlinRepositoryImpl.class);
 
    
    @Inject
    public EvlModuleTraceGremlinRepositoryImpl(
        GraphTraversalSource trvrslSrc,
        TraceFactory fctry) {
        super(trvrslSrc, fctry);
        
    }

    @Override
    public IEvlModuleTrace add(IEvlModuleTrace item) {
        logger.info("Adding {} to repository", item);
        assert item instanceof EvlModuleTraceGremlin;
        EvlModuleTraceGremlin impl = (EvlModuleTraceGremlin)item;
        Vertex attached = ((DetachedVertex)impl.delegate()).attach(Attachable.Method.getOrCreate(gts.getGraph()));
        return factory.createTraceElement(attached, gts);
    }

    @Override
    public IEvlModuleTrace remove(IEvlModuleTrace item) {
        logger.info("Removing {} from repository", item);
        Vertex v = ((EvlModuleTraceGremlin)item).delegate();
        v.remove();
        return factory.createTraceElement(null, gts);
    }
    
    @Override
    public void dispose() {    
        super.dispose();
    } 
  
    @Override
    public IEvlModuleTrace get(Object id) {
        logger.debug("Get EvlModuleTrace with id:{}", id);
        EvlModuleTraceGremlin result = null;
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
 
    
    /** protected region IEvlModuleTraceRepositry on begin **/
	@Override
	public Iterator<IEvlModuleTrace> getAllModuleTraces() {
		IncrementalFactoryIterator<IEvlModuleTrace> it;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			it = new IncrementalFactoryIterator<IEvlModuleTrace>(agts.V().hasLabel("EvlModuleTrace"), gts, factory);
        } catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return it;
	}
    
	@Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String uri) {
		EvlModuleTraceGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V().hasLabel("EvlModuleTrace").has("uri", uri);
			if (gt.hasNext()) {	
				result = factory.createTraceElement(gt.next(), gts);
			}		
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String uri) {
		return getEvlModuleTraceByIdentity(uri);
	}
	
	
	@Override
	public Set<ModuleElementTraceExecution> findAllInstancesExecutionTraces(
		String moduleUri,
		String modelUri,
		String typeName) {
		return findAllInstancesExecutionTraces(moduleUri, modelUri, typeName, false);
	}
	
	@Override
	public Set<ModuleElementTraceExecution> findAllInstancesExecutionTraces(
	    String moduleUri,
	    String modelUri,
	    String typeName,
	    boolean ofType) {
		Set<ModuleElementTraceExecution> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Path> find_gt = agts.V().hasLabel("EvlModuleTrace")
				.has("uri", moduleUri)
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
			while (find_gt.hasNext()) {
				Optional<ModuleElementTraceExecution> ret = makeRexecutionTrace(find_gt);
				ret.ifPresent(result::add);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	@Override
	public Set<ModuleElementTraceExecution> findIndirectExecutionTraces(
	    String moduleUri,
	    Object elementId,
	    Collection<String> allElementTypes) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		
		Set<ModuleElementTraceExecution> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Path> find_gt = agts.V().hasLabel("EvlModuleTrace")
					.has("uri", moduleUri)
					.out("access")
					.or(__.hasLabel("ElementAccess")
							.out("element")
							.hasId(elementId),
						__.hasLabel("PropertyAccess")
							.out("property")
							.out("elementTrace")
							.hasId(elementId),
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
						   .hasId(elementId)
					)	   	
					.path();
			while (find_gt.hasNext()) {
				Optional<ModuleElementTraceExecution> ret = makeRexecutionTrace(find_gt);
				ret.ifPresent(result::add);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	@Override
	public Set<ModuleElementTraceExecution> findModelElementExecutionTraces(
		String moduleUri,
		String elementUri,
		String modelUri,
		Set<String> allElementTypes) {
		Set<ModuleElementTraceExecution> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Path> gt = agts.V().hasLabel("EvlModuleTrace")
					.has("uri", moduleUri)
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
			while (gt.hasNext()) {
				Optional<ModuleElementTraceExecution> ret = makeRexecutionTrace(gt);
				ret.ifPresent(result::add);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	@Override
	public Set<ModuleElementTraceExecution> findPropertyAccessExecutionTraces(
		String moduleUri,
		IPropertyTrace propertyTrace) {
		Set<ModuleElementTraceExecution> result = new HashSet<>();
		if (propertyTrace != null) {
			try (ActiveTraversal agts = new ActiveTraversal(gts)) {
				GraphTraversal<Vertex, Path> find_gt = agts.V().hasLabel("EvlModuleTrace")
						.has("uri", moduleUri)
						.out("accesses")
						.hasLabel("PropertyAccess")
						.as("a")
						.out("property")
						.has(T.id, propertyTrace.getId())
						.select("a")
						.out("from")
						.as("me")
						.select("a")
						.out("in")
						.as("c")
						.select("a")
						.path();
				
				while (find_gt.hasNext()) {
					Optional<ModuleElementTraceExecution> ret = makeRexecutionTrace(find_gt);
					ret.ifPresent(result::add);
				}
			} catch (Exception e) {
	            throw new IllegalStateException("There was an error during graph traversal.", e);
	        }
		}
		return result;
	}
	
	@Override
	public Set<ModuleElementTraceExecution> findPropertyAccessExecutionTraces(IPropertyAccess pa) {
		Set<ModuleElementTraceExecution> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Path> find_gt = agts.V(pa.getId())
					.out("from")
					.as("me")
					.select("a")
					.out("in")
					.as("c")
					.select("a")
					.path();
			
			while (find_gt.hasNext()) {
				Optional<ModuleElementTraceExecution> ret = makeRexecutionTrace(find_gt);
				ret.ifPresent(result::add);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	@Override
	public IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName) {
		InvariantTraceGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt = agts.V(contextTrace.getId())
					.out("constraints")
					.has("name", invariantName);
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}


	@Override
	public ICheckResult findResultInCheck(ICheckTrace checkTrace, IExecutionContext currentContext) {
		CheckResultGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt =  agts.V(checkTrace.getId()).out("result").as("r").out("context").hasId(currentContext.getId()).select("r");
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}


	@Override
	public IGuardResult findResultInGuard(IGuardTrace guardTrace, IExecutionContext currentContext) {
		GuardResultGremlin result = null;
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> gt =  agts.V(guardTrace.getId()).out("result").as("r").out("context").hasId(currentContext.getId()).select("r");
			if (gt.hasNext()) {
				result = factory.createTraceElement(gt.next(), gts);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
		return result;
	}
	
	
	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> element_gt = agts.V().hasLabel("EvlModuleTrace")
					.has("uri", moduleUri)
					.out("models").has("uri", modellUri)
					.out("elements").has("uri", elementUri);
			if (element_gt.hasNext()) {
				Vertex elementVertex = element_gt.next();
				// Remove elementAccessTraces
				try (ActiveTraversal agts2 = new ActiveTraversal(gts);
					 ActiveTraversal agts3 = new ActiveTraversal(gts);
					 ActiveTraversal agts4 = new ActiveTraversal(gts);) {
					GraphTraversal<Vertex, Object> eAccess_gt = agts2.V().hasLabel("ElementAccess")
							.as("a")
							.out("element")
							.hasId(elementVertex.id())
							.select("a")
							.drop();
					GraphTraversal<Vertex, Object> pAccess_gt = agts3.V().hasLabel("PropertyAccess")
							.as("a")
							.out("elementTrace")
							.hasId(elementVertex.id())
							.select("a")
							.drop();
					// TODO Should we remove empty ExecutionContexts?
					GraphTraversal<Vertex, Object> context_gt = agts4.V().hasLabel("ExecutionContext")
							.as("c")
							.out("accesses")
							.count()
							.is(P.eq(0))
							.select("c")
							.drop();
				} catch (Exception e) {
		            throw new IllegalStateException("There was an error during graph traversal.", e);
		        }
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }	
	}

	@Override
	public Collection<IPropertyAccess> getPropertyAccessesForElement(
		String elementUri,
		String modelUri,
		String moduleUri) {
		Set<IPropertyAccess> result = new HashSet<>();
		try (ActiveTraversal agts = new ActiveTraversal(gts)) {
			GraphTraversal<Vertex, Vertex> find_gt = agts.V().hasLabel("EvlModuleTrace")
					.has("uri", moduleUri)
					.out("accesses")
					.hasLabel("PropertyAccess")
					.as("a")
					.out("property")
					.and(__.out("elementTrace").has("uri", elementUri),
						__.out("elementTrace").out("modelTrace").has("uri", modelUri))
					.select("a");
			
			while (find_gt.hasNext()) {
				IPropertyAccess pa = factory.createTraceElement(find_gt.next(), gts);
				result.add(pa);
			}
		} catch (Exception e) {
            throw new IllegalStateException("There was an error during graph traversal.", e);
        }
        return result;
	}

	
	private Optional<ModuleElementTraceExecution>  makeRexecutionTrace(GraphTraversal<Vertex, Path> find_gt) {
		Path p = find_gt.next();
		Vertex metVertex = p.get("me");
		Object met = factory.createTraceElement(metVertex, gts);
		Object exContext = factory.createTraceElement(p.get("c"), gts);
		ModuleElementTraceExecution rt = null;
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

    /** protected region IEvlModuleTraceRepositry end **/

}