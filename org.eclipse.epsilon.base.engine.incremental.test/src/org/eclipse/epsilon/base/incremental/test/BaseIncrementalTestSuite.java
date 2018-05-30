package org.eclipse.epsilon.base.incremental.test;

import org.eclipse.epsilon.base.incremental.execute.introspection.recording.ExecutionListenerTests;
import org.eclipse.epsilon.base.incremental.trace.BaseTraceModelTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@SuiteClasses({	BaseTraceModelTests.class,
				ExecutionListenerTests.class})
public class BaseIncrementalTestSuite {
	
	public static Test suite() {
		return new JUnit4TestAdapter(BaseIncrementalTestSuite.class);
	}

}
