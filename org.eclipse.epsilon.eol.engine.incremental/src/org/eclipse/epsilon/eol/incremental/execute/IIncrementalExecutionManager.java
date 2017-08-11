/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.execute;

import java.util.Collection;


/**
 * An IIncrementalExecutionManager is responsible for creating and managing the different trace model elements that
 * are created during execution of an ExL module. The manager works both as a factory to create traces and as a manager
 * to manage the traces. The interface defines methods to signal the manager that execution of an ExL script has started and
 * finished.
 * 
 * Implementations are free to select the preferred persistence technology for the traces, e.g. a database, EMF
 * Resource, etc. Implementations are also free to implement a transaction mechanism.  
 * 
 * FIXME We probably need a manager factory so one manager exists for each run configuration (script+models). 
 * 
 * @author Horacio Hoyos
 */
public interface IIncrementalExecutionManager {
	
	/**
	 * Called to inform the manager that an ExL module or module element is going to be executed.
	 */
	public void startManager();
	
	/**
	 * Called to inform the manager that an ExL module or module element has finished executing
	 */
	public void stopManager();
	
	
	/**
	 * Adds a trace for the properties accessed on a specific model element during the execution of a Epsilon module
	 * element (constraint, rule, etc). To support different Epsilon languages the executed module is represented by an
	 * id. For example, in EVL this id could be &lt;context&gt;:&lt;constraint&gt; and in ETL &lt;context&gt;:&lt;ruleName&gt;
	 *
	 * @param moduleElementId the module element id
	 * @param elementId the element id
	 * @param properties the properties' name of properties accessed during execution
	 * @param result True if the traces where added
	 * @return true, if successful
	 */
	public boolean addTraces(String moduleElementId, String elementId, Collection<String> properties, Boolean result);
	
	
	/**
	 * Gets the property trace for the given property and element.
	 *
	 * @param propertyName the property name
	 * @param elementId the element id
	 * @return the property
	 */
	// FIXME can ther be multiple traces per property?
	// FIXME why object? it must be a more specific class/interface.
	public Object getPropertyTrace(String propertyName, String elementId);
	
	
	/**
	 * Gets the element trace.
	 *
	 * @param elementId the element id
	 * @return the element trace
	 */
	public Object getElementTrace(String elementId);

}