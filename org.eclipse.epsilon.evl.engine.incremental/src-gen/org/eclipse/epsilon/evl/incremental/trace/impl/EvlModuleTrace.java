 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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
import java.util.Iterator;
import java.util.NoSuchElementException;

/** protected region EvlModuleTraceImports on begin **/
/** protected region EvlModuleTraceImports end **/

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

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
    public IContextTrace getOrCreateContextTrace(String kind, int index) throws EolIncrementalExecutionException {
        IContextTrace contextTrace = null;
        try {
            contextTrace = new ContextTrace(kind, index, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (contextTrace != null) {
    	        return contextTrace;
    	    }
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
    		if (contextTrace == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested ContextTrace was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return contextTrace;
    }      

                  
    @Override
    public IElementAccess getOrCreateElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException {
        IElementAccess elementAccess = null;
        try {
            elementAccess = new ElementAccess(executionTrace, element, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (elementAccess != null) {
    	        return elementAccess;
    	    }
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
    			if (item.executionTrace().get().equals(executionTrace) &&
    			    item.element().get().equals(element)) {
    				elementAccess = item;
    				break;
    			}
    		}
    		if (elementAccess == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested ElementAccess was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return elementAccess;
    }      

    @Override
    public IAllInstancesAccess getOrCreateAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException {
        IAllInstancesAccess allInstancesAccess = null;
        try {
            allInstancesAccess = new AllInstancesAccess(ofKind, executionTrace, type, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (allInstancesAccess != null) {
    	        return allInstancesAccess;
    	    }
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
    			if (item.getOfKind() == ofKind &&
    			    item.executionTrace().get().equals(executionTrace) &&
    			    item.type().get().equals(type)) {
    				allInstancesAccess = item;
    				break;
    			}
    		}
    		if (allInstancesAccess == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested AllInstancesAccess was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return allInstancesAccess;
    }      

    @Override
    public IPropertyAccess getOrCreatePropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException {
        IPropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccess(executionTrace, property, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyAccess != null) {
    	        return propertyAccess;
    	    }
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
    			if (item.executionTrace().get().equals(executionTrace) &&
    			    item.property().get().equals(property)) {
    				propertyAccess = item;
    				break;
    			}
    		}
    		if (propertyAccess == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested PropertyAccess was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return propertyAccess;
    }      

                  
    @Override
    public IModelAccess getOrCreateModelAccess(String modelName, IModelTrace modelTrace) throws EolIncrementalExecutionException {
        IModelAccess modelAccess = null;
        try {
            modelAccess = new ModelAccess(modelName, modelTrace, this);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (modelAccess != null) {
    	        return modelAccess;
    	    }
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
    		if (modelAccess == null) {
               	throw new EolIncrementalExecutionException("Error creating trace model element. Requested ModelAccess was "
                		+ "duplicate but previous one was not found.");
            }
        }
        return modelAccess;
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
