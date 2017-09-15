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
package org.eclipse.epsilon.eol.incremental;


public class EOLIncrementalExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474163903079549097L;

	
	public EOLIncrementalExecutionException(String string, Exception e) {
		super(string, e);
	}


	public EOLIncrementalExecutionException(String string) {
		super(string);
	}

	
}
