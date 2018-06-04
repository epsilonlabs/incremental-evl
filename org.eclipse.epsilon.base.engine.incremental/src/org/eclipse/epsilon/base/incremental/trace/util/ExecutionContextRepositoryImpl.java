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
package org.eclipse.epsilon.base.incremental.trace.util;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;

/** protected region ExecutionContextRepositoryImplImports on begin **/
import java.util.Arrays;
import java.util.HashSet;
/** protected region ExecutionContextRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutionContextRepositoryImpl implements IExecutionContextRepository {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionContextRepositoryImpl.class);
    
    private final Set<IExecutionContext> extent;
    
    public ExecutionContextRepositoryImpl() {
        this.extent = new LinkedHashSet<>();
    }
    
    @Override
    public boolean add(IExecutionContext item) {
        logger.info("Adding {} to repository", item);
        return extent.add(item);
    }

    @Override
    public boolean remove(IExecutionContext item) {
        logger.info("Removing {} from repository", item);
        return extent.remove(item);
    }
    
    @Override
    public IExecutionContext get(Object id) {
        
        logger.debug("Get ExecutionContext with id:{}", id);
        IExecutionContext  result = null;
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
    
    /** protected region IExecutionContextRepositry on begin **/
    // Specialised search methods
    @Override
	public IExecutionContext getExecutionContextFor(IModelElementVariable... selfVariable) {
		
		@SuppressWarnings("unchecked")
		HashSet<IModelElementVariable> expectedVariables = new HashSet<>(Arrays.asList(selfVariable));
		IExecutionContext result;
		result = extent.stream()
						.filter(ec -> expectedVariables.equals(ec.contextVariables().get()))
						.findFirst()
						.orElseGet(() -> null);
		return result;
	}
    /** protected region IExecutionContextRepositry end **/

}