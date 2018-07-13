 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
/** protected region EvlModuleTraceRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvlModuleTraceRepositoryImpl extends ModuleExecutionTraceRepositoryImpl<IEvlModuleTrace> implements IEvlModuleTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(EvlModuleTraceRepositoryImpl.class);
    
    private final Set<IEvlModuleTrace> extent;
    
    public EvlModuleTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
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
            result = extent.stream()
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
		return extent.stream()
				.filter(mt -> mt.getUri() == source)
				.findFirst()
				.orElse(null);
	}

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source) {
		return getEvlModuleTraceByIdentity(source);
	}
	
	
	@Override
	public Set<IEvlModuleTrace> findPropertyAccessExecutionTraces(String moduleSource,
			IModelElementTrace modelElementTrace, String propertyName) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleSource);
		Set<IEvlModuleTrace> result = moduleTrace.accesses().get().stream()
				.filter(IPropertyAccess.class::isInstance)
				.map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().getName().equals(propertyName) &&
						modelElementTrace.properties().get().contains(pa.property().get())
				)
				.map(pa -> pa.executionTrace().get())
				.map(IEvlModuleTrace.class::cast)
				.collect(Collectors.toSet());
		return result;
	}		
	
	@Override
	public Set<IEvlModuleTrace> findAllInstancesExecutionTraces(String moduleSource, String typeName) {
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleSource);
		Set<IEvlModuleTrace> result = moduleTrace.accesses().get().stream()
				.filter(IAllInstancesAccess.class::isInstance)
				.map(IAllInstancesAccess.class::cast)
				.filter(aia -> aia.type().get().getName().equals(typeName))
				.map(aia -> aia.executionTrace().get())
				.map(IEvlModuleTrace.class::cast)
				.collect(Collectors.toSet());
		return result;
	}
	
	
	@Override
	public Set<IEvlModuleTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace) {
		// TODO Implement IEvlExecutionTraceRepository.findSatisfiesExecutionTraces
		throw new UnsupportedOperationException("Unimplemented Method    IEvlExecutionTraceRepository.findSatisfiesExecutionTraces invoked.");
	}

	@Override
	public Set<IEvlModuleTrace> getAllExecutionTraces() {
		return Collections.unmodifiableSet(extent);
	}

	@Override
	public Set<IEvlModuleTrace> findIndirectExecutionTraces(String moduleSource, IModelElementTrace modelElementTrace,
			Object modelElement, IIncrementalModel model) {
		
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleSource);
		
		List<IEvlModuleTrace> elementTraces = getElementAccessesOfModelElement(modelElementTrace, moduleTrace);
		List<IEvlModuleTrace> propertyTraces = getPropertyAccessesOfModelElement(modelElementTrace, moduleTrace);
		List<IEvlModuleTrace> typeTraces = getAllInstancesAccessOfModelElement(modelElement, model, moduleTrace);
		Set<IEvlModuleTrace> result = new HashSet<IEvlModuleTrace>();
		result.addAll(elementTraces);
		result.addAll(propertyTraces);
		result.addAll(typeTraces);
		return result;
	}
	
	@Override
	public void removeTraceInformation(String moduleSource, IIncrementalModel model, IModelElementTrace modelElementTrace) {
		
		IEvlModuleTrace moduleTrace = getEvlModuleTraceByIdentity(moduleSource);
		//IModelTrace modelTrace = getModelTraceByIdentity(moduleSource, model.getName(), model.getModelUri());
		//IModelElementTrace modelElementTrace = getModelElementTraceFor(moduleSource, model.getName(), model.getModelUri(), modelElementUri);
		// ElementAccess
		List<IElementAccess> elementAccessTraces = moduleTrace.accesses().get().stream()
				.filter(IElementAccess.class::isInstance)
				.map(IElementAccess.class::cast)
				.filter(ea -> ea.element().get().equals(modelElementTrace))
				.collect(Collectors.toList());
		for (IElementAccess ea : elementAccessTraces) {
			IModuleElementTrace executionTrace = ea.executionTrace().get();
			ea.executionTrace().destroy(executionTrace);
			if (executionTrace.accesses().get().isEmpty()) {
				moduleTrace.moduleElements().destroy(executionTrace);
			}
			moduleTrace.accesses().destroy(ea);
		}
		List<IPropertyAccess> propertyAccessTraces = moduleTrace.accesses().get().stream()
				.filter(IPropertyAccess.class::isInstance)
				.map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().equals(modelElementTrace))
				.collect(Collectors.toList());
		for (IPropertyAccess pa : propertyAccessTraces) {
			IModuleElementTrace executionTrace = pa.executionTrace().get();
			pa.executionTrace().destroy(executionTrace);
			if (executionTrace.accesses().get().isEmpty()) {
				moduleTrace.moduleElements().destroy(executionTrace);
			}
			moduleTrace.accesses().destroy(pa);
		}
		
		//modelTrace.elements().destroy(modelElementTrace);
		// TODO We could delete allInstances access is no more elements of the type exist in the model?
	}
	
	/**
	 * @param modelElementUri
	 * @param moduleTrace
	 * @return
	 */
	private List<IEvlModuleTrace> getPropertyAccessesOfModelElement(IModelElementTrace modelElementTrace,
			IEvlModuleTrace moduleTrace) {
		// PropertyAccess to the element properties
		List<IEvlModuleTrace> propertyTraces = moduleTrace.accesses().get().stream()
				.filter(IPropertyAccess.class::isInstance)
				.map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().elementTrace().get().equals(modelElementTrace))
				.map(pa -> pa.executionTrace().get())
				.map(IEvlModuleTrace.class::cast)
				.collect(Collectors.toList());
		return propertyTraces;
	}
	
	/**
	 * AllInstancesAccess have to be done in the type, and in the super types
	 * @param modelElement
	 * @param model
	 * @param moduleTrace
	 * @return
	 */
	private List<IEvlModuleTrace> getAllInstancesAccessOfModelElement(Object modelElement,
			IIncrementalModel model, IEvlModuleTrace moduleTrace) {
		Set<String> elementTypes = model.getAllTypeNamesOf(modelElement);
		String elementType = model.getTypeNameOf(modelElement);
		List<IEvlModuleTrace> typeTraces = moduleTrace.accesses().get().stream()
				.filter(IAllInstancesAccess.class::isInstance)
				.map(IAllInstancesAccess.class::cast)
				.filter(aia -> {
					IModelTrace modelTrace = aia.type().get().modelTrace().get();
					if (modelTrace.getUri() == model.getModelUri()) {
						if (aia.getOfKind()) {
							return elementTypes.contains(aia.type().get().getName());
						}
						else {
							return aia.type().get().getName() == elementType;
						}
					}
					return false;
				})
				.map(pa -> pa.executionTrace().get())
				.map(IEvlModuleTrace.class::cast)
				.collect(Collectors.toList());
		return typeTraces;
	}

	/**
	 * @param modelElementUri
	 * @param moduleTrace
	 * @return
	 */
	private List<IEvlModuleTrace> getElementAccessesOfModelElement(IModelElementTrace modelElementTrace,
			IEvlModuleTrace moduleTrace) {
		List<IEvlModuleTrace> elementTraces = moduleTrace.accesses().get().stream()
				.filter(IElementAccess.class::isInstance)
				.map(IElementAccess.class::cast)
				.filter(ea -> ea.element().get().equals(modelElementTrace))
				.map(ea -> ea.executionTrace().get())
				.map(IEvlModuleTrace.class::cast)
				.collect(Collectors.toList());
		return elementTraces;
	}

    /** protected region IEvlModuleTraceRepositry end **/

}