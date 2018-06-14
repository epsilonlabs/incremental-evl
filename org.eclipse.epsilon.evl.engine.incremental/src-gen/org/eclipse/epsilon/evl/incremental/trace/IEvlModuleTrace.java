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
package org.eclipse.epsilon.evl.incremental.trace;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;    
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;    
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;    
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;    
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;    
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;    

/**
 * A ModuleExecutionTrace represents the trace of the execution of a particular Epsilon module
 * (e.g. Eol, Etl, etc.) for a given script (identified by the source) and a given set of Models.
 */
public interface IEvlModuleTrace extends IModuleExecutionTrace {

 
    /**
     * EvlModuleTrace has same identity in the aggregate.
     */
    public boolean sameIdentityAs(final IEvlModuleTrace other);
    
    /** The ContextTrace Factory. */
    IContextTrace createContextTrace(String kind, int index) throws EolIncrementalExecutionException;       

    /** The ElementAccess Factory. */
    IElementAccess createElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException;       

    /** The AllInstancesAccess Factory. */
    IAllInstancesAccess createAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException;       

    /** The PropertyAccess Factory. */
    IPropertyAccess createPropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException;       

    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name, String uri) throws EolIncrementalExecutionException;       

}
