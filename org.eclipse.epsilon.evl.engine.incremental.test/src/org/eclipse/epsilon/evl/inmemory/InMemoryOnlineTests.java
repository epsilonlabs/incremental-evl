package org.eclipse.epsilon.evl.inmemory;

import java.io.File;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.ExecutionTests;
import org.eclipse.epsilon.evl.OnlineTests;
import org.eclipse.epsilon.evl.incremental.EvlIncrementalGuiceModule;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.junit.Before;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class InMemoryOnlineTests extends OnlineTests<EvlIncrementalGuiceModule> {

	private File evlFile;
	private IncrementalEvlModule module;
	private String modelFilePath;
	private File modelCopy;
	
	@Before
	public void setup() throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		Injector injector = Guice.createInjector(new EvlIncrementalGuiceModule());
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
		module = injector.getInstance(IncrementalEvlModule.class);
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		modelFilePath = OnlineTests.class.getResource("bankSmall.csv").getPath();
		properties.put(CsvModel.PROPERTY_FILE, modelFilePath);
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.parse(evlFile);
		module.getContext().getModelRepository().addModel(model);
		// Make model copy
		modelCopy = saveModelCopy(modelFilePath);
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
	protected String modelFilePath() {
		return modelFilePath;
	}

	@Override
	protected File modelCopy() {
		return modelCopy;
	}

}
