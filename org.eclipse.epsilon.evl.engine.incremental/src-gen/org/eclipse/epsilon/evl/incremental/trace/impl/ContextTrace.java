 /*******************************************************************************
 * This file was automatically generated on: 2017-11-21.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleElementTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.ElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.impl.PropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasContext;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTraceHasContext;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementTraceHasGuard;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTrace;

/**
 * Implementation of IContextTrace. 
 */
public class ContextTrace implements IContextTrace {

    /** The id */
    private Object id;

    /** The kind */
    private String kind;

    /** The guard relation */
    private final IGuardedElementTraceHasGuard guard;

    /** The accesses relation */
    private final IExecutionTraceHasAccesses accesses;

    /** The module relation */
    private final IModuleElementTraceHasModule module;

    /** The constraints relation */
    private final IContextTraceHasConstraints constraints;

    /** The context relation */
    private final IContextTraceHasContext context;

    /**
     * Instantiates a new ContextTrace. The ContextTrace is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public ContextTrace(String kind, IModuleExecution container) throws TraceModelDuplicateRelation {
        this.kind = kind;
        this.guard = new GuardedElementTraceHasGuard(this);
        this.accesses = new ExecutionTraceHasAccesses(this);
        this.module = new ModuleElementTraceHasModule(this);
        this.constraints = new ContextTraceHasConstraints(this);
        this.context = new ContextTraceHasContext(this);
        if (!container.moduleElements().create(this)) {
            throw new TraceModelDuplicateRelation();
        };
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
    public String getKind() {
        return kind;
    }
    
    
    @Override
    public void setKind(String value) {
        this.kind = value;
    }   
     
    @Override
    public IGuardedElementTraceHasGuard guard() {
        return guard;
    }

    @Override
    public IExecutionTraceHasAccesses accesses() {
        return accesses;
    }

    @Override
    public IModuleElementTraceHasModule module() {
        return module;
    }

    @Override
    public IContextTraceHasConstraints constraints() {
        return constraints;
    }

    @Override
    public IContextTraceHasContext context() {
        return context;
    }

    @Override
    public IGuardTrace createGuardTrace() throws EolIncrementalExecutionException {
        IGuardTrace guardTrace = null;
        try {
            guardTrace = new GuardTrace(this);
            
            this.guard().create(guardTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (guardTrace != null) {
    	        return guardTrace;
    	    }
            guardTrace = this.guard.get();
            if (guardTrace  == null) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested GuardTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return guardTrace;
    }      
                  
    @Override
    public IAllInstancesAccess createAllInstancesAccess(IModelTypeTrace type) throws EolIncrementalExecutionException {
        IAllInstancesAccess allInstancesAccess = null;
        try {
            allInstancesAccess = new AllInstancesAccess(type, this);
            
            this.accesses().create(allInstancesAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (allInstancesAccess != null) {
    	        return allInstancesAccess;
    	    }
            try {
                allInstancesAccess = this.accesses.get().stream()
                    .map(AllInstancesAccess.class::cast)
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
    public IElementAccess createElementAccess(IModelElementTrace modelElement) throws EolIncrementalExecutionException {
        IElementAccess elementAccess = null;
        try {
            elementAccess = new ElementAccess(modelElement, this);
            
            this.accesses().create(elementAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (elementAccess != null) {
    	        return elementAccess;
    	    }
            try {
                elementAccess = this.accesses.get().stream()
                    .map(ElementAccess.class::cast)
                    .filter(item -> item.modelElement().get().equals(modelElement))
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
    public IPropertyAccess createPropertyAccess(IModelElementTrace modelElement, IPropertyTrace property) throws EolIncrementalExecutionException {
        IPropertyAccess propertyAccess = null;
        try {
            propertyAccess = new PropertyAccess(modelElement, property, this);
            
            this.accesses().create(propertyAccess);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (propertyAccess != null) {
    	        return propertyAccess;
    	    }
            try {
                propertyAccess = this.accesses.get().stream()
                    .map(PropertyAccess.class::cast)
                    .filter(item -> item.modelElement().get().equals(modelElement))
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
    public IInvariantTrace createInvariantTrace(String name) throws EolIncrementalExecutionException {
        IInvariantTrace invariantTrace = null;
        try {
            invariantTrace = new InvariantTrace(name, this);
            
            this.constraints().create(invariantTrace);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (invariantTrace != null) {
    	        return invariantTrace;
    	    }
            try {
                invariantTrace = this.constraints.get().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested InvariantTrace was "
                        + "duplicate but previous one was not found.");
            }
        }
        return invariantTrace;
    }      
                  
    @Override
    public boolean sameIdentityAs(final IContextTrace other) {
        if (other == null) {
            return false;
        }
        if (getKind() == null) {
            if (other.getKind() != null)
                return false;
        } else if (!getKind().equals(other.getKind()))
            return false;
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ContextTrace))
            return false;
        ContextTrace other = (ContextTrace) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kind == null) ? 0 : kind.hashCode());
        return result;
    }

}
