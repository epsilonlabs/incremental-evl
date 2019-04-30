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
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.junit.Test;

import com.google.inject.Module;

/**
 * Test that the correct access traces are retrieved and the corresponding EVL
 * blocks/rules executed.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class OfflineTests<M extends Module> {

	protected abstract IEvlModuleIncremental module();
	protected abstract File evlFile();
	protected abstract File tempModel();

//	@Before
//	public void setup() throws Exception {
//		evlFile = new File(OfflineTests.class.getResource("testExecution.evl").toURI());
//		module.parse(evlFile);
//		tempModel() = createTempFile();
//	}


	@Test
	public final void testOnChange() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmall.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().addModel(model);
		module().execute();
		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = (IEvlModuleTrace) module().getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().next();
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
		long unsatisfied = module().getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallChange.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().removeModel(model);
		module().getContext().getModelRepository().addModel(model2);

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
				module().onChange(model2, object, d.getFieldName());
			}
		}
		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		assertThat("Change should not create new traces", contextExecutionTracesCntNew - contextExecutionTracesCnt,
				is(0L));
		long unsatisfiedNew = module().getContext().getUnsatisfiedConstraints().size();
		assertThat("Change breaks one constraint", unsatisfiedNew - unsatisfied, is(1L));
	}

	@Test
	public void testOnCreate() throws Exception {

		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmall.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().addModel(model);
		module().execute();

		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = (IEvlModuleTrace) module().getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().next();
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
		long unsatisfied = module().getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallInjectA.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().removeModel(model);
		module().getContext().getModelRepository().addModel(model2);

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
				module().onCreate(model2, object);
			}
		}
		for (UnsatisfiedConstraint uc : module().getContext().getUnsatisfiedConstraints()) {
			System.out.println(uc);
		}
		
		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		assertThat("A new row does not add new ContextTraces", contextExecutionTracesCntNew,
				is(contextExecutionTracesCnt));
		int unsatisfiedNew = module().getContext().getUnsatisfiedConstraints().size();
		System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
		assertThat("Added row should break \"Is in Overdraft\"", unsatisfiedNew - unsatisfied, is(1L));
		// New row should add 1 new model element access
		
	}

	@Test
	public void testOnDelete() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String csvFilePath = OfflineTests.class.getResource("bankSmallInjectB.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().addModel(model);
		module().execute();

		// Save the previous state so we can compare changes
		// ContextTraces
		IEvlModuleTrace moduleTrace = (IEvlModuleTrace) module().getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces().next();
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
		long unsatisfied = module().getContext().getUnsatisfiedConstraints().size();

		csvFilePath = OfflineTests.class.getResource("bankSmallDelete.csv").getPath();
		copyModelToTempFile(csvFilePath, tempModel());
		properties.put(CsvModel.PROPERTY_FILE, tempModel().getAbsolutePath());
		CsvModelIncremental model2 = new CsvModelIncremental();
		model2.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module().getContext().getModelRepository().removeModel(model);
		module().getContext().getModelRepository().addModel(model2);

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
				module().onDelete(model2, object);
			}
		}
		for (UnsatisfiedConstraint uc : module().getContext().getUnsatisfiedConstraints()) {
			System.out.println(uc);
		}
		
		contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		long contextExecutionTracesCntNew = contextExecutionTraces.size();
		// FIXME We need to assert accesses because now the tree is unique
		assertThat("Deleted rows removes four ContextTraces", contextExecutionTracesCntNew,
				is(contextExecutionTracesCnt));
		
		
		int unsatisfiedNew = module().getContext().getUnsatisfiedConstraints().size();
		System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
		assertThat("Deleted rows remove two unsaisfied contraints", unsatisfiedNew - unsatisfied, is(-2L));
	}

	private void copyModelToTempFile(String modelPath, File tempFile) throws IOException {
		Files.copy(Paths.get(modelPath), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

}