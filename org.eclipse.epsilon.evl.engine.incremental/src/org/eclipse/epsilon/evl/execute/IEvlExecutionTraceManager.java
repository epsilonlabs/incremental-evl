/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.evl.execute;

import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.introspection.recording.SatisfiesInvocationExecutionListener;

public interface IEvlExecutionTraceManager<T> extends IEolExecutionTraceManager<T> {
	
	IContextTraceRepository contextTraces();

	SatisfiesInvocationExecutionListener getSatisfiesListener();

	// FIXME These should go in the ContextTraceRepository
	/**
	 * Gets the property trace for the given property and element. This allows fine grained incremental execution for
	 * models that can notify changes at the property level.
	 * 
	 * @param objectId the model object id
	 * @param propertyName the property name
	 *
	 * @return the property
	 * @throws EolIncrementalExecutionException 
	 */
	//List<ExecutionTrace> findExecutionTraces(String objectId, String propertyName) throws EolIncrementalExecutionException;
	
	/**
	 * Gets the execution traces related to this element. This allows coarse grained incremental execution for
	 * models that can notify changes at the element level.
	 *
	 * @param objectId the model object id
	 * @return the element trace
	 */
	//List<ExecutionTrace> findExecutionTraces(String objectId) throws EolIncrementalExecutionException;
	
	/**
	 * Remove the trace information related to a specific object. This should be invoked when an element is deleted
	 * from the model.
	 * 
	 * @param objectId the id of the object for which the trace information should be deleted.
	 * @throws EolIncrementalExecutionException
	 */
	//void removeTraceInformation(String objectId) throws EolIncrementalExecutionException;
}
