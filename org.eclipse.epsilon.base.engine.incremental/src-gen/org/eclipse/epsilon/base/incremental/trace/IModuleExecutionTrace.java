 /*******************************************************************************
 * This file was automatically generated on: 2018-05-31.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;    
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;    
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;    
import org.eclipse.epsilon.base.incremental.trace.IInContextModuleElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;    
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;    
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;    
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;    

/**
 * A ModuleExecutionTrace represents the trace of the execution of a particular Epsilon module
   (e.g. Eol, Etl, etc.) for a given script (identified by the source) and a given set of Models.
 
 */
public interface IModuleExecutionTrace extends IIdElement {

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute.
     * <!-- protected region source-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region source-getter-doc end --> 
     * @return the value of the '<em>Source</em>' attribute.
     */
    String getSource();            

    /** The moduleElements reference. */
    IModuleExecutionTraceHasModuleElements moduleElements();
                
    /** The accesses reference. */
    IModuleExecutionTraceHasAccesses accesses();
                
    /** The models reference. */
    IModuleExecutionTraceHasModels models();
                
   
    /** The ElementAccess Factory. */
    IElementAccess createElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException;       
            
    /** The AllInstancesAccess Factory. */
    IAllInstancesAccess createAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException;       
            
    /** The PropertyAccess Factory. */
    IPropertyAccess createPropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException;       
            
   
    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name) throws EolIncrementalExecutionException;       
   
}
