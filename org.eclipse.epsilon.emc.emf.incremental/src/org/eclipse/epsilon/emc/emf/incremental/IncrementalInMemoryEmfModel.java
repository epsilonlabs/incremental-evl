package org.eclipse.epsilon.emc.emf.incremental;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

public class IncrementalInMemoryEmfModel extends AbstractIncrementalEMFModel implements IIncrementalModel {
	
	
	@Override
	Resource getResource() {
		return ((InMemoryEmfModel)delegate).getResource();
	}

	
}
