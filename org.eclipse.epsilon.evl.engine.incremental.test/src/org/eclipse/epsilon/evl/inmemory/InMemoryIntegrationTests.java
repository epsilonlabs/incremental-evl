package org.eclipse.epsilon.evl.inmemory;

import org.eclipse.epsilon.evl.PreexecutionTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ PreexecutionTests.class, InMemoryExecutionTests.class, InMemoryHashTests.class,
		InMemoryOnlineTests.class, InMemoryOfflineTests.class })
public class InMemoryIntegrationTests {

}
