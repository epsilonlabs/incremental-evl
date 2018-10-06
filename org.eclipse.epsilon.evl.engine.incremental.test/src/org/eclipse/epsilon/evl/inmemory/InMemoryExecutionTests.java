package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.ExecutionTests;

public class InMemoryExecutionTests extends ExecutionTests<EvlIncrementalGuiceModule> {

	@Override
	public EvlIncrementalGuiceModule getEvlGuiceModule() {
		return new EvlIncrementalGuiceModule();
	}

}
