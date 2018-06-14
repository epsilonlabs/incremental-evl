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

import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import java.util.Arrays;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ElementAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;

/**
 * Implementation of IEvlModuleTrace. 
 */
public class EvlModuleTrace implements IEvlModuleTrace {

    /**
	 * The id.
	 */
    private Object id;

    /**
	 * The uri.
	 */
    private String uri;

    /**
     * * The module elements that conform the module.
       * Each language shoud specialize this class to represent the structure of its AST.
     */
    private final IModuleExecutionTraceHasModuleElements moduleElements;

    /**
     * * The different accesses that where recorded during execution.
     */
    private final IModuleExecutionTraceHasAccesses accesses;

    /**
     * * The different models involved in the execution
     */
    private final IModuleExecutionTraceHasModels models;

    /**
     * Instantiates a new EvlModuleTrace. The EvlModuleTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTrace(String uri) throws TraceModelDuplicateRelation {
        this.uri = uri;
        this.moduleElements = new ModuleExecutionTraceHasModuleElements(this);
        this.accesses = new ModuleExecutionTraceHasAccesses(this);
        this.models = new ModuleExecutionTraceHasModels(this);

    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(Object value) {
        this.id = value;
    }   
     
    @Override
    public String getUri() {
        return uri;
    }
    
    @Override
    public IModuleExecutionTraceHasModuleElements moduleElements() {
        return moduleElements;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IModuleExecutionTraceHasModels models() {
        return models;
    }

    @Override
    public IContextTrace createContextTrace(String kind, int index) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        try {
            contextTrace = new ContextTrace(kind, index, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
            try {
                contextTrace = this.moduleElements.get().stream()
                    .filter(t -> t instanceof IContextTrace)
                    .map(IContextTrace.class::cast)
                    .filter(item -> item.getKind() == kind)
                    .filter(item -> item.getIndex() == index)
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return contextTrace;
    }      

                  
    @Override
    public IElementAccess createElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException {
        IElementAccess elementAccess = null;
        try {
            elementAccess = new ElementAccess(executionTrace, element, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (elementAccess != null) {
    	        return elementAccess;
    	    }
            try {
                elementAccess = this.accesses.get().stream()
                    .filter(t -> t instanceof IElementAccess)
                    .map(IElementAccess.class::cast)
                    .filter(item -> item.executionTrace().get().equals(executionTrace))
                    .filter(item -> item.element().get().equals(element))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return elementAccess;
    }      

    @Override
    public IAllInstancesAccess createAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException {
        IAllInstancesAccess allInstancesAccess = null;
        try {
            allInstancesAccess = new AllInstancesAccess(ofKind, executionTrace, type, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (allInstancesAccess != null) {
    	        return allInstancesAccess;
    	    }
            try {
                allInstancesAccess = this.accesses.get().stream()
                    .filter(t -> t instanceof IAllInstancesAccess)
                    .map(IAllInstancesAccess.class::cast)
                    .filter(item -> item.getOfKind() == ofKind)
                    .filter(item -> item.executionTrace().get().equals(executionTrace))
                    .filter(item -> item.type().get().equals(type))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return allInstancesAccess;
    }      

    @Override
    public IPropertyAccess createPropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException {
        IPropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccess(executionTrace, property, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyAccess != null) {
    	        return propertyAccess;
    	    }
            try {
                propertyAccess = this.accesses.get().stream()
                    .filter(t -> t instanceof IPropertyAccess)
                    .map(IPropertyAccess.class::cast)
                    .filter(item -> item.executionTrace().get().equals(executionTrace))
                    .filter(item -> item.property().get().equals(property))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return propertyAccess;
    }      

                  
    @Override
    public IModelTrace createModelTrace(String name, String uri) throws EolIncrementalExecutionException {
        IModelTrace modelTrace = null;
        try {
            modelTrace = new ModelTrace(name, uri, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelTrace != null) {
    	        return modelTrace;
    	    }
            try {
                modelTrace = this.models.get().stream()
                    .filter(item -> item.getName() == name)
                    .filter(item -> item.getUri() == uri)
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IEvlModuleTrace other) {
        if (other == null) {
            return false;
        }
        String uri = getUri();
        String otherUri = other.getUri();
        if (uri == null) {
            if (otherUri != null)
                return false;
        } else if (!uri.equals(otherUri)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EvlModuleTrace))
            return false;
        EvlModuleTrace other = (EvlModuleTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }
}
