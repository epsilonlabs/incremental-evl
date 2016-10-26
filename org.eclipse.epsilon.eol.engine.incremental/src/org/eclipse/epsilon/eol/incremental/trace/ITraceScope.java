/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Refactoring and decoupling
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace;

// TODO: Auto-generated Javadoc
/**
 * The TraceScope is a relation between the a ModuleElement, a ModelElement, the
 * ElementProperties accessed and the result of the execution of the ModuleElement.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
public interface ITraceScope extends TraceComponent {

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	void setResult(boolean result);
	
	/**
	 * Gets the result.
	 *
	 * @param result the result
	 * @return the result
	 */
	boolean getResult(boolean result);
	
	/**
	 * Get the constraint that is used to evaluate this scope.
	 * 
	 * @return The constraint.
	 */
	IConstraintTrace getConstraint();

	/**
	 * Set the constraint that is used to evaluate this scope.
	 * 
	 * @param constraint
	 *            the new constraint.
	 */
	void setConstraint(IConstraintTrace constraint);

	/**
	 * Get the model element that is used as the root of this scope.
	 *
	 * @return The model element.
	 */
	IModelElement getRootElement();

	/**
	 * Set the model element that is used as the root of this scope.
	 *
	 * @param element            the new model element
	 */
	void setRootElement(IModelElement element);

	/**
	 * Get the properties accessed by this scope.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	Iterable<IElementProperty> getProperties();

	/**
	 * Add a property that is accessed this scope.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this scope.
	 */
	IElementProperty addProperty(IElementProperty property);

	/**
	 * Remove a property that is accessed by this scope.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	void removeProperty(IElementProperty property);

}
