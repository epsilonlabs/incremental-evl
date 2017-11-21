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
package org.eclipse.epsilon.eol.incremental.trace;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;    
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;    
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;    

/**
 * The ExecutionTrace defines the access methods for the EClass features.
 * Additionally, the IExecutionTrace acts as the root entity of the AGGREGATE of its
 * container references. That is, elements contained in the ExecutionTrace must be
 * created through this interface.
 */
public interface IExecutionTrace extends IIdElement {

    /** The accesses reference. */
    IExecutionTraceHasAccesses accesses();
                
    /** The AllInstancesAccess Factory. */
    IAllInstancesAccess createAllInstancesAccess(IModelTypeTrace type) throws EolIncrementalExecutionException;       
            
    /** The ElementAccess Factory. */
    IElementAccess createElementAccess(IModelElementTrace modelElement) throws EolIncrementalExecutionException;       
            
    /** The PropertyAccess Factory. */
    IPropertyAccess createPropertyAccess(IModelElementTrace modelElement, IPropertyTrace property) throws EolIncrementalExecutionException;       
            
   
}
