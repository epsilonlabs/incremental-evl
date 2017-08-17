/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - Refactoring and decoupling
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

/**
 * A trace manager provides the API for managing traces in the trace model. This includes creation, query and deletion
 * of trace information. There are 4 basic elements used for storing trace information:
 * <ul>
 *	<li> ModuleElement: The block of a module that generated the trace during execution
 * 	<li> ModelElement: The model element that was accessed during execution
 * 	<li> ElementProperty: The property of a model element that was accessed during execution
 * 	<li> TraceScope: A scope is a trace relation between the a ModuleElement, a ModelElement, the ElementProperties
 * 		 accessed and the result of the execution.
 * </ul>
 * <p>
 * The Trace manager is also responsible for the life cycle of the trace model. 
 *  
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
@Deprecated
public interface IIncrementalTraceManager {


	/**
	 * Commit.
	 */
	void commit();

	/**
	 * Creates the constraint.
	 *
	 * @param contraintName the contraint name
	 * @param contextName the context name
	 * @return the i constraint trace
	 */
	IConstraintTrace createConstraint(String contraintName, String contextName);

	/**
	 * Creates the constraint.
	 *
	 * @param constraintName the constraint name
	 * @param context the context
	 * @return the i constraint trace
	 */
	IConstraintTrace createConstraint(String constraintName, IContextTrace context);

	/**
	 * Creates the context.
	 *
	 * @param contextName the context name
	 * @return the i context trace
	 */
	IContextTrace createContext(String contextName);

	/**
	 * Creates the element.
	 *
	 * @param elementId the element id
	 * @return the i model element
	 */
	IModelElement createElement(String elementId);

	/**
	 * Creates the property.
	 *
	 * @param propertyName the property name
	 * @param elementId the element id
	 * @return the i element property
	 */
	IElementProperty createProperty(String propertyName, String elementId);

	/**
	 * Creates the property.
	 *
	 * @param propertyName the property name
	 * @param element the element
	 * @return the i element property
	 */
	IElementProperty createProperty(String propertyName, IModelElement element);

	/**
	 * Creates the scope.
	 *
	 * @param elementId the element id
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 * @return the i trace scope
	 */
	IExecutionTrace createScope(String elementId, String constraintName, String contextName);

	/**
	 * Creates the scope.
	 *
	 * @param elementId the element id
	 * @param constraint the constraint
	 * @return the i trace scope
	 */
	IExecutionTrace createScope(String elementId, IConstraintTrace constraint);

	/**
	 * Creates the scope.
	 *
	 * @param element the element
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 * @return the i trace scope
	 */
	IExecutionTrace createScope(IModelElement element, String constraintName, String contextName);


	/**
	 * Creates the scope.
	 *
	 * @param element the element
	 * @param constraint the constraint
	 * @return the i trace scope
	 */
	IExecutionTrace createScope(IModelElement element, IConstraintTrace constraint);

	/**
	 * Gets the all constraints.
	 *
	 * @return the all constraints
	 */
	Iterable<IConstraintTrace> getAllConstraints();

	/**
	 * Gets the all contexts.
	 *
	 * @return the all contexts
	 */
	Iterable<IContextTrace> getAllContexts();

	/**
	 * Gets the all elements.
	 *
	 * @return the all elements
	 */
	Iterable<IModelElement> getAllElements();

	/**
	 * Gets the all properties.
	 *
	 * @return the all properties
	 */
	Iterable<IElementProperty> getAllProperties();

	/**
	 * Gets the all scopes.
	 *
	 * @return the all scopes
	 */
	Iterable<IExecutionTrace> getAllScopes();

	/**
	 * Gets the constraint.
	 *
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 * @return the constraint
	 */
	IConstraintTrace getConstraint(String constraintName, String contextName);

	/**
	 * Gets the constraint.
	 *
	 * @param constraintName the constraint name
	 * @param context the context
	 * @return the constraint
	 */
	IConstraintTrace getConstraint(String constraintName, IContextTrace context);

	/**
	 * Gets the context.
	 *
	 * @param contextName the context name
	 * @return the context
	 */
	IContextTrace getContext(String contextName);

	/**
	 * Gets the element.
	 *
	 * @param elementId the element id
	 * @return the element
	 */
	IModelElement getElement(String elementId);


	/**
	 * Gets the property.
	 *
	 * @param propertyName the property name
	 * @param elementId the element id
	 * @return the property
	 */
	IElementProperty getProperty(String propertyName, String elementId);

	/**
	 * Gets the property.
	 *
	 * @param propertyName the property name
	 * @param element the element
	 * @return the property
	 */
	IElementProperty getProperty(String propertyName, IModelElement element);

	/**
	 * Gets the scope.
	 *
	 * @param elementId the element id
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 * @return the scope
	 */
	IExecutionTrace getScope(String elementId, String constraintName, String contextName);

	/**
	 * Gets the scope.
	 *
	 * @param elementId the element id
	 * @param constraint the constraint
	 * @return the scope
	 */
	IExecutionTrace getScope(String elementId, IConstraintTrace constraint);

	/**
	 * Gets the scope.
	 *
	 * @param element the element
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 * @return the scope
	 */
	IExecutionTrace getScope(IModelElement element, String constraintName, String contextName);


	/**
	 * Gets the scope.
	 *
	 * @param element the element
	 * @param constraint the constraint
	 * @return the scope
	 */
	IExecutionTrace getScope(IModelElement element, IConstraintTrace constraint);

	/**
	 * Gets the scopes of.
	 *
	 * @param element the element
	 * @return the scopes of
	 */
	Iterable<IExecutionTrace> getScopesOf(IModelElement element);

	/**
	 * Gets the scopes of id.
	 *
	 * @param elementId the element id
	 * @return the scopes of id
	 */
	Iterable<IExecutionTrace> getScopesOfId(String elementId);

	/**
	 * Checks if is open.
	 *
	 * @return {@code true} if the underlying database used in the trace graph
	 *         is still in use, {@code false} otherwise.
	 */
	boolean isOpen();

	/**
	 * Removes the constraint.
	 *
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 */
	void removeConstraint(String constraintName, String contextName);

	/**
	 * Removes the constraint.
	 *
	 * @param constraintName the constraint name
	 * @param context the context
	 */
	void removeConstraint(String constraintName, IContextTrace context);

	/**
	 * Removes the constraint.
	 *
	 * @param constraint the constraint
	 */
	void removeConstraint(IConstraintTrace constraint);

	/**
	 * Removes the context.
	 *
	 * @param contextName the context name
	 */
	void removeContext(String contextName);

	/**
	 * Removes the context.
	 *
	 * @param context the context
	 */
	void removeContext(IContextTrace context);

	/**
	 * Removes the element.
	 *
	 * @param elementId the element id
	 */
	void removeElement(String elementId);

	/**
	 * Removes the element.
	 *
	 * @param element the element
	 */
	void removeElement(IModelElement element);

	/**
	 * Removes the property.
	 *
	 * @param propertyName the property name
	 * @param elementId the element id
	 */
	void removeProperty(String propertyName, String elementId);

	/**
	 * Removes the property.
	 *
	 * @param propertyName the property name
	 * @param element the element
	 */
	void removeProperty(String propertyName, IModelElement element);

	/**
	 * Removes the property.
	 *
	 * @param property the property
	 */
	void removeProperty(IElementProperty property);

	/**
	 * Removes the scope.
	 *
	 * @param elementId the element id
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 */
	void removeScope(String elementId, String constraintName, String contextName);
	
	/**
	 * Removes the scope.
	 *
	 * @param elementId the element id
	 * @param constraint the constraint
	 */
	void removeScope(String elementId, IConstraintTrace constraint);
	
	/**
	 * Removes the scope.
	 *
	 * @param element the element
	 * @param constraintName the constraint name
	 * @param contextName the context name
	 */
	void removeScope(IModelElement element, String constraintName, String contextName);


	/**
	 * Removes the scope.
	 *
	 * @param element the element
	 * @param constraint the constraint
	 */
	void removeScope(IModelElement element, IConstraintTrace constraint);

	/**
	 * Removes the scope.
	 *
	 * @param scope the scope
	 */
	void removeScope(IExecutionTrace scope);

	/**
	 * Shutdown the underlying database.
	 */
	void shutdown();


}
