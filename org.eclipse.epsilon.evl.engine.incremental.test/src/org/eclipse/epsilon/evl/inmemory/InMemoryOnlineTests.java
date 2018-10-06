package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.OnlineTests;

public class InMemoryOnlineTests extends OnlineTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
