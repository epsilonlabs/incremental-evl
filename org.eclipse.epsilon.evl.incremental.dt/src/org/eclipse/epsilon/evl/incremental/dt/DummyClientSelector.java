package org.eclipse.epsilon.evl.incremental.dt;

import org.eclipse.emf.validation.model.IClientSelector;

public class DummyClientSelector implements IClientSelector {

	@Override
	public boolean selects(Object object) {
		return true;
	}

}
