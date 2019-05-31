 /*******************************************************************************
 * This file was automatically generated on: 2019-05-31.
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

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceRepositoryImpl;
/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.function.Function;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.trace.*;
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

@SuppressWarnings("unused")
public class EvlModuleTraceRepositoryImpl extends ModuleExecutionTraceRepositoryImpl<IEvlModuleTrace> implements IEvlModuleTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceRepositoryImpl.class);
 
    
    public EvlModuleTraceRepositoryImpl() {
        super();
    }
    
    @Override
    public IEvlModuleTrace  add(IEvlModuleTrace item) {
        logger.info("Adding {} to repository", item);
        extent.add(item);
        // FIXME Perhaps throw exception if not added?
        return item;
    }

    @Override
    public IEvlModuleTrace  remove(IEvlModuleTrace item) {
        logger.info("Removing {} from repository", item);
        extent.remove(item);
        // FIXME Perhaps throw exception if not removed?
        return item;
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }
    
    
    @Override
    public IEvlModuleTrace get(Object id) {
        
        logger.debug("Get EvlModuleTrace with id:{}", id);
        IEvlModuleTrace  result = null;
        try {
            result = (IEvlModuleTrace)extent.stream()
                    .filter(item -> item.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException  e) {
            // No info about the ModelTrace
        }
        return result;
    }
    
    /** protected region IEvlModuleTraceRepositry on begin **/
	@Override
	public Iterator<IEvlModuleTrace> getAllModuleTraces() {
		return extent.iterator();
	}

    @Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String source) {
		return (IEvlModuleTrace) extent.stream().filter(mt -> mt.getUri() == source).findFirst().orElse(null);
	}

    @Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source) {
		return getEvlModuleTraceByIdentity(source);
	}
    
//	@Override
//	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
//		IModuleExecutionTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
//		return getModelTraceForModule(modelUri, moduleTrace);
//	}
//    
//	@Override
//	public IModelTrace getModelTraceForModule(String modelUri, IModuleExecutionTrace moduleTrace) {
//		IModelTrace modelTrace = null;
//		Iterator<IModelAccess> it = moduleTrace.models().get();
//		while (it.hasNext()) {
//			IModelAccess next = it.next();
//			IModelTrace mt = next.modelTrace().get();
//			if (mt.getUri().equals(modelUri)) {
//				modelTrace = mt;
//				break;
//			}
//		}
//		return modelTrace;
//	}

//	@Override
//	public IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace) {
//		IModelElementTrace elementTrace = null;
//		Iterator<IModelElementTrace> eIt = modelTrace.elements().get();
//		while (eIt.hasNext()) {
//			IModelElementTrace next = eIt.next();
//			if (next.getUri().equals(elementUri)) {
//				elementTrace = next;
//			}
//		}
//		return elementTrace;
//	}
	
//	@Override
//	public Set<IReexecutionTrace> getAllExecutionTraces() {
//		HashSet<IReexecutionTrace> retVal = new HashSet<IReexecutionTrace>();
//		extent.forEach(e -> retVal.addAll(IncrementalUtils.asStream(e.moduleElements().get())
//				.collect(Collectors.toSet())));
//		return retVal;
//	}
	
	@Override
	public Set<TraceReexecution> findAllInstancesExecutionTraces(String moduleUri, String modelUri, String typeName) {
		
		return findAllInstancesExecutionTraces(moduleUri, null, typeName, false);
	}
	
	@Override
	public Set<TraceReexecution> findAllInstancesExecutionTraces(String moduleUri, String modelUri, String typeName, boolean ofKind) {
		Set<TraceReexecution> result = new HashSet<>();
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		Iterator<IAccess> it = moduleTrace.accesses().get();
		while (it.hasNext()) {
			IAccess access = it.next();
			if (access instanceof IAllInstancesAccess) {
				IAllInstancesAccess allInstAcc = (IAllInstancesAccess) access;
				if (allInstAcc.type().get().getName().equals(typeName)
						&& (allInstAcc.getOfKind() == ofKind)) {
					Optional<TraceReexecution> rext = createReexecutionTrace(allInstAcc.in().get(), allInstAcc.from().get()); 
					if (rext.isPresent()) {
						result.add(rext.get());
					}
				}
			}
		}
		return result;
	}
	
