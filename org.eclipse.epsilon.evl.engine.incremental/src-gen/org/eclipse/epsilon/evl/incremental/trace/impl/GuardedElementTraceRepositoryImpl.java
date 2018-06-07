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

import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceRepository;

/** protected region GuardedElementTraceRepositoryImplImports on begin **/
/** protected region GuardedElementTraceRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GuardedElementTraceRepositoryImpl implements IGuardedElementTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(GuardedElementTraceRepositoryImpl.class);
    
    private final Set<IGuardedElementTrace> extent;
    
    public GuardedElementTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IGuardedElementTrace item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IGuardedElementTrace item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IGuardedElementTrace get(Object id) {
        
        logger.debug("Get GuardedElementTrace with id:{}", id);
        IGuardedElementTrace  result = null;
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
    
    /** protected region IGuardedElementTraceRepositry on begin **/
    // Specialised search methods

    /** protected region IGuardedElementTraceRepositry end **/

}