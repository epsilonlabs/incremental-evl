 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
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
/** protected region ModelTraceRepositoryImplImports on begin **/
import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
/** protected region ModelTraceRepositoryImplImports end **/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelTraceRepositoryImpl implements IModelTraceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ModelTraceRepositoryImpl.class);
 
    protected final Set<IModelTrace> extent;    
    
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
    public void dispose() {
        this.extent.clear();
    }
    
    
    @Override
    public IModelTrace get(Object id) {
        
        logger.debug("Get ModelTrace with id:{}", id);
        IModelTrace  result = null;
        try {
            result = (IModelTrace)extent.stream()
                    .filter(item -> item.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException  e) {
            // No info about the ModelTrace
        }
        return result;
    }
    
    /** protected region IModelTraceRepositry on begin **/
	@Override
	public IModelTrace getModelTraceByIdentity(String modelUri) {
		return extent.stream().filter(mt -> mt.getUri() == modelUri).findFirst().orElse(null);
	}

	@Override
	public IModelTypeTrace getTypeTraceFor(String modelUri, String typeName) {
		IModelTrace modelTrace = getModelTraceByIdentity(modelUri);
		Iterator<IModelTypeTrace> iterator = modelTrace.types().get();
		while (iterator.hasNext()) {
			IModelTypeTrace tt = iterator.next();
			if (tt.getName() == typeName) {
				return tt;
			}
		}
		return null;
	}

	@Override
	public IModelElementTrace getModelElementTraceFor(String modelUri, String modelElementUri) {
		IModelTrace modelTrace = getModelTraceByIdentity(modelUri);
		Iterator<IModelElementTrace> iterator = modelTrace.elements().get();
		while (iterator.hasNext()) {
			IModelElementTrace et = iterator.next();
			if (et.getUri().equals(modelElementUri)) {
				return et;
			}
		}
		return null;
	}

	@Override
	public IPropertyTrace getPropertyTraceFor(String modelUri, String elementId, String propertyName) {
		IModelElementTrace modelElementTrace = getModelElementTraceFor(modelUri, elementId);
		Iterator<IPropertyTrace> iterator = modelElementTrace.properties().get();
		while (iterator.hasNext()) {
			IPropertyTrace pt = iterator.next();
			if (pt.getName() == propertyName) {
				return pt;
			}
		}
		return null;
	}

	/** protected region IModelTraceRepositry end **/

}