 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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

import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * Implementation of IEvlModuleTrace. 
 */
@SuppressWarnings("unused") 
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
     * * The different models involved in the execution
     */
    private final IModuleExecutionTraceHasModels models;

    /**
     * * The different accesses that where recorded during execution.
     */
    private final IModuleExecutionTraceHasAccesses accesses;

    /**
     * Instantiates a new EvlModuleTrace. The EvlModuleTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation {
        this.uri = uri;

        this.moduleElements = new ModuleExecutionTraceHasModuleElements(this);
        this.models = new ModuleExecutionTraceHasModels(this);
        this.accesses = new ModuleExecutionTraceHasAccesses(this);

    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(java.lang.Object value) {
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
    public IModuleExecutionTraceHasModels models() {
        return models;
    }

    @Override
    public IModuleExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IContextTrace getOrCreateContextTrace(String kind, Integer index) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        
        try {
            contextTrace = new ContextTrace(kind, index, this);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (contextTrace == null) {
                // Find the matching element
                Iterator<IModuleElementTrace> it = this.moduleElements.get();
                while (it.hasNext()) {
                    IContextTrace item;
                    IModuleElementTrace t = it.next();
                    if (t instanceof IContextTrace) {
                        item = (IContextTrace) t;
                    }
                    else {
                        continue;
                    }
        	        if (item.getKind().equals(kind) &&
        	            item.getIndex() == index) {
        	            contextTrace = item;
        	            break;
        	        }
                }
            }
            if (contextTrace == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return contextTrace;
    }      
        
                  
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        IModelAccess modelAccess = null;
        
        try {
            modelAccess = new ModelAccess(modelName, modelTrace, this);
        } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
            // Pass
        } finally {
            if (modelAccess == null) {
                // Find the matching element
                Iterator<IModelAccess> it = this.models.get();
                while (it.hasNext()) {
                    IModelAccess item;
                    item = (IModelAccess) it.next();
        	        if (item.getModelName().equals(modelName) &&
        	            item.modelTrace().get().equals(modelTrace)) {
        	            modelAccess = item;
        	            break;
        	        }
                }
            }
            if (modelAccess == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelAccess was "
                        + "duplicate but previous one was not found.");
            }
        }
        return modelAccess;
    }      
                  
    @SuppressWarnings("unchecked")
    public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException {
        A result = null;
        switch(accessClass.getName()) {
            case "SatisfiesAccess":
                assert args[1] instanceof IModuleElementTrace;
                assert args[2] instanceof IExecutionContext;
                assert args[3] instanceof IModelElementTrace;
                ISatisfiesAccess satisfiesAccess = null;
                
                try {
                    satisfiesAccess = new SatisfiesAccess((IModuleElementTrace) args[0], (IExecutionContext) args[1], (IModelElementTrace) args[2], this);
                    this.accesses().create(satisfiesAccess);
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
                    // Pass
                } finally {
                    if (satisfiesAccess == null) {
                        // Find the matching element
                        Iterator<IAccess> it = this.accesses.get();
                        while (it.hasNext()) {
                            ISatisfiesAccess item;
                            IAccess t = it.next();
                            if (t instanceof ISatisfiesAccess) {
                                item = (ISatisfiesAccess) t;
                            }
                            else {
                                continue;
                            }
                	        if (item.from().get().equals(args[1]) &&
                	            item.in().get().equals(args[2]) &&
                	            item.modelElement().get().equals(args[3])) {
                	            satisfiesAccess = item;
                	            break;
                	        }
                        }
                    }
                    if (satisfiesAccess == null) {
                        throw new EolIncrementalExecutionException("Error creating trace model element. Requested SatisfiesAccess was "
                                + "duplicate but previous one was not found.");
                    }
                }
                result = (A) satisfiesAccess;
                break;
            case "ElementAccess":
                assert args[1] instanceof IModuleElementTrace;
                assert args[2] instanceof IExecutionContext;
                assert args[3] instanceof IModelElementTrace;
                IElementAccess elementAccess = null;
                
                try {
                    elementAccess = new ElementAccess((IModuleElementTrace) args[0], (IExecutionContext) args[1], (IModelElementTrace) args[2], this);
                    this.accesses().create(elementAccess);
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
                    // Pass
                } finally {
                    if (elementAccess == null) {
                        // Find the matching element
                        Iterator<IAccess> it = this.accesses.get();
                        while (it.hasNext()) {
                            IElementAccess item;
                            IAccess t = it.next();
                            if (t instanceof IElementAccess) {
                                item = (IElementAccess) t;
                            }
                            else {
                                continue;
                            }
                	        if (item.from().get().equals(args[1]) &&
                	            item.in().get().equals(args[2]) &&
                	            item.element().get().equals(args[3])) {
                	            elementAccess = item;
                	            break;
                	        }
                        }
                    }
                    if (elementAccess == null) {
                        throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
                                + "duplicate but previous one was not found.");
                    }
                }
                result = (A) elementAccess;
                break;
            case "AllInstancesAccess":
                assert args[1] instanceof Boolean;
                assert args[2] instanceof IModuleElementTrace;
                assert args[3] instanceof IExecutionContext;
                assert args[4] instanceof IModelTypeTrace;
                IAllInstancesAccess allInstancesAccess = null;
                
                try {
                    allInstancesAccess = new AllInstancesAccess((Boolean) args[0], (IModuleElementTrace) args[1], (IExecutionContext) args[2], (IModelTypeTrace) args[3], this);
                    this.accesses().create(allInstancesAccess);
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
                    // Pass
                } finally {
                    if (allInstancesAccess == null) {
                        // Find the matching element
                        Iterator<IAccess> it = this.accesses.get();
                        while (it.hasNext()) {
                            IAllInstancesAccess item;
                            IAccess t = it.next();
                            if (t instanceof IAllInstancesAccess) {
                                item = (IAllInstancesAccess) t;
                            }
                            else {
                                continue;
                            }
                	        if (item.getOfKind() == args[1] &&
                	            item.from().get().equals(args[2]) &&
                	            item.in().get().equals(args[3]) &&
                	            item.type().get().equals(args[4])) {
                	            allInstancesAccess = item;
                	            break;
                	        }
                        }
                    }
                    if (allInstancesAccess == null) {
                        throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
                                + "duplicate but previous one was not found.");
                    }
                }
                result = (A) allInstancesAccess;
                break;
            case "PropertyAccess":
                assert args[1] instanceof IModuleElementTrace;
                assert args[2] instanceof IExecutionContext;
                assert args[3] instanceof IPropertyTrace;
                IPropertyAccess propertyAccess = null;
                
                try {
                    propertyAccess = new PropertyAccess((IModuleElementTrace) args[0], (IExecutionContext) args[1], (IPropertyTrace) args[2], this);
                    this.accesses().create(propertyAccess);
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation  e) {
                    // Pass
                } finally {
                    if (propertyAccess == null) {
                        // Find the matching element
                        Iterator<IAccess> it = this.accesses.get();
                        while (it.hasNext()) {
                            IPropertyAccess item;
                            IAccess t = it.next();
                            if (t instanceof IPropertyAccess) {
                                item = (IPropertyAccess) t;
                            }
                            else {
                                continue;
                            }
                	        if (item.from().get().equals(args[1]) &&
                	            item.in().get().equals(args[2]) &&
                	            item.property().get().equals(args[3])) {
                	            propertyAccess = item;
                	            break;
                	        }
                        }
                    }
                    if (propertyAccess == null) {
                        throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
                                + "duplicate but previous one was not found.");
                    }
                }
                result = (A) propertyAccess;
                break;
        }
        return result;
    }
                  
    public Map<String,Object> getIdProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("uri", getUri());
        return result;
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
