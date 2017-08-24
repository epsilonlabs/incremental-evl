/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Jonathan Co   - Initial API and implementation
 *     Horacio Hoyos - Decoupling and abstraction
 *******************************************************************************/
package org.eclipse.epsilon.emc.emf.incremental;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

/**
 * @author astalavista_thes
 *
 */
public class IncrementalEmfModel extends AbstractIncrementalEMFModel implements IIncrementalModel {

	@Override
	Resource getResource() {
		// TODO Auto-generated method stub
		return ((EmfModel)delegate).getResource();
	};

}
