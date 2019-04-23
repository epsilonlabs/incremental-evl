package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.OfflineTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;

public class InMemoryOfflineTests extends OfflineTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
