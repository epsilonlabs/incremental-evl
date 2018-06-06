 /*******************************************************************************
 * This file was automatically generated on: 2018-06-06.
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

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;

/** protected region ModuleExecutionTraceRepositoryImplImports on begin **/
/** protected region ModuleExecutionTraceRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ModuleExecutionTraceRepositoryImpl implements IModuleExecutionTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModuleExecutionTraceRepositoryImpl.class);
    
    private final Set<IModuleExecutionTrace> extent;
    
    public ModuleExecutionTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IModuleExecutionTrace item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IModuleExecutionTrace item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IModuleExecutionTrace get(Object id) {
        
        logger.debug("Get ModuleExecutionTrace with id:{}", id);
        IModuleExecutionTrace  result = null;
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
    
    /** protected region IModuleExecutionTraceRepositry on begin **/
    // Specialised search methods

    /** protected region IModuleExecutionTraceRepositry end **/

}