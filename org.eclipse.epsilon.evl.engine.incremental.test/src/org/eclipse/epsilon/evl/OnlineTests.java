package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvAddRowInjector;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvAppendMethod;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvChangeInjector;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
public abstract class OnlineTests<M extends Module> {

	private IncrementalEvlModule module;
	private File evlFile;
	private String csvFilePath;
	private File modelCopy;

	public abstract EvlIncrementalGuiceModule getEvlGuiceModule();

	@Before
	public void setup() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		csvFilePath = OnlineTests.class.getResource("bankSmall.csv").getPath();
		properties.put(CsvModel.PROPERTY_FILE, csvFilePath);
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});
		module = new IncrementalEvlModule();
		module.injectTraceManager(getEvlGuiceModule());
		evlFile = new File(OnlineTests.class.getResource("testExecution.evl").toURI());
		module.parse(evlFile);
		module.context.getModelRepository().addModel(model);
		// Make model copy
		modelCopy = saveModelCopy(csvFilePath);

		//
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
	}

	@After
	public void teardown() throws Exception {
		restoreModel(modelCopy, csvFilePath);
	}
	
	@Ignore
	@Test
	public void testOnCreate() throws Exception {

		module.setOnlineExecution(true);
		module.execute();
		// Save the previous state so we can compare changes
		// ContextTraces
		Set<IModuleElementTrace> executionTraces = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllExecutionTraces();

		long contextExecutionTraces = executionTraces.stream().filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).count();
		// Unsatisfied constraints
		long unsatisfied = module.getContext().getUnsatisfiedConstraints().size();
		System.out.println("Unsatisfied constraints " + unsatisfied);
		// Add a listener so we can synchronise the execution.
		BlockingQueue<ExecutionResult> results = new SynchronousQueue<ExecutionResult>();
		IExecutionListener<IEolContext> assertExecutionListener = new IExecutionListener<IEolContext>() {

			@Override
			public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception,
					IEolContext context) {
				// TODO Implement Type1516897943540.finishedExecutingWithException
				throw new UnsupportedOperationException(
						"Unimplemented Method    Type1516897943540.finishedExecutingWithException invoked.");
			}

			@Override
			public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
				try {
					results.put(new ExecutionResult(ast, result));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void aboutToExecute(ModuleElement ast, IEolContext context) {
				// We dont trace abouts
			}
		};
		module.getContext().getExecutorFactory().addExecutionListener(assertExecutionListener);

		// Insert Line 71 from test data
		String[] data = new String[] { "CZ63 7593 6158 0945 3366 6310", "Bobrowice", "bkennet1x@wiley.com", "5/16/2017",
				"Brainsphere", "false", "$-3.35", "Often" };
		Path csvPath = Paths.get(csvFilePath);
		CsvAddRowInjector injector = new CsvAddRowInjector(data, csvPath,
				CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader(), CsvAppendMethod.RANDOM);
		ExecutorService executor = Executors.newSingleThreadExecutor();
