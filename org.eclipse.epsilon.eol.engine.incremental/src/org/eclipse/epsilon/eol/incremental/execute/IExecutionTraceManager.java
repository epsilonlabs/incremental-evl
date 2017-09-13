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

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.engine.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.Trace;


/**
 * An IIncrementalExecutionManager is responsible for creating and managing the different trace model elements that
 * are created during execution of an ExL module. The manager works both as a factory to create traces and as a manager
 * to find traces relevant to specific model elements and properties.
 * 
 * The interface defines methods to signal the manager that execution of an ExL script has started and
 * finished.
 * 
 * Implementations are free to select the preferred persistence technology for the traces, e.g. a database, EMF
 * Resource, etc. Implementations are also free to implement a transaction mechanism.  
 * 
 * FIXME We probably need a manager factory so one manager exists for each run configuration (script+models). 
 * 
 * @author Horacio Hoyos Rodriguez
 */
public interface IExecutionTraceManager {
	
	// FIXME There is more than online-offline...
	// Its more like a "in-memory" or persisted trace model. That is, the trace information is available only during
	// a "validations session" or is available across validation sessions.
	// A validation session starts with the execution of the run configuration. The validation session can be
	// offline or onlince. In offline, the traces are created, but no listeners are attached to the model.
	// In online, listeners are attached to the model and reaction to changes are triggered for the span of a session.
	// How does a online session is ended?
	// Further, in either mode and "full" execution of the script can be requested. .... 
	//  I think I need an activity diagram to understand this better.
	// THis only affects when the trace models are created and dropped, and when they should be loaded/unloaded.
	
	/**
	 * Called to inform the manager that an ExL module is going to be executed.
	 * 
	 * Implementations can use this method, for example, to setup the connection to a db. Furhter, in an online, 
	 * session-only mode, the manager could create a new DB ad set up the required schema information. 
	 * 
	 * @throws EOLIncrementalExecutionException 
	 */
	public void batchExecutionStarted() throws EOLIncrementalExecutionException;
	
	/**
	 * Called to inform the manager that an ExL module has finished executing.
	 * 
	 * Implementations should take into consideration the execution mode. In online mode changes in the model will
	 * be listened after this method has been invoked, so the trace model should be kept "alive" (e.g. mantain the 
	 * connection to the DB). In offline mode, the trace model can be persisted 
	 * 
	 */
	public void batchExecutionFinished()throws EOLIncrementalExecutionException;
	
	/**
	 * This method is called when live changes are detected in the model to prepare the trace manager to access the trace
	 * model. For example, if the DB connection was dropped in the {@link IExecutionTraceManager#batchExecutionFinished()},
	 * then in this method the connection could be reestablished.
	 */
	void eventExecutionStarted() throws EOLIncrementalExecutionException;
	
	/**
	 * This method is called after the model change event has been processed. 
	 */
	void eventExecutionFinished() throws EOLIncrementalExecutionException;
	
	/**
	 * Called to indicate that a live execution has started.
	 * @throws EOLIncrementalExecutionException
	 */
	void liveExecutionStarted() throws EOLIncrementalExecutionException;
	
	/**
	 * Called to indicate that a live execution has finished.
	 * @throws EOLIncrementalExecutionException
	 */
	void liveExecutionFinished() throws EOLIncrementalExecutionException;
	
	
	/**
	 * Set the execution context for this manager.
	 * @param scriptId the id of the ExL Script
	 * @param models the list of models involved in the execution
	 * 
	 * @throws EOLIncrementalExecutionException If there is an exception adding the context to the trace model.
	 */
	public void setExecutionContext(String scriptId, List<String> models) throws EOLIncrementalExecutionException;
	
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
	// FIXME we probably want the list of the ones that could be created so we can retry!
	public boolean createExecutionTraces(String moduleElementId, String elementId, List<String> properties) throws EOLIncrementalExecutionException;
	
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
	public boolean createExecutionTrace(String moduleElementId, String elementId, String propertyName) throws EOLIncrementalExecutionException;
	
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
	public List<Trace> findExecutionTraces(String objectId, String propertyName) throws EOLIncrementalExecutionException;
	
	/**
	 * Gets the execution traces related to this element. This allows coarse grained incremental execution for
	 * models that can notify changes at the element level.
	 *
	 * @param objectId the model object id
	 * @return the element trace
	 */
	public List<Trace> findExecutionTraces(String objectId) throws EOLIncrementalExecutionException;

	/**
	 * Since the configuration parameters are unique for each possible trace manager, the configuration parameters
	 * are passed as an array of strings. Specific implementations can use individual slots or perhaps just a single
	 * connection string. The configuration dialog for the specific manager (defined as an extension to the
	 * AbstractTraceManagerConfigurationDialog in the org.eclipse.epsilon.common.incremental.dt project) can be used
	 * to allow the user to enter/select the connection parameters.
	 * 
	 * This should be the first method invoked in the manager as it opens the db connection, loads the Resource, opens
	 * the text file, etc. 
	 * 
	 * @param properties an array of values to configure the manager.
	 */
	public void configure(StringProperties properties);


}