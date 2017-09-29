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
package org.eclipse.epsilon.emc.emf.online;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

public class OnlineEmfModel extends AbstractIncrementalEMFModel implements IIncrementalModel {
	
	@Override
	Resource getResource() {
		return ((InMemoryEmfModel)delegate).getResource();
	}

	
}