//	@Override
//	public Set<IReexecutionTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
//		Set<IReexecutionTrace> result = new HashSet<>();
//		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
//		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
//		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
//		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
//				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
//		
//		Iterator<IAccess> it = moduleTrace.accesses().get();
//		while (it.hasNext()) {
//			IAccess a = it.next();
//			boolean elementIsAccessed = false;
//			if (a instanceof IElementAccess) {
//				elementIsAccessed = ((IElementAccess)a).element().get().equals(modelElementTrace);
//			}
//			else if (a instanceof IPropertyAccess) {
//				elementIsAccessed = ((IPropertyAccess)a).property().get().elementTrace().get().equals(modelElementTrace);
//			}
//			else if (a instanceof IAllInstancesAccess) {
//				elementIsAccessed = allElementTypes.contains(((IAllInstancesAccess)a).type().get().getName());
//			}
//			if (elementIsAccessed) {
//				IExecutionContext exContext = a.in().get();
//				if (IncrementalUtils.asStream(exContext.contextVariables().get())
//								.anyMatch(v -> v.getName().equals("self") 
//											&& !v.value().get().equals(modelElementTrace))) {
//					
//					Optional<IReexecutionTrace> rext = createReexecutionTrace(exContext, a.from().get());
//					if (rext.isPresent()) {
//						result.add(rext.get());
//					}
//				}								
//			}
//		}
//		return result;	
//	}
//	
//	@Override
//	public Set<IReexecutionTrace> findModelElementExecutionTraces(String moduleUri, String elementUri, String modelUri) {
//		Set<IReexecutionTrace> result = new HashSet<>();
//		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
//		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
//		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
//		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
//				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
//		
//		Iterator<IAccess> it = moduleTrace.accesses().get();
//		while (it.hasNext()) {
//			IAccess a = it.next();
//			boolean elementIsAccessed = false;
//			if (a instanceof IElementAccess) {
//				elementIsAccessed = ((IElementAccess)a).element().get().equals(modelElementTrace);
//			}
//			else if (a instanceof IPropertyAccess) {
//				elementIsAccessed = ((IPropertyAccess)a).property().get().elementTrace().get().equals(modelElementTrace);
//			}
//			else if (a instanceof IAllInstancesAccess) {
//				elementIsAccessed = allElementTypes.contains(((IAllInstancesAccess)a).type().get().getName());
//			}
//			if (elementIsAccessed) {
//				IExecutionContext exContext = a.in().get();
//				Optional<IReexecutionTrace> rext = createReexecutionTrace(exContext, a.from().get());
//				if (rext.isPresent()) {
//					result.add(rext.get());
//				}
//			}
//		}
//		return result;	
//	}
	
	
	@Override
	public Set<TraceReexecution> findPropertyAccessExecutionTraces(
		String moduleUri,
		IPropertyTrace propertyTrace) {
		
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		moduleTrace.accesses().get();
//		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
//		if (modelTrace == null) {
//			// Model not involved in execution
//			return Collections.emptySet();
//		}
//		IModelElementTrace elementTrace = getModelElementTraceFromModel(elementId, modelTrace);
//		if (elementTrace == null) {
//			// No info about element, there should be no trace associated to it
//			return Collections.emptySet();
//		}
//		IPropertyTrace pt = null;
//		Iterator<IPropertyTrace> pIt = elementTrace.properties().get();
//		while (pIt.hasNext()) {
//			pt = pIt.next();
//			if (pt.getName().equals(propertyName)) {
//				break;
//			}
//		}
//		if (pt == null) {
//			// Element does not have property by that name
//			return Collections.emptySet();
//		}
		Set<TraceReexecution> result = new HashSet<>();
		IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance)
				.map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().equals(propertyTrace))
				.map(pa -> createReexecutionTrace(pa.in().get(), pa.from().get()))
				.filter(Optional::isPresent)
				.forEach(et -> result.add(et.get()));
		return result;

	}
	
	@Override
	public IInvariantTrace findInvariantTraceinContext(IContextTrace contextTrace, String invariantName) {
		// TODO Implement IEvlModuleTraceRepository.findInvariantTraceinContext
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findInvariantTraceinContext invoked.");
	}

	@Override
	public ICheckResult findResultInCheck(ICheckTrace checkTrace, IExecutionContext currentContext) {
		// TODO Implement IEvlModuleTraceRepository.findResultInCheck
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findResultInCheck invoked.");
	}

	@Override
	public IGuardResult findResultInGuard(IGuardTrace guardTrace, IExecutionContext currentContext) {
		// TODO Implement IEvlModuleTraceRepository.findResultInGuard
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findResultInGuard invoked.");
	}
	
	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {

//		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
//		IModelTrace modelTrace = getModelTraceForModule(modellUri, moduleUri);
//		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
//
//		List<IElementAccess> elementAccessTraces = IncrementalUtils.asStream(moduleTrace.accesses().get())
//				.filter(IElementAccess.class::isInstance).map(IElementAccess.class::cast)
//				.filter(ea -> ea.element().get().equals(modelElementTrace)).collect(Collectors.toList());
//		for (IElementAccess ea : elementAccessTraces) {
//			IModuleElementTrace executionTrace = ea.executionTrace().get();
//			ea.executionTrace().destroy(executionTrace);
//			List<IAccess> list = new ArrayList<>();
//			executionTrace.accesses().get().forEachRemaining(list::add);
//			if (list.isEmpty()) {
//				moduleTrace.moduleElements().destroy(executionTrace);
//			}
//			moduleTrace.accesses().destroy(ea);
//		}
//		List<IPropertyAccess> propertyAccessTraces = IncrementalUtils.asStream(moduleTrace.accesses().get())
//				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
//				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
//				.collect(Collectors.toList());
//		for (IPropertyAccess pa : propertyAccessTraces) {
//			IModuleElementTrace executionTrace = pa.executionTrace().get();
//			pa.executionTrace().destroy(executionTrace);
//			List<IAccess> list = new ArrayList<>();
//			executionTrace.accesses().get().forEachRemaining(list::add);
//			if (list.isEmpty()) {
//				moduleTrace.moduleElements().destroy(executionTrace);
//			}
//			moduleTrace.accesses().destroy(pa);
//		}

		// modelTrace.elements().destroy(modelElementTrace);
		// TODO We could delete allInstances access is no more elements of the type
		// exist in the model?
	}
	
	private Optional<TraceReexecution> createReexecutionTrace(
			IExecutionContext exContext,
			IModuleElementTrace met) {
			
			TraceReexecution rt = null;
			// This could be a factory method in the IxxxTrace
			// IxxxTrace.createRexecutionTrace(IExecutionContext exContext)
//			if (met instanceof IContextTrace) {
//				rt = new ReexecutionContextTrace((IContextTrace) met, exContext);
//			}
//			else if (met instanceof IInvariantTrace) {
//				rt = new ReexecutionInvariantTrace((IInvariantTrace) met, exContext);
//			}
//			else if (met instanceof IGuardTrace) {
//				rt = new ReexecutionGuardTrace((IGuardTrace) met, exContext);
//			}
//			else if (met instanceof ICheckTrace) {
//				rt = new ReexecutionCheckTrace((ICheckTrace) met, exContext);
//			}
//			else if (met instanceof IMessageTrace) {
//				rt = new ReexecutionMessageTrace((IMessageTrace) met, exContext);
//			}
			return Optional.of(rt);
			
		}

	/**
	 * Filter the IExecutionContext based on the provided filter and if matched, return a
	 * IReexecutionTrace of the context and its trace.
	 * 
	 * @param exContext
	 * @param met
	 * @param filter
	 * @return
	 */
	private Optional<TraceReexecution> filterExecutionContexts(
		IExecutionContext exContext,
		IModuleElementTrace met,
		Function<IExecutionContext, Boolean> filter) {
		if (filter.apply(exContext)) {
			TraceReexecution rt = null;
			// This could be a factory method in the IxxxTrace
			// IxxxTrace.createRexecutionTrace(IExecutionContext exContext)
//			if (met instanceof IContextTrace) {
//				rt = new ReexecutionContextTrace((IContextTrace) met, exContext);
//			}
//			else if (met instanceof IInvariantTrace) {
//				rt = new ReexecutionInvariantTrace((IInvariantTrace) met, exContext);
//			}
//			else if (met instanceof IGuardTrace) {
//				rt = new ReexecutionGuardTrace((IGuardTrace) met, exContext);
//			}
//			else if (met instanceof ICheckTrace) {
//				rt = new ReexecutionCheckTrace((ICheckTrace) met, exContext);
//			}
//			else if (met instanceof IMessageTrace) {
//				rt = new ReexecutionMessageTrace((IMessageTrace) met, exContext);
//			}
			return Optional.of(rt);
		}
		return Optional.empty();
	}
	
	@Override
	public Collection<IPropertyAccess> getPropertyAccessesForElement(
			String elementUri,
			String modelUri,
			String moduleUri) {
		IModuleExecutionTrace moduleTrace = getModuleExecutionTraceByIdentity(moduleUri);
 		//IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		return IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().get().getUri().equals(elementUri)
							&& pa.property().get().elementTrace().get().modelTrace().get().getUri().equals(elementUri))
				.collect(Collectors.toList());
	}


	@Override
	public Set<TraceReexecution> findPropertyAccessExecutionTraces(IPropertyAccess pa) {
		// TODO Implement IEvlModuleTraceRepository.findPropertyAccessExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findPropertyAccessExecutionTraces invoked.");
	}


	@Override
	public Set<TraceReexecution> findIndirectExecutionTraces(String moduleUri, Object elementId,
			Collection<String> allElementTypes) {
		// TODO Implement IEvlModuleTraceRepository.findIndirectExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findIndirectExecutionTraces invoked.");
	}

	@Override
	public Set<TraceReexecution> findModelElementExecutionTraces(String moduleUri, String elementUri, String modelUri,
			Set<String> allElementTypes) {
		// TODO Implement IEvlModuleTraceRepository.findModelElementExecutionTraces
		throw new RuntimeException("Unimplemented Method IEvlModuleTraceRepository.findModelElementExecutionTraces invoked.");
	}
	
	
