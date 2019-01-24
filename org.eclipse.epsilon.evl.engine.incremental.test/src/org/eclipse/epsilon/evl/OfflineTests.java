package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvCompare;
import org.eclipse.epsilon.emc.csv.incremental.CsvCompare.CsvComparison;
import org.eclipse.epsilon.emc.csv.incremental.CsvCompare.Diff;
import org.eclipse.epsilon.emc.csv.incremental.CsvCompare.DifferenceKind;
import org.eclipse.epsilon.emc.csv.incremental.CsvCompare.DifferenceSource;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.google.inject.Module;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * Test that the correct access traces are retrieved and the corresponding EVL
 * blocks/rules executed.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class OfflineTests<M extends Module> {

	protected IncrementalEvlModule module;
	private File evlFile;
	private File tempModel;

	public abstract M getEvlGuiceModule();

	@Before
	public void setup() throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		module = new IncrementalEvlModule();
		module.injectTraceManager(getEvlGuiceModule());
		evlFile = new File(OfflineTests.class.getResource("testExecution.evl").toURI());
		module.parse(evlFile);
		tempModel = createTempFile();
	}

	@After
	public void teardown() throws Exception {
			
	}

	@Test
	public void testOnChange() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmall.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().addModel(model);
		module.execute();
		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().iterator().next();
		List<IContextTrace> contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCnt = contextExecutionTraces.size();
		// We have two contexts, traces per Row
		Collection<Map<String, Object>> modelRows = model.getAllOfType("Row");
		assertThat("One ContextTrace per Evl ContraintContext", contextExecutionTraces.size(),
				is(2));
		// Each context should have as many ExecutionContexts as Rows in the model
		for (IContextTrace ct : contextExecutionTraces) {
			List<IExecutionContext> excts = IncrementalUtils.asList(ct.executionContext().get());
			assertThat("One ExecutionContext per Row", excts.size(), is(modelRows.size()));
		}
		// Unsatisfied constraints
		long unsatisfied = module.getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallChange.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().removeModel(model);
		module.getContext().getModelRepository().addModel(model2);

		CsvCompare compare = new CsvCompare();
		CsvComparison comparison = compare.match(model, model2);
		compare.diff(comparison);
		for (Diff d : comparison.getDifferences()) {
			if (d.getKind() == DifferenceKind.CHANGE) {
				Object object;
				if (d.getSource() == DifferenceSource.LEFT) {
					object = d.getMatch().getLeft();
				} else {
					object = d.getMatch().getRight();
				}
				module.onChange(model2, object, d.getFieldName());
			}
		}
		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		assertThat("Change should not create new traces", contextExecutionTracesCntNew - contextExecutionTracesCnt,
				is(0L));
		long unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
		assertThat("Change breaks one constraint", unsatisfiedNew - unsatisfied, is(1L));
	}

	@Test
	public void testOnCreate() throws Exception {

		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmall.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().addModel(model);
		module.execute();

		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().iterator().next();
		List<IContextTrace> contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCnt = contextExecutionTraces.size();
		// We have two contexts, traces per Row
		Collection<Map<String, Object>> modelRows = model.getAllOfType("Row");
		assertThat("One ContextTrace per Evl ContraintContext", contextExecutionTraces.size(),
				is(2));
		// Each context should have as many ExecutionContexts as Rows in the model
		for (IContextTrace ct : contextExecutionTraces) {
			List<IExecutionContext> excts = IncrementalUtils.asList(ct.executionContext().get());
			assertThat("One ExecutionContext per Row", excts.size(), is(modelRows.size()));
		}
		// Unsatisfied constraints
		long unsatisfied = module.getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallInjectA.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().removeModel(model);
		module.getContext().getModelRepository().addModel(model2);

		CsvCompare compare = new CsvCompare();
		CsvComparison comparison = compare.match(model, model2);
		compare.diff(comparison);
		for (Diff d : comparison.getDifferences()) {
			if (d.getKind() == DifferenceKind.ADD) {
				Object object;
				if (d.getSource() == DifferenceSource.LEFT) {
					object = d.getMatch().getLeft();
				} else {
					object = d.getMatch().getRight();
				}
				module.onCreate(model2, object);
			}
		}
		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		assertThat("A new row adss two new ContextTraces", contextExecutionTracesCntNew,
				is(contextExecutionTracesCnt + 2));
		int unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
		System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
		assertThat("Added row should not break any constraints", unsatisfiedNew - unsatisfied, is(0L));
	}

	@Test
	public void testOnDelete() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmallInjectB.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().addModel(model);
		module.execute();

		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().iterator().next();
		List<IContextTrace> contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCnt = contextExecutionTraces.size();
		
		// We have two contexts, traces per Row
		Collection<Map<String, Object>> modelRows = model.getAllOfType("Row");
		assertThat("One ContextTrace per Evl ContraintContext", contextExecutionTraces.size(),
				is(2));
		// Each context should have as many ExecutionContexts as Rows in the model
		for (IContextTrace ct : contextExecutionTraces) {
			List<IExecutionContext> excts = IncrementalUtils.asList(ct.executionContext().get());
			assertThat("One ExecutionContext per Row", excts.size(), is(modelRows.size()));
		}
		
		// Unsatisfied constraints
		long unsatisfied = module.getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallDelete.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel);
		properties.put(CsvModel.PROPERTY_FILE, tempModel.getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module.getContext().getModelRepository().removeModel(model);
		module.getContext().getModelRepository().addModel(model2);

		CsvCompare compare = new CsvCompare();
		CsvComparison comparison = compare.match(model, model2);
		compare.diff(comparison);
		for (Diff d : comparison.getDifferences()) {
			if (d.getKind() == DifferenceKind.DELETE) {
				Object object;
				if (d.getSource() == DifferenceSource.LEFT) {
					object = d.getMatch().getLeft();
				} else {
					object = d.getMatch().getRight();
				}
				module.onDelete(model2, object);
			}
		}

		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		assertThat("Deleted rows removes four ContextTraces", contextExecutionTracesCntNew,
				is(contextExecutionTracesCnt - 4));
		// TODO Make sure invariants, etc disappeared too?
		
		int unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
		System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
		assertThat("Deleted rows remove two unsaisfied contraints", unsatisfiedNew - unsatisfied, is(-2L));
	}

	private File createTempFile() throws IOException {
		File temp = File.createTempFile("temp-model", ".tmp");
		return temp;
	}

	private void copyModelToTempFile(String modelPath, File tempFile) throws IOException {
		Files.copy(Paths.get(modelPath), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

}