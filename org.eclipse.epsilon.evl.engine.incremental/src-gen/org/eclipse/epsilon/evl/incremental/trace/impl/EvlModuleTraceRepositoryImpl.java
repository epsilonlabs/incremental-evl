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

/** protected region EvlModuleTraceRepositoryImplImports on begin **/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IInContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceRepositoryImpl;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
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
	// Specialised search methods
	@Override
	public IEvlModuleTrace getEvlModuleTraceByIdentity(String source) {
		return (IEvlModuleTrace) extent.stream().filter(mt -> mt.getUri() == source).findFirst().orElse(null);
	}

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source) {
		return getEvlModuleTraceByIdentity(source);
	}

	@Override
	public Set<IModuleElementTrace> findPropertyAccessExecutionTraces(String moduleUri, String modelUri,
			String elementId, String propertyName) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleUri);
		if (modelTrace == null) {
			// Model not involved in execution
			return Collections.emptySet();
		}
		IModelElementTrace elementTrace = getModelElementTraceFromModel(elementId, modelTrace);
		if (elementTrace == null) {
			// No info about element, there should be no trace associated to it
			return Collections.emptySet();
		}
		IPropertyTrace next = null;
		Iterator<IPropertyTrace> pIt = elementTrace.properties().get();
		while (pIt.hasNext()) {
			next = pIt.next();
			if (next.getName().equals(propertyName)) {
				break;
			}
		}
		if (next == null) {
			// Element does not have property by that name
			return Collections.emptySet();
		}
		IPropertyTrace pt = next;
		Set<IModuleElementTrace> result = IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().equals(pt)).map(pa -> pa.executionTrace().get())
				.collect(Collectors.toSet());
		return result;

	}

	@Override
	public Set<IModuleElementTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleSource);

		Set<IModuleElementTrace> result = IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IAllInstancesAccess.class::isInstance).map(IAllInstancesAccess.class::cast)
				.filter(aia -> aia.type().get().getName().equals(typeName)).map(aia -> aia.executionTrace().get())
				.collect(Collectors.toSet());
		return result;
	}

	@Override
	public Set<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace) {
		// TODO Implement IEvlExecutionTraceRepository.findSatisfiesExecutionTraces
		throw new UnsupportedOperationException(
				"Unimplemented Method    IEvlExecutionTraceRepository.findSatisfiesExecutionTraces invoked.");
	}

	@Override
	public Set<IModuleElementTrace> getAllExecutionTraces() {
		HashSet<IModuleElementTrace> retVal = new HashSet<IModuleElementTrace>();
		extent.forEach(e -> retVal.addAll(IncrementalUtils.asStream(e.accesses().get())
				.map(acs -> acs.executionTrace().get()).collect(Collectors.toSet())));
		return retVal;
	}

	@Override
	public Set<IEvlModuleTrace> getAllModuleTraces() {
		return Collections.unmodifiableSet(extent);
	}

	@Override
	public Set<IModuleElementTrace> findIndirectExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		Set<IModuleElementTrace> allTraces = findAllExecutionTraces(moduleUri, elementUri, modelUri);
		// Filter the ones with the element as context
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleUri);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		return allTraces.stream().filter(t -> {
			IExecutionContext ctx = null;
			if (t instanceof IContextModuleElementTrace) {
				ctx = ((IContextModuleElementTrace) t).executionContext().get();
			}
			else if (t instanceof IInContextModuleElementTrace) {
				try {
				ctx = ((IInContextModuleElementTrace) t).parentTrace().get().executionContext().get();
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
			else {
				return false;
			}
			Optional<IModelElementVariable> self = IncrementalUtils.asStream(ctx.contextVariables().get())
					.filter(v -> v.getName().equals("self"))
					.findFirst();
			if (!self.isPresent()) {
				return false;
			}
			IModelElementVariable v = self.get();
			if (v.value().get().equals(modelElementTrace)) {
				return false;
			}
			return true;
		}).collect(Collectors.toSet());
	}
	
	@Override
	public Set<IModuleElementTrace> findAllExecutionTraces(String moduleUri, String elementUri, String modelUri) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modelUri, moduleUri);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);
		
		Set<IAccess> accesses = new HashSet<IAccess>();
		accesses.addAll(getElementAccessesOfModelElement(modelElementTrace, moduleTrace));
		accesses.addAll(getPropertyAccessesOfModelElement(modelElementTrace, moduleTrace));
		String elementType = modelElementTrace.type().get().getName();
		Set<String> allElementTypes = IncrementalUtils.asStream(modelElementTrace.kind().get())
				.map(IModelTypeTrace::getName).collect(Collectors.toSet());
		accesses.addAll(getAllInstancesAccessOfModelElement(elementType, allElementTypes,
				modelUri, moduleTrace));
		return accesses.stream().map(a -> a.executionTrace().get()).collect(Collectors.toSet());
	}

	@Override
	public void removeTraceInformation(String moduleUri, String elementUri, String modellUri) {

		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
		IModelTrace modelTrace = getModelTraceForModule(modellUri, moduleUri);
		IModelElementTrace modelElementTrace = getModelElementTraceFromModel(elementUri, modelTrace);

		List<IElementAccess> elementAccessTraces = IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IElementAccess.class::isInstance).map(IElementAccess.class::cast)
				.filter(ea -> ea.element().get().equals(modelElementTrace)).collect(Collectors.toList());
		for (IElementAccess ea : elementAccessTraces) {
			IModuleElementTrace executionTrace = ea.executionTrace().get();
			ea.executionTrace().destroy(executionTrace);
			List<IAccess> list = new ArrayList<>();
			executionTrace.accesses().get().forEachRemaining(list::add);
			if (list.isEmpty()) {
				moduleTrace.moduleElements().destroy(executionTrace);
			}
			moduleTrace.accesses().destroy(ea);
		}
		List<IPropertyAccess> propertyAccessTraces = IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
				.collect(Collectors.toList());
		for (IPropertyAccess pa : propertyAccessTraces) {
			IModuleElementTrace executionTrace = pa.executionTrace().get();
			pa.executionTrace().destroy(executionTrace);
			List<IAccess> list = new ArrayList<>();
			executionTrace.accesses().get().forEachRemaining(list::add);
			if (list.isEmpty()) {
				moduleTrace.moduleElements().destroy(executionTrace);
			}
			moduleTrace.accesses().destroy(pa);
		}

		// modelTrace.elements().destroy(modelElementTrace);
		// TODO We could delete allInstances access is no more elements of the type
		// exist in the model?
	}

	@Override
	public IModelTrace getModelTraceForModule(String modelUri, String moduleUri) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleUri);
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

	/**
	 * @param modelElementUri
	 * @param moduleTrace
	 * @return
	 */
	private List<IPropertyAccess> getPropertyAccessesOfModelElement(IModelElementTrace modelElementTrace,
			IEvlModuleTrace moduleTrace) {
		return IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IPropertyAccess.class::isInstance).map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
				.collect(Collectors.toList());
	}

	/**
	 * AllInstancesAccess have to be done in the type, and in the super types
	 * 
	 * @param modelElement
	 * @param modelUri
	 * @param moduleTrace
	 * @return
	 */
	private List<IAllInstancesAccess> getAllInstancesAccessOfModelElement(String elementType,
			Set<String> allElementTypes, String modelUri, IEvlModuleTrace moduleTrace) {

		return IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IAllInstancesAccess.class::isInstance)
				.map(IAllInstancesAccess.class::cast).filter(aia -> {
					IModelTrace modelTrace = aia.type().get().modelTrace().get();
					if (modelTrace.getUri().equals(modelUri)) {
						if (aia.getOfKind()) {
							return allElementTypes.contains(aia.type().get().getName());
						} else {
							return aia.type().get().getName().equals(elementType);
						}
					}
					return false;
				}).collect(Collectors.toList());
	}

	/**
	 * @param modelElementUri
	 * @param moduleTrace
	 * @return
	 */
	private List<IElementAccess> getElementAccessesOfModelElement(IModelElementTrace modelElementTrace,
			IEvlModuleTrace moduleTrace) {
		return IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IElementAccess.class::isInstance).map(IElementAccess.class::cast)
				.filter(ea -> ea.element().get().equals(modelElementTrace))
				.collect(Collectors.toList());
	}

	/** protected region IEvlModuleTraceRepositry end **/

}