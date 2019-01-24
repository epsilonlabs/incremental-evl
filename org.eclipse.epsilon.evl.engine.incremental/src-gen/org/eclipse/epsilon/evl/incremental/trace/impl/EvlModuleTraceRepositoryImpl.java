 /*******************************************************************************
 * This file was automatically generated on: 2019-01-23.
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


import org.eclipse.epsilon.evl.IReexecutionTrace;
import org.eclipse.epsilon.evl.ReexecutionCheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceRepositoryImpl;


/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.Collections;
import java.util.function.Function;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;


import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.ReexecutionContextTrace;
import org.eclipse.epsilon.evl.ReexecutionGuardTrace;
import org.eclipse.epsilon.evl.ReexecutionInvariantTrace;
import org.eclipse.epsilon.evl.ReexecutionMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.*;
/** protected region EvlModuleTraceRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvlModuleTraceRepositoryImpl extends ModuleExecutionTraceRepositoryImpl<IEvlModuleTrace> implements IEvlModuleTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceRepositoryImpl.class);
 
    
    public EvlModuleTraceRepositoryImpl() {
        super();
    }
    
    @Override
    public boolean add(IEvlModuleTrace item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IEvlModuleTrace item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
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
	public Set<IEvlModuleTrace> getAllModuleTraces() {
		return Collections.unmodifiableSet(extent);
	}

    @Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String source) {
		return (IEvlModuleTrace) extent.stream().filter(mt -> mt.getUri() == source).findFirst().orElse(null);
	}

    @Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source) {
		return getEvlModuleTraceByIdentity(source);
	}
    
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		IModuleExecutionTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		return getModelTraceForModule(modelUri, moduleTrace);
	}
    
	@Override
	public IModelTrace getModelTraceForModule(String modelUri, IModuleExecutionTrace moduleTrace) {
		IModelTrace modelTrace = null;
		Iterator<IModelAccess> it = moduleTrace.models().get();
		while (it.hasNext()) {
			IModelAccess next = it.next();
			IModelTrace mt = next.modelTrace().get();
			if (mt.getUri().equals(modelUri)) {
				modelTrace = mt;
				break;
			}
		}
		return modelTrace;
	}

	@Override
	public IModelElementTrace getModelElementTraceFromModel(String elementUri, IModelTrace modelTrace) {
		IModelElementTrace elementTrace = null;
		Iterator<IModelElementTrace> eIt = modelTrace.elements().get();
		while (eIt.hasNext()) {
			IModelElementTrace next = eIt.next();
			if (next.getUri().equals(elementUri)) {
				elementTrace = next;
			}
		}
		return elementTrace;
	}
	
//	@Override
//	public Set<IReexecutionTrace> getAllExecutionTraces() {
//		HashSet<IReexecutionTrace> retVal = new HashSet<IReexecutionTrace>();
//		extent.forEach(e -> retVal.addAll(IncrementalUtils.asStream(e.moduleElements().get())
//				.collect(Collectors.toSet())));
//		return retVal;
//	}
	
	@Override
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName) {
		
		return findAllInstancesExecutionTraces(moduleUri, typeName, false);
	}
	
	@Override
	public Set<IReexecutionTrace> findAllInstancesExecutionTraces(String moduleUri, String typeName, boolean ofType) {
		Set<IReexecutionTrace> result = new HashSet<>();
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		Iterator<IModuleElementTrace> it = moduleTrace.moduleElements().get();
		while (it.hasNext()) {
			IModuleElementTrace met = it.next();
			Iterator<IExecutionContext> exContextIt;
			if (met instanceof IContextModuleElementTrace) {
				exContextIt = ((IContextModuleElementTrace) met).executionContext().get();
			} else {
				exContextIt = ((IInContextModuleElementTrace)met).contextModuleElement().get().executionContext().get();
			}
			while (exContextIt.hasNext()) {
				IExecutionContext exContext = exContextIt.next();
				Optional<IReexecutionTrace> filtered = filterExecutionContexts(exContext, met, (x) -> {
						return IncrementalUtils.asStream(x.accesses().get())
								.filter(IAllInstancesAccess.class::isInstance)
								.map(IAllInstancesAccess.class::cast)
								.anyMatch(aia -> aia.type().get().getName().equals(typeName)
										&& (ofType && !aia.getOfKind()));
					});
				if (filtered.isPresent()) {
					result.add(filtered.get());
				}
			}
		}
		return result;
	}
	
	@Override
	public Set<IReexecutionTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		Set<IReexecutionTrace> result = new HashSet<>();
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		Iterator<IModuleElementTrace> it = moduleTrace.moduleElements().get();
		
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
		
		while (it.hasNext()) {
			IModuleElementTrace met = it.next();
			Iterator<IExecutionContext> exContextIt;
			if (met instanceof IContextModuleElementTrace) {
				exContextIt = ((IContextModuleElementTrace) met).executionContext().get();
			} else {
				exContextIt = ((IInContextModuleElementTrace)met).contextModuleElement().get().executionContext().get();
			}
			while (exContextIt.hasNext()) {
				IExecutionContext exContext = exContextIt.next();
				Optional<IReexecutionTrace> filtered = filterExecutionContexts(exContext, met, (x) -> {
					return !IncrementalUtils.asStream(x.contextVariables().get())
							.anyMatch(v -> v.getName().equals("self") 
									&& v.value().get().equals(modelElementTrace))
							&& IncrementalUtils.asStream(x.accesses().get())
								.anyMatch(a -> {
									if (a instanceof IElementAccess) {
										return ((IElementAccess)a).element().get().equals(modelElementTrace);
									}
									if (a instanceof IPropertyAccess) {
										return ((IPropertyAccess)a).property().get().elementTrace().get().equals(modelElementTrace);
									}
									if (a instanceof IAllInstancesAccess) {
										return allElementTypes.contains(((IAllInstancesAccess)a).type().get().getName());
									}
									return false;
								});
				});
				if (filtered.isPresent()) {
					result.add(filtered.get());
				}
			}
		}
		return result;	
	}
	
	@Override
	public Set<IReexecutionTrace> findModelElementExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		Set<IReexecutionTrace> result = new HashSet<>();
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		Iterator<IModuleElementTrace> it = moduleTrace.moduleElements().get();
		
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
		
		while (it.hasNext()) {
			IModuleElementTrace met = it.next();
			Iterator<IExecutionContext> exContextIt;
			if (met instanceof IContextModuleElementTrace) {
				exContextIt = ((IContextModuleElementTrace) met).executionContext().get();
			} else {
				exContextIt = ((IInContextModuleElementTrace)met).contextModuleElement().get().executionContext().get();
			}
			while (exContextIt.hasNext()) {
				IExecutionContext exContext = exContextIt.next();
				Optional<IReexecutionTrace> filtered = filterExecutionContexts(exContext, met, (x) -> {
					return IncrementalUtils.asStream(x.accesses().get())
							.anyMatch(a -> {
								if (a instanceof IElementAccess) {
									return ((IElementAccess)a).element().get().equals(modelElementTrace);
								}
								if (a instanceof IPropertyAccess) {
									return ((IPropertyAccess)a).property().get().elementTrace().get().equals(modelElementTrace);
								}
								if (a instanceof IAllInstancesAccess) {
									return allElementTypes.contains(((IAllInstancesAccess)a).type().get().getName());
								}
								return false;
							});
				});
				if (filtered.isPresent()) {
					result.add(filtered.get());
				}
			}
		}
		return result;
	}
	
	
	@Override
	public Set<IReexecutionTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri,
			String elementId, String propertyName) {
		
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleTrace);
		if (modelTrace == null) {
			// Model not involved in execution
			return Collections.emptySet();
		}
		IModelElementTrace elementTrace = getModelElementTraceFromModel(elementId, modelTrace);
		if (elementTrace == null) {
			// No info about element, there should be no trace associated to it
			return Collections.emptySet();
		}
		IPropertyTrace pt = null;
		Iterator<IPropertyTrace> pIt = elementTrace.properties().get();
		while (pIt.hasNext()) {
			pt = pIt.next();
			if (pt.getName().equals(propertyName)) {
				break;
			}
		}
		if (pt == null) {
			// Element does not have property by that name
			return Collections.emptySet();
		}
		IPropertyTrace pt2 = pt;
		Set<IReexecutionTrace> result = new HashSet<>();
		Iterator<IModuleElementTrace> it = moduleTrace.moduleElements().get();
		while (it.hasNext()) {
			IModuleElementTrace met = it.next();
			Iterator<IExecutionContext> exContextIt;
			if (met instanceof IContextModuleElementTrace) {
				exContextIt = ((IContextModuleElementTrace) met).executionContext().get();
			} else {
				exContextIt = ((IInContextModuleElementTrace)met).contextModuleElement().get().executionContext().get();
			}
			while (exContextIt.hasNext()) {
				IExecutionContext exContext = exContextIt.next();
				Optional<IReexecutionTrace> filtered = filterExecutionContexts(exContext, met, (x) -> {
					return IncrementalUtils.asStream(x.accesses().get())
							.filter(IPropertyAccess.class::isInstance)
							.map(IPropertyAccess.class::cast)
							.anyMatch(pa -> pa.property().get().equals(pt2));
				});
				if (filtered.isPresent()) {
					result.add(filtered.get());
				}	
			}
		}
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

	/**
	 * Filter the IExecutionContext based on the provided filter and if matched, return a
	 * IReexecutionTrace of the context and its trace.
	 * 
	 * @param exContext
	 * @param met
	 * @param filter
	 * @return
	 */
	private Optional<IReexecutionTrace> filterExecutionContexts(
		IExecutionContext exContext,
		IModuleElementTrace met,
		Function<IExecutionContext, Boolean> filter) {
		if (filter.apply(exContext)) {
			IReexecutionTrace rt = null;
			// This could be a factory method in the IxxxTrace
			// IxxxTrace.createRexecutionTrace(IExecutionContext exContext)
			if (met instanceof IContextTrace) {
				rt = new ReexecutionContextTrace((IContextTrace) met, exContext);
			}
			else if (met instanceof IInvariantTrace) {
				rt = new ReexecutionInvariantTrace((IInvariantTrace) met, exContext);
			}
			else if (met instanceof IGuardTrace) {
				rt = new ReexecutionGuardTrace((IGuardTrace) met, exContext);
			}
			else if (met instanceof ICheckTrace) {
				rt = new ReexecutionCheckTrace((ICheckTrace) met, exContext);
			}
			else if (met instanceof IMessageTrace) {
				rt = new ReexecutionMessageTrace((IMessageTrace) met, exContext);
			}
			return Optional.of(rt);
		}
		return Optional.empty();
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