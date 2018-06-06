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

import org.eclipse.epsilon.base.incremental.trace.IIdElement;
import org.eclipse.epsilon.base.incremental.trace.IIdElementRepository;

/** protected region IdElementRepositoryImplImports on begin **/
/** protected region IdElementRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class IdElementRepositoryImpl implements IIdElementRepository {

    private static final Logger logger = LoggerFactory.getLogger(IdElementRepositoryImpl.class);
    
    private final Set<IIdElement> extent;
    
    public IdElementRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IIdElement item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IIdElement item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IIdElement get(Object id) {
        
        logger.debug("Get IdElement with id:{}", id);
        IIdElement  result = null;
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
    
    /** protected region IIdElementRepositry on begin **/
    // Specialised search methods

    /** protected region IIdElementRepositry end **/

}