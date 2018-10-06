package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.HashTests;

public class InMemoryHashTests extends HashTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
