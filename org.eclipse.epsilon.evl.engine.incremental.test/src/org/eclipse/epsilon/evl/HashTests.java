package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.File;
import java.net.URISyntaxException;

import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.incremental.IEvlTraceFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Module;

public abstract class HashTests<M extends Module> {

	private IncrementalEvlModule module;
	private File evlFile;

	public abstract EvlIncrementalGuiceModule getEvlGuiceModule();

	@Before
	public void setup() throws URISyntaxException {
		module = new IncrementalEvlModule();
		module.injectTraceManager(getEvlGuiceModule());
		evlFile = new File(HashTests.class.getResource("testExecution.evl").toURI());
	}

	@Test
	public void testHash() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String path = HashTests.class.getResource("bankSmall.csv").getPath();
		properties.put(CsvModel.PROPERTY_FILE, path);
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		// module.parse(evlFile);
		// module.context.getModelRepository().addModel(model);

		IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> etManager = module.context
				.getTraceManager();
		IEvlTraceFactory factory = etManager.getTraceFactory();

		IModelTrace modelTrace = factory.createModelTrace(((IIncrementalModel) model).getModelUri());
		int hashCode1 = modelTrace.hashCode();
		int hashCode2 = modelTrace.hashCode();
		assertThat("IModelTrace Hash code is not constant", hashCode1, is(hashCode2));

		IModelTypeTrace typeTrace = modelTrace.getOrCreateModelTypeTrace("TypeA");
		hashCode1 = typeTrace.hashCode();
		hashCode2 = typeTrace.hashCode();
		assertThat("IModelTypeTrace Hash code is not constant", hashCode1, is(hashCode2));

		IModelElementTrace elementTrace = modelTrace.getOrCreateModelElementTrace("some/element/uri", typeTrace);
		hashCode1 = elementTrace.hashCode();
		hashCode2 = elementTrace.hashCode();
		assertThat("IModelElementTrace Hash code is not constant", hashCode1, is(hashCode2));

		IPropertyTrace propertyTrace = elementTrace.getOrCreatePropertyTrace("PropertyOne");
		hashCode1 = propertyTrace.hashCode();
		hashCode2 = propertyTrace.hashCode();
		assertThat("IModelElementTrace Hash code is not constant", hashCode1, is(hashCode2));

		IEvlModuleTrace evlModuleTrace = factory.createModuleTrace(evlFile.getAbsolutePath());
		hashCode1 = evlModuleTrace.hashCode();
		hashCode2 = evlModuleTrace.hashCode();
		assertThat("IEvlModuleTrace Hash code is not constant", hashCode1, is(hashCode2));

		IModelAccess modelAccess = evlModuleTrace.getOrCreateModelAccess("csvModel", modelTrace);
		hashCode1 = modelAccess.hashCode();
		hashCode2 = modelAccess.hashCode();
		assertThat("IModelAccess Hash code is not constant", hashCode1, is(hashCode2));

		IContextTrace contextTrace = evlModuleTrace.getOrCreateContextTrace("TypeA", 1);
		hashCode1 = contextTrace.hashCode();
		hashCode2 = contextTrace.hashCode();
		assertThat("IContextTrace Hash code is not constant", hashCode1, is(hashCode2));

		IContextTrace contextTrace2 = evlModuleTrace.getOrCreateContextTrace("TypeA", 2);
		hashCode1 = contextTrace2.hashCode();
		hashCode2 = contextTrace2.hashCode();
		assertThat("IContextTrace Hash code is not constant", hashCode1, is(hashCode2));

		hashCode1 = contextTrace.hashCode();
		hashCode2 = contextTrace2.hashCode();
		assertThat("IContextTrace Hash code is not constant", hashCode1, is(not(hashCode2)));

		IExecutionContext executionContext = contextTrace.getOrCreateExecutionContext();
		hashCode1 = executionContext.hashCode();
		hashCode2 = executionContext.hashCode();
		assertThat("IExecutionContext Hash code is not constant", hashCode1, is(hashCode2));

		hashCode1 = contextTrace.hashCode();
		hashCode2 = contextTrace.hashCode();
		assertThat("IContextTrace Hash code is not constant", hashCode1, is(hashCode2));

		IModelElementVariable selfVar = contextTrace.executionContext().get().getOrCreateModelElementVariable("self",
				elementTrace);
		hashCode1 = selfVar.hashCode();
		hashCode2 = selfVar.hashCode();
		assertThat("IModelElementVariable Hash code is not constant", hashCode1, is(hashCode2));

		hashCode1 = contextTrace.executionContext().get().hashCode();
		hashCode2 = contextTrace.executionContext().get().hashCode();
		assertThat("IExecutionContext Hash code is not constant", hashCode1, is(hashCode2));

		IInvariantTrace invariantATrace = contextTrace.getOrCreateInvariantTrace("A");
		hashCode1 = invariantATrace.hashCode();
		hashCode2 = invariantATrace.hashCode();
		assertThat("IInvariantTrace Hash code is not constant", hashCode1, is(hashCode2));
	}
}