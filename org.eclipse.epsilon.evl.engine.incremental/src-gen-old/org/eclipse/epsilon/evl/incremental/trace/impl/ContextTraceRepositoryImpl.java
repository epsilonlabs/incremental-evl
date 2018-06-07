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
package org.eclipse.epsilon.evl.incremental.trace.impl;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceRepository;

/** protected region ContextTraceImports on begin **/

/** protected region ContextTraceImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextTraceRepositoryImpl implements IContextTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ContextTraceRepositoryImpl.class);
    
    private final Set<IContextTrace> extent;
    
    public ContextTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IContextTrace item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IContextTrace item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IContextTrace get(Object id) {
        
        logger.debug("Get ContextTrace with id:{}", id);
        IContextTrace  result = null;
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