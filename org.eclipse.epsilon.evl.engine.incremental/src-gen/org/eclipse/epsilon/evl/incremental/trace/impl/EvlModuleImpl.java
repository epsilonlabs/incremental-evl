 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
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

import org.eclipse.epsilon.evl.incremental.trace.EvlModule;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.ModuleHasModuleElements;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModuleHasModuleElementsImpl;
import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextImpl;

/**
 * Implementation of EvlModule. 
 */
public class EvlModuleImpl implements EvlModule {

    /** The id */
    private Object id;

    /** The source */
    private String source;

    /** The moduleElements relation */
    private final ModuleHasModuleElements moduleElements;

    /**
     * Instantiates a new EvlModule. The EvlModule is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public EvlModuleImpl(String source, ExecutionTrace container) throws TraceModelDuplicateRelation {
        this.source = source;
        this.moduleElements = new ModuleHasModuleElementsImpl(this);
        if (!container.module().create(this)) {
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
    public String getSource() {
        return source;
    }
    
    
    @Override
    public void setSource(String value) {
        this.source = value;
    }   
     
    @Override
    public ModuleHasModuleElements moduleElements() {
        return moduleElements;
    }

    @Override
    public Context createContext() throws EolIncrementalExecutionException {
        Context context = null;
        try {
            context = new ContextImpl(this);
            
            this.moduleElements().create(context);
        } catch (TraceModelDuplicateRelation e) {
            // Pass
        } finally {
    	    if (context != null) {
    	        return context;
    	    }
            try {
                context = this.moduleElements.get().stream()
                    .map(Context.class::cast)
                    .findFirst()
                    .get();
            } catch (NoSuchElementException ex) {
                throw new EolIncrementalExecutionException("Error creating trace model element. Requested Context was "
                        + "duplicate but previous one was not found.");
            }
        }
        return context;
    }      
            
                  
    @Override
    public boolean sameIdentityAs(final EvlModule other) {
        if (other == null) {
            return false;
        }
        if (getSource() == null) {
            if (other.getSource() != null)
                return false;
        } else if (!getSource().equals(other.getSource()))
            return false;
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EvlModuleImpl))
            return false;
        EvlModuleImpl other = (EvlModuleImpl) obj;
        if (!sameIdentityAs(other))
            return false;
        return true; 
  }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

}
