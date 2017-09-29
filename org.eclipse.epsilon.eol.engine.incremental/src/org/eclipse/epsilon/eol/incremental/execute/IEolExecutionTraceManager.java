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

import java.util.List;

import org.eclipse.epsilon.eol.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.models.ITraceModel;
import org.eclipse.epsilon.incremental.trace.eol.Trace;

/**
 * The manager holds the reference to the Trace Model, keeps a queue of model changes and flushes the changes
 * when the queue has a specific size, when the user requests a flush or when the ExL module finishes execution.
 * 
 * An IIncrementalExecutionManager is responsible for creating and managing the different trace model elements that
 * are created during execution of an ExL module. The manager works both as a factory to create traces and as a manager
 * to find traces relevant to specific model elements and properties.
 * 
 * Implementations are free to select the preferred persistence technology for the traces, e.g. a database, EMF
 * Resource, etc. Implementations are also free to implement a transaction mechanism.  
 * 
 * FIXME We probably need a manager factory so one manager exists for each run configuration (script+models). 
 * 
 * @author Horacio Hoyos Rodriguez
 */
public interface IEolExecutionTraceManager {
	
	/**
	 * Get the trace model used by this manager
	 * @return
	 */
	ITraceModel getTraceModel();
	
	/**
	 * Set the trace model to be used by this trace manager
	 * @param model
	 */
	void setTraceModel(ITraceModel model);
	
	/**
	 * Instruct the manager to persist the model changes using a parallel execution.
	 * @param inParallel
	 */
	void setParallelPersist(boolean inParallel);
	
	/**
	 * During execution the manager queues the execution trace information. The trace information will be stored in 
	 * the trace model after execution. Alternatively, the user can request that the temporal information be persisted.
	 * Implementations are also free to provide additional strategies, e.g. persist information when the queue reaches
	 * a given size.
	 *
	 * @param moduleElementID the moduleElement ID
	 * @param modelId the model id
	 * @param modelElementId the modelElement id
	 * @param propertyName the property name
	 */
	boolean addTraceInformation(String moduleElementID, String modelId, String modelElementId, String propertyName);
	
	/**
	 * Sets the size of the queue at which point the traces will be persisted in the trace model.
	 */
	void setFlushQueueSize(int size);
	
	/**
	 * Set the period time at which point the traces will be persisted in the trace model.
	 * 
	 * @param period the period, in miliseconds, at which the queue will be persisted
	 */
	void setFlushTimeout(float period);
	
	/**
	 * Makes the trace manager persist any temporal trace information in the trace model.
	 * @return
	 */
	boolean persistTraceInformation();
	
	/**
	 * Set the execution context for this manager.
	 * @param scriptId the id of the ExL Script
	 * @param models the list of models involved in the execution
	 * 
	 * @throws EOLIncrementalExecutionException If there is an exception adding the context to the trace model.
	 */
	void setExecutionContext(String scriptId, List<String> models) throws EOLIncrementalExecutionException;
	
	/**
	 * Creates traces for the properties accessed on a specific model element during the execution of a Epsilon module
	 * element (constraint, rule, etc). To support different Epsilon languages the executed module is represented by an
	 * id. The {@link IIncrementalModule} for the specific ExL language is responsible for creating the ids (see {@link IIncrementalModule}
	 *
	 * @param moduleElementId the module element id
	 * @param elementId the element id
	 * @param properties the properties' name of properties accessed during execution
	 * @return true, if successful
	 */
	//boolean createExecutionTraces(String moduleElementId, String elementId, List<String> properties) throws EOLIncrementalExecutionException;
	
	/**
	 * Creates a trace for the property accessed on a specific model element during the execution of a Epsilon module
	 * element (constraint, rule, etc). To support different Epsilon languages the executed module is represented by an
	 * id. The {@link IIncrementalModule} for the specific ExL language is responsible for creating the ids (see {@link IIncrementalModule}
	 *
	 * @param moduleElementId the module element id
	 * @param elementId the element id
	 * @param properties the properties' name of properties accessed during execution
	 * @return true, if successful. False if a trace for the given property already exists.
	 * @throws EOLIncrementalExecutionException 
	 */
	//boolean createExecutionTrace(String moduleElementId, String elementId, String propertyName) throws EOLIncrementalExecutionException;
	
	/**
	 * Gets the property trace for the given property and element. This allows fine grained incremental execution for
	 * models that can notify changes at the property level.
	 * 
	 * @param objectId the model object id
	 * @param propertyName the property name
	 *
	 * @return the property
	 * @throws EOLIncrementalExecutionException 
	 */
	List<Trace> findExecutionTraces(String objectId, String propertyName) throws EOLIncrementalExecutionException;
	
	/**
	 * Gets the execution traces related to this element. This allows coarse grained incremental execution for
	 * models that can notify changes at the element level.
	 *
	 * @param objectId the model object id
	 * @return the element trace
	 */
	List<Trace> findExecutionTraces(String objectId) throws EOLIncrementalExecutionException;
	
	/**
	 * Remove the trace information related to a specific object. This should be invoked when an element is deleted
	 * from the model.
	 * 
	 * @param objectId the id of the object for which the trace information should be deleted.
	 * @throws EOLIncrementalExecutionException
	 */
	void removeTraceInformation(String objectId) throws EOLIncrementalExecutionException;

}