//	/**
//	 * @param modelElementUri
//	 * @param moduleTrace
//	 * @return
//	 */
//	private List<IPropertyAccess> getPropertyAccessesOfModelElement(IModelElementTrace modelElementTrace,
//			IEvlModuleTrace moduleTrace) {
//		return IncrementalUtils.asStream(moduleTrace.accesses().get())
//				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
//				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
//				.collect(Collectors.toList());
//	}
//
//	/**
//	 * AllInstancesAccess have to be done in the type, and in the super types
//	 * 
//	 * @param modelElement
//	 * @param modelUri
//	 * @param moduleTrace
//	 * @return
//	 */
//	private List<IAllInstancesAccess> getAllInstancesAccessOfModelElement(String elementType,
//			Set<String> allElementTypes, String modelUri, IEvlModuleTrace moduleTrace) {
//
//		return IncrementalUtils.asStream(moduleTrace.accesses().get())
//				.filter(IAllInstancesAccess.class::isInstance)
//				.map(IAllInstancesAccess.class::cast).filter(aia -> {
//					IModelTrace modelTrace = aia.type().get().modelTrace().get();
//					if (modelTrace.getUri().equals(modelUri)) {
//						if (aia.getOfKind()) {
//							return allElementTypes.contains(aia.type().get().getName());
//						} else {
//							return aia.type().get().getName().equals(elementType);
//						}
//					}
//					return false;
//				}).collect(Collectors.toList());
//	}
//
//	/**
//	 * @param modelElementUri
//	 * @param moduleTrace
//	 * @return
//	 */
//	private List<IElementAccess> getElementAccessesOfModelElement(IModelElementTrace modelElementTrace,
//			IEvlModuleTrace moduleTrace) {
//		return IncrementalUtils.asStream(moduleTrace.accesses().get())
//				.filter(IElementAccess.class::isInstance).map(IElementAccess.class::cast)
//				.filter(ea -> ea.element().get().equals(modelElementTrace))
//				.collect(Collectors.toList());
//	}

	/** protected region IEvlModuleTraceRepositry end **/

}