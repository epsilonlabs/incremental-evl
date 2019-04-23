package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.HashTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;

public class InMemoryHashTests extends HashTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
