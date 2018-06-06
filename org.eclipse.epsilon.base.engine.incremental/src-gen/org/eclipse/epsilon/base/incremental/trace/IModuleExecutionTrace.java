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
 * (e.g. Eol, Etl, etc.) for a given script (identified by the source) and a given set of Models.
 */
public interface IModuleExecutionTrace extends IIdElement {
    
    /**
     * Returns the value of the '{@link ModuleExecutionTrace#source <em>source</em>}' attribute.
     * The path of the source file executed
     * @return the value of the '<em>source</em>' attribute.
     */
    String getSource();

    /**
     * Returns the value of the '<em><b>Module Elements</b></em>' reference.
     * The module elements that conform the module.
     * Each language shoud specialize this class to represent the structure of its AST.
     * @return the value of the '<em>Module Elements</em>' reference.
     */
    IModuleExecutionTraceHasModuleElements moduleElements();
                
    /**
     * Returns the value of the '<em><b>accesses</b></em>' reference.
     * The different accesses that where recorded during execution.
     * @return the value of the '<em>accesses</em>' reference.
     */
    IModuleExecutionTraceHasAccesses accesses();
                
    /**
     * Returns the value of the '<em><b>models</b></em>' reference.
     * The different models involved in the execution
     * @return the value of the '<em>models</em>' reference.
     */
    IModuleExecutionTraceHasModels models();
                
   
    /** The ElementAccess Factory. */
    IElementAccess createElementAccess(IModuleElementTrace executionTrace, IModelElementTrace element) throws EolIncrementalExecutionException;       
            
    /** The AllInstancesAccess Factory. */
    IAllInstancesAccess createAllInstancesAccess(boolean ofKind, IModuleElementTrace executionTrace, IModelTypeTrace type) throws EolIncrementalExecutionException;       
            
    /** The PropertyAccess Factory. */
    IPropertyAccess createPropertyAccess(IModuleElementTrace executionTrace, IPropertyTrace property) throws EolIncrementalExecutionException;       
            
   
    /** The ModelTrace Factory. */
    IModelTrace createModelTrace(String name, String uri) throws EolIncrementalExecutionException;       
   
}
