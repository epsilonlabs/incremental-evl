package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;

public class InMemoryExecutionTests extends ExecutionTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
