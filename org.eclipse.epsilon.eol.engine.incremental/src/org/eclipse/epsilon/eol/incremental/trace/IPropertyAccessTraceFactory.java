/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace;


// TODO: Auto-generated Javadoc
/**
 * Interface specifying common methods for a factory for {@link IIncrementalTraceManager}s.
 *
 * @author Jonathan Co
 */
public interface IPropertyAccessTraceFactory {

	/**
	 * Gets the trace.
	 *
	 * @return An instance of the {@link IIncrementalTraceManager}.
	 */
	IIncrementalTraceManager getTrace();
	
}
