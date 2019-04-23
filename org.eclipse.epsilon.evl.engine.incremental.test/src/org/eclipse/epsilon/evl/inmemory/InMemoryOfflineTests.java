package org.eclipse.epsilon.evl.inmemory;

import java.io.File;

import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.OfflineTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InMemoryOfflineTests extends OfflineTests<EvlIncrementalGuiceModule> {
	
	private File evlFile;
	private File tempModel;
	private IncrementalEvlModule module;
	
	@Before
	public void setup() throws Exception {
		Injector injector = Guice.createInjector(new EvlIncrementalGuiceModule());
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
		module = injector.getInstance(IncrementalEvlModule.class);
		tempModel = File.createTempFile("temp-model", ".tmp");
	}

	@Override
	public File evlFile() {
		return evlFile;
	}

	@Override
	public IncrementalEvlModule module() {
		return module;
	}

	@Override
	protected File tempModel() {
		return tempModel;
	}	

}
