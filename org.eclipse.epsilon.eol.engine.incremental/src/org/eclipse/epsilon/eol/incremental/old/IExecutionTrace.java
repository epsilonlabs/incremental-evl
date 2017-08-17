/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Refactoring and Consolidation
 *     
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.common.module.ModuleElement;

/**
 * The Execution Trace is a relation between a ModuleElement, a ModelElement, and the ElementProperties accessed.
 * 
 * There is a unique execution trace for each set tuple of (ModuleElement, List<ModelElement>), that is, the execution
 * trace record the information of all model elements that are accessed during execution of the module element.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
public interface IExecutionTrace extends ITraceComponent {

	
	/**
	 * Get the {@link ModuleElement} that is associated to this trace.
	 * @return
	 */
	IModuleElement getModuleElement();
	
	/**
	 * Set the {@link ModuleElement} that is associated to this trace.
	 * @param element
	 */
	void setModuleElement(IModuleElement element);
	
	
	/**
	 * Get the model elements that is associated to this trace.
	 *
	 * @return The model element.
	 */
	List<IModelElement> getModelElements();

	/**
	 * Add a model element that was accessed by this trace.
	 * @param executionTrace
	 * @return
	 */
	IModelElement addModelElementTrace(IModelElement modelElement);
	
	/**
	 * Remove a model element that was accessed by this trace.
	 * @param executionTrace
	 */
	void removeModelElementTrace(IModelElement modelElement);

	/**
	 * Get the properties associated to this trace, that is, the associated model element's properties that where accessed
	 * during the execution of the associated module element.
	 * 
	 * @return An {@link Collection} containing the associated properties.
	 */
	List<IElementProperty> getElementProperties();
	
	/**
	 * Add a model element's properties that was accessed during the execution of the associated module element.
	 * 
	 * @param elementProperty The accessed property
	 */
	IElementProperty addElementPropertyTrace(IElementProperty elementProperty);
	
	/**
	 * Remove a model element's properties that was accessed during the execution of the associated module element.
	 * 
	 * @param elementProperty The accessed property
	 */
	void removeElementPropertyTrace(IElementProperty elementProperty);

}
 