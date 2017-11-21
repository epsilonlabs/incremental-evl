package org.eclipse.epsilon.eol.engine.incremental.test;

import org.eclipse.epsilon.eol.incremental.trace.EolTraceModelTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@SuiteClasses({EolTraceModelTests.class})
public class EolIncrementalTestSuite {
	
	public static Test suite() {
		return new JUnit4TestAdapter(EolIncrementalTestSuite.class);
	}

}
