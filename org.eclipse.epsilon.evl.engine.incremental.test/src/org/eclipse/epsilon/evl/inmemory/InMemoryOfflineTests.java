package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.OfflineTests;

public class InMemoryOfflineTests extends OfflineTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
