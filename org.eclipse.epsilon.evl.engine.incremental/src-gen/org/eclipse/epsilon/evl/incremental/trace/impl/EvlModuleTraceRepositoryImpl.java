 /*******************************************************************************
 * This file was automatically generated on: 2018-06-07.
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
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
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
		// TODO Implement IEvlModuleTraceRepository.getEvlModuleTraceByIdentity
		throw new UnsupportedOperationException("Unimplemented Method    IEvlModuleTraceRepository.getEvlModuleTraceByIdentity invoked.");
	}

	@Override
	public IModuleExecutionTrace getModuleExecutionTraceByIdentity(String source) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.getModuleExecutionTraceByIdentity
		throw new UnsupportedOperationException("Unimplemented Method    IModuleExecutionTraceRepository<IEvlModuleTrace>.getModuleExecutionTraceByIdentity invoked.");
	}

	@Override
	public IPropertyTrace getPropertyTraceFor(IModuleExecutionTrace moduleExecutionTrace, String modelName,
			String modelId, String elementId, String propertyName) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.getPropertyTraceFor
		throw new UnsupportedOperationException("Unimplemented Method    IModuleExecutionTraceRepository<IEvlModuleTrace>.getPropertyTraceFor invoked.");
	}

	@Override
	public IModelTypeTrace getTypeTraceFor(String modelName, String modelId, String typeName) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.getTypeTraceFor
		throw new UnsupportedOperationException("Unimplemented Method    IModuleExecutionTraceRepository<IEvlModuleTrace>.getTypeTraceFor invoked.");
	}

	@Override
	public IModelTrace getModelTraceFor(String name, String modelId) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.getModelTraceFor
		throw new UnsupportedOperationException("Unimplemented Method    IModuleExecutionTraceRepository<IEvlModuleTrace>.getModelTraceFor invoked.");
	}

	@Override
	public IModelElementTrace getModelElementTraceFor(String modelName, String modelId, String elementId) {
		// TODO Implement IModuleExecutionTraceRepository<IEvlModuleTrace>.getModelElementTraceFor
		throw new UnsupportedOperationException("Unimplemented Method    IModuleExecutionTraceRepository<IEvlModuleTrace>.getModelElementTraceFor invoked.");
	}

    /** protected region IEvlModuleTraceRepositry end **/

}