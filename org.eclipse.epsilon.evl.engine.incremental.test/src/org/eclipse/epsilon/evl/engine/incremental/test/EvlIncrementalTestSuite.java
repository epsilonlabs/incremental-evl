package org.eclipse.epsilon.evl.engine.incremental.test;

import org.eclipse.epsilon.evl.execute.introspection.recording.ExecutionListenerTests;
import org.eclipse.epsilon.evl.incremental.trace.EvlTests;
import org.eclipse.epsilon.evl.inmemory.InMemoryIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@SuiteClasses({ EvlTests.class, ExecutionListenerTests.class, InMemoryIntegrationTests.class })
public class EvlIncrementalTestSuite {

	public static Test suite() {
		return new JUnit4TestAdapter(EvlIncrementalTestSuite.class);
	}

}
