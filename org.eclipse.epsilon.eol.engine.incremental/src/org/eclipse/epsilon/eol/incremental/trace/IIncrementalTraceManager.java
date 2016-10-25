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
package org.eclipse.epsilon.eol.incremental.trace;


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
public interface IIncrementalTraceManager {


	void commit();

	IConstraintTrace createConstraint(String contraintName, String contextName);

	IConstraintTrace createConstraint(String constraintName, IContextTrace context);

	IContextTrace createContext(String contextName);

	IModelElement createElement(String elementId);

	IElementProperty createProperty(String propertyName, String elementId);

	IElementProperty createProperty(String propertyName, IModelElement element);

	ITraceScope createScope(String elementId, String constraintName, String contextName);

	ITraceScope createScope(String elementId, IConstraintTrace constraint);

	ITraceScope createScope(IModelElement element, String constraintName, String contextName);


	ITraceScope createScope(IModelElement element, IConstraintTrace constraint);

	Iterable<IConstraintTrace> getAllConstraints();

	Iterable<IContextTrace> getAllContexts();

	Iterable<IModelElement> getAllElements();

	Iterable<IElementProperty> getAllProperties();

	Iterable<ITraceScope> getAllScopes();

	IConstraintTrace getConstraint(String constraintName, String contextName);

	IConstraintTrace getConstraint(String constraintName, IContextTrace context);

	IContextTrace getContext(String contextName);

	IModelElement getElement(String elementId);


	IElementProperty getProperty(String propertyName, String elementId);

	IElementProperty getProperty(String propertyName, IModelElement element);

	ITraceScope getScope(String elementId, String constraintName, String contextName);

	ITraceScope getScope(String elementId, IConstraintTrace constraint);

	ITraceScope getScope(IModelElement element, String constraintName, String contextName);


	ITraceScope getScope(IModelElement element, IConstraintTrace constraint);

	Iterable<ITraceScope> getScopesOf(IModelElement element);

	Iterable<ITraceScope> getScopesOfId(String elementId);

	/**
	 * @return {@code true} if the underlying database used in the trace graph
	 *         is still in use, {@code false} otherwise.
	 */
	boolean isOpen();

	void removeConstraint(String constraintName, String contextName);

	void removeConstraint(String constraintName, IContextTrace context);

	void removeConstraint(IConstraintTrace constraint);

	void removeContext(String contextName);

	void removeContext(IContextTrace context);

	void removeElement(String elementId);

	void removeElement(IModelElement element);

	void removeProperty(String propertyName, String elementId);

	void removeProperty(String propertyName, IModelElement element);

	void removeProperty(IElementProperty property);

	void removeScope(String elementId, String constraintName, String contextName);
	
	void removeScope(String elementId, IConstraintTrace constraint);
	void removeScope(IModelElement element, String constraintName, String contextName);


	void removeScope(IModelElement element, IConstraintTrace constraint);

	void removeScope(ITraceScope scope);

	/**
	 * Shutdown the underlying database.
	 */
	void shutdown();


}
