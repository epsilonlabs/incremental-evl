 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
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
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;


/** protected region ModuleExecutionTraceRepositoryImplImports on begin **/
/** protected region ModuleExecutionTraceRepositoryImplImports end **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public abstract class ModuleExecutionTraceRepositoryImpl<ST extends IModuleExecutionTrace> implements IModuleExecutionTraceRepository<ST> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleExecutionTraceRepositoryImpl.class);
 
    protected final Set<ST> extent;    
    
    public ModuleExecutionTraceRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public ST  add(ST item) {
        logger.info("Adding {} to repository", item);
        extent.add(item);
        // FIXME Perhaps throw exception if not added?
        return item;
    }

    @Override
    public ST  remove(ST item) {
        logger.info("Removing {} from repository", item);
        extent.remove(item);
        // FIXME Perhaps throw exception if not removed?
        return item;
    }
    
    @Override
    public void dispose() {
        this.extent.clear();
    }
    
    
    /** protected region IModuleExecutionTraceRepositry on begin **/
    // Specialised search methods

    /** protected region IModuleExecutionTraceRepositry end **/

}