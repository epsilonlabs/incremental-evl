 /*******************************************************************************
 * This file was automatically generated on: 2018-05-30.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.util;

import org.eclipse.epsilon.base.incremental.execute.IRepository;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
/** protected region IExecutionContextRepositoryImports on begin **/
/** protected region IExecutionContextRepositoryImports end **/

public interface IExecutionContextRepository extends IRepository<IExecutionContext> {

/** protected region IExecutionContextRepositry on begin **/
    // Specialised search methods
	
	/**
	 * Returns the execution context for the given set of variables
	 * @param selfVariable
	 * @return
	 */
	IExecutionContext getExecutionContextFor(IModelElementVariable... selfVariable);

/** protected region IExecutionContextRepositry end **/

}