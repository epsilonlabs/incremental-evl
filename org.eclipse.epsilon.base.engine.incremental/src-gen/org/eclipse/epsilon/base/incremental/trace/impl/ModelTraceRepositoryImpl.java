 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
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

import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;

/** protected region ModelTraceImports on begin **/
/** protected region ModelTraceImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelTraceRepositoryImpl implements IModelTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModelTraceRepositoryImpl.class);
    
    private final Set<IModelTrace> extent;
    
    public ModelTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IModelTrace item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IModelTrace item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IModelTrace get(Object id) {
        
        logger.debug("Get ModelTrace with id:{}", id);
        IModelTrace  result = null;
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

}