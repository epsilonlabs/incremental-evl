package org.eclipse.epsilon.evl.incremental.arangodb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.incremental.BitsyGraphResource;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlTinkerpopGuiceModule;
import org.junit.After;
import org.junit.Before;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class TinkerpopExecutionTests extends ExecutionTests {

	private File evlFile;
	private IncrementalEvlModule module;
	private BitsyGraphResource gr;
	
	@Before
	public void setup() throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);
		Path dbPath = Paths.get("/Users/horacio/bitsy");
		if (dbPath.toFile().exists()) {
			// Delete previous graph if exists
			Arrays.stream(dbPath.toFile().listFiles()).forEach(f -> f.delete());
		}
		else {
			dbPath.toFile().mkdir();
		}
		gr = new BitsyGraphResource(dbPath, true);
		
		IncrementalEvlTinkerpopGuiceModule gModule = new IncrementalEvlTinkerpopGuiceModule(gr.getTraversalSource());
		Injector injector = Guice.createInjector(gModule);
		module = injector.getInstance(IncrementalEvlModule.class);
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
	}
	
	@After
	public void tearDown() throws Exception {
		gr.close();
	}
	

	@Override
	public File evlFile() {
		return evlFile;
	}

	@Override
	public IncrementalEvlModule module() {
		return module;
	}

}
