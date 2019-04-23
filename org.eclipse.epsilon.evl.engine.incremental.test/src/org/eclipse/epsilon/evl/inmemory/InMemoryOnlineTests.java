package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.OnlineTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;

public class InMemoryOnlineTests extends OnlineTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