//			Future<?> future = executor.submit(injector);
//			// Wait for changes in file to finish
//			while (future.isDone()) {
//			}
//			// Wait for CsvModelIncremental to pickup changes and send notifications
//			for (;;) {
//				ExecutionResult r = results.poll(10, TimeUnit.SECONDS); // 10 Seconds for CVS to pick changes
//				// System.out.println(r);
//				if (r == null) {
//					break;
//				}
//			}
//			// Let Evl finish execute
//			Thread.sleep(100);
//			executionTraces = module.getContext().getTraceManager().getExecutionTraceRepository()
//					.getAllExecutionTraces();
//			long contextExecutionTracesNew = executionTraces.stream().filter(t -> t instanceof IContextTrace).count();
//			assertThat("A new row adss two new ContextTraces", contextExecutionTracesNew,
//					is(contextExecutionTraces + 2));
//			long unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
//			System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
//			assertThat("Added row should not break any constraints", unsatisfiedNew - unsatisfied, is(0L));

		// Insert Line 264 from test data
		// contextExecutionTraces = contextExecutionTracesNew;
		// unsatisfied = unsatisfiedNew;
		String[] data2 = new String[] { "BE02 7334 0167 6391", "Pelabuhanratu", "rmaclaughlin7a@loc.gov", "11/21/2017",
				"Buzzdog", "true", "$-28.03", "Often" };
		injector = new CsvAddRowInjector(data2, csvPath, CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader(),
				CsvAppendMethod.RANDOM);
		Future<?> future2 = executor.submit(injector);
		executor.shutdown();
		// Wait for changes in file to finish
		while (future2.isDone()) {
		}
		// Wait for evl to execute again
		for (;;) {
			ExecutionResult r = results.poll(10, TimeUnit.SECONDS); // 10 Seconds for CVS to pick changes
			if (r == null) {
				break;
			}
		}
		// Let Evl finish execute
		Thread.sleep(100);
		executionTraces = module.getContext().getTraceManager().getExecutionTraceRepository().getAllExecutionTraces();
		long contextExecutionTracesNew = executionTraces.stream().filter(t -> t instanceof IContextTrace).count();
		assertThat("A new row adss two new ContextTraces", contextExecutionTracesNew, is(contextExecutionTraces + 2));
		int unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
		System.out.println("New Unsatisfied constraints " + unsatisfiedNew);
		assertThat("Added row should break two constraints", unsatisfiedNew - unsatisfied, is(2L));
	}

	@Ignore
	@Test
	public void testOnChange() throws Exception {
		module.setOnlineExecution(true);
		module.execute();
		// Save the previous state so we can compare changes
		// ContextTraces
		Set<IModuleElementTrace> executionTraces = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllExecutionTraces();
		long contextExecutionTraces = executionTraces.stream().filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).count();
		// Unsatisfied constraints
		long unsatisfied = module.getContext().getUnsatisfiedConstraints().size();

		// Add a listener so we can synchronise the execution.
		BlockingQueue<ExecutionResult> results = new SynchronousQueue<ExecutionResult>();
		IExecutionListener<IEolContext> assertExecutionListener = new IExecutionListener<IEolContext>() {

			@Override
			public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception,
					IEolContext context) {
				// TODO Implement Type1516897943540.finishedExecutingWithException
				throw new UnsupportedOperationException(
						"Unimplemented Method    Type1516897943540.finishedExecutingWithException invoked.");
			}

			@Override
			public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
				try {
					results.put(new ExecutionResult(ast, result));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void aboutToExecute(ModuleElement ast, IEolContext context) {
				// We dont trace abouts
			}
		};
		module.getContext().getExecutorFactory().addExecutionListener(assertExecutionListener);

		// Make a customer go into overdraft
		// PL04 6348 6856 3863 4689 7150 1771 had $85.98, take it to $-45.12
		Path csvPath = Paths.get(csvFilePath);
		CsvChangeInjector injector = new CsvChangeInjector(0, "PL04 6348 6856 3863 4689 7150 1771", 6, "$-45.12",
				csvPath, CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader());
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(injector);
		// Wait for changes in file to finish
		while (future.isDone()) {
		}
		// Wait for CsvModelIncremental to pickup changes and send notifications
		for (;;) {
			ExecutionResult r = results.poll(10, TimeUnit.SECONDS);
			if (r == null) {
				System.out.println("Results done");
				break;
			} else {
				System.out.println("Result added " + r);
			}
		}
		// Let Evl execute
		Thread.sleep(100);
		executionTraces = module.getContext().getTraceManager().getExecutionTraceRepository().getAllExecutionTraces();

		long contextExecutionTracesNew = executionTraces.stream().filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).count();
		assertThat("Change should not create new traces", contextExecutionTracesNew - contextExecutionTraces, is(0L));
		long unsatisfiedNew = module.getContext().getUnsatisfiedConstraints().size();
		assertThat("Change breaks one constraint", unsatisfiedNew - unsatisfied, is(1L));
	}

	private class ExecutionResult {
		private final ModuleElement ast;
		private final Object result;

		public ExecutionResult(ModuleElement ast, Object result) {
			super();
			this.ast = ast;
			this.result = result;
		}

		@Override
		public String toString() {
			return String.format("Executed %s with result %s", ast.toString(), result);
		}

	}

	private File saveModelCopy(String modelPath) throws IOException {
		File temp = File.createTempFile("temp-model", ".tmp");
		// System.out.println("Temp file : " + temp.getAbsolutePath());
		Files.copy(Paths.get(modelPath), temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return temp;
	}

	private void restoreModel(File temp, String modelPath) throws IOException {
		Files.copy(temp.toPath(), Paths.get(modelPath), StandardCopyOption.REPLACE_EXISTING);
	}

}