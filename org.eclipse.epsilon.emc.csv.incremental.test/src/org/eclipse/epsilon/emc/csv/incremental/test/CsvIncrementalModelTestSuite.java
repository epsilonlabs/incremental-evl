package org.eclipse.epsilon.emc.csv.incremental.test;

import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncrementalTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@SuiteClasses({CsvModelIncrementalTests.class})
public class CsvIncrementalModelTestSuite {

	public static Test suite() {
		return new JUnit4TestAdapter(CsvIncrementalModelTestSuite.class);
	}
}